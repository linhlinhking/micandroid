<%@ page pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="resources/js/extjs/examples/grid/grid-examples.css" />

<!-- Common Styles for the examples -->
<link rel="stylesheet" type="text/css" href="resources/js/extjs/examples/shared/examples.css" />

<script>
	Ext.onReady(function(){

    // create the Data Store
    var store = new Ext.data.JsonStore({
        root: 'users',
        totalProperty: 'totalCount',
        idProperty: 'id',
        remoteSort: false,

        fields: [
            'id', 'name', 'mail', 'preview'
        ],

        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        proxy: new Ext.data.ScriptTagProxy({
            url: 'sysmgr/UserMgr/index'
        })
    });

    var grid = new Ext.grid.GridPanel({
        width:700,
        height:500,
        title:'ExtJS.com - Browse Forums--need modified',
        store: store,
        trackMouseOver:false,
        disableSelection:true,
        loadMask: true,

        // grid columns
        columns:[{
            id: 'topic', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
            header: "ID",
            dataIndex: 'id',
            width: 40,
            sortable: true
        },{
            header: "Name",
            dataIndex: 'name',
            width: 100,
            sortable: true
        },{
            header: "Mail",
            dataIndex: 'mail',
            width: 270,
            align: 'right',
            sortable: true
        }],

        // customize view config
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true,
            getRowClass : function(record, rowIndex, p, store){
                if(this.showPreview){
                    p.body = '<p>'+record.data.preview+'</p>';
                    return 'x-grid3-row-expanded';
                }
                return 'x-grid3-row-collapsed';
            }
        },

        // paging bar on the bottom
        bbar: new Ext.PagingToolbar({
            pageSize: 25,
            store: store,
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display",
            items:[
                '-', {
                pressed: true,
                enableToggle:true,
                text: 'Show Preview',
                cls: 'x-btn-text-icon details',
                toggleHandler: function(btn, pressed){
                    var view = grid.getView();
                    view.showPreview = pressed;
                    view.refresh();
                }
            }]
        })
    });

    // render it
    grid.render('topic-grid');

    // trigger the data store load
    store.load({params:{start:0, limit:25}});
});
</script>

<h1>Paging Grid Example</h1>
<p>This example shows how to create a grid with paging. This grid uses a ScriptTagProxy to fetch cross-domain
    remote data (from the Ext forums).</p>
<p>Note that the js is not minified so it is readable. See <a href="paging.js">paging.js</a>.</p>

<div id="topic-grid"></div>