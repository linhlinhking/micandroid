//test flexgrid
$(function(){
	$("#flex").flexigrid({
		url:"http://localhost:8000/showPage/1/20"
		dataType:"json",
		colModel:[{
			 display : 'ID',
	         name : 'id',
	         width : 50,// 得加上 要不IE报错
	         sortable : true,
	         align : 'center'
		},{
			 display : 'name_cn',
	         name : 'name_cn',
	         width : 50,
	         sortable : false,
	         align : 'center'
		}
		],
		usepager:true,
		title:"",
		useRp:true,
		checkbox:true,
		rpOptions: [10, 15, 20, 30, 50], 
		rowId:'id',
		rp:20,
		showTableToggleBtn:true,
		width:"auto",
		height:"200"
		
	});
	
	
	
})