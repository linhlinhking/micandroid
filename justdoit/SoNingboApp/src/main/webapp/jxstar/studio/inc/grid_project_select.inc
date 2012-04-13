	config.eventcfg = {

		selproject: function(){
			var records = this.grid.getSelectionModel().getSelections();
			if (!JxUtil.selectone(records)) return;

			var keyid = records[0].get(this.define.pkcol);

			var params = 'keyid='+ keyid +'&funid='+ this.define.nodeid;
			
			params += '&pagetype=editgrid&eventcode=selproject';

			//保存当前项目信息
			var hdcall = function(data) {
				Jxstar.session['project_id'] = records[0].get('project_list__project_id');
				Jxstar.session['project_code'] = records[0].get('project_list__project_code');
				Jxstar.session['project_name'] = records[0].get('project_list__project_name');
				Jxstar.session['project_path'] = records[0].get('project_list__project_path');
			};

			Request.postRequest(params, hdcall);
		}
	};