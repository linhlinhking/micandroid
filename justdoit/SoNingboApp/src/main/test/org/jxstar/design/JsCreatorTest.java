package org.jxstar.design;

import java.util.List;
import java.util.Map;

import org.jxstar.dao.BaseDao;
import org.jxstar.dao.DaoParam;
import org.jxstar.fun.design.JsCreatorBO;
import org.jxstar.test.AbstractTest;

public class JsCreatorTest extends AbstractTest {
	
	public static void main(String[] args) {
		/*PageTemplet pageTpl = PageTemplet.getInstance();
		pageTpl.read(path+"/WEB-INF/tpl/grid-page-tpl.txt", "grid");
		
		ElementTemplet elTpl = ElementTemplet.getInstance();
		elTpl.read(path+"/WEB-INF/tpl/grid-element-tpl.xml", "grid");
		
		pageTpl.read(path+"/WEB-INF/tpl/form-page-tpl.txt", "form");
		elTpl.read(path+"/WEB-INF/tpl/form-element-tpl.xml", "form");*/
		
		//JsCreatorBO parse = new JsCreatorBO();
		//parse.createJs("doss_card", "form", path);
		
		createpage();
	}
	
	@SuppressWarnings("rawtypes")
	public static void createpage() {
		String path = "D:/tomcat6/webapps/jxstar/";
		
		BaseDao dao = BaseDao.getInstance();
		String sql = "select * from fun_base where (modify_userid > ' ' or "+
			"module_id = '10050001' or module_id = '10100002' or module_id = '10090003')";
		DaoParam param = dao.createParam(sql);
		List ls = dao.query(param);
		
		JsCreatorBO parse = new JsCreatorBO();
		for (int i = 0; i < ls.size(); i++) {
			Map mp = (Map) ls.get(i);
			
			String funid = (String) mp.get("fun_id");
			
			parse.createJs(funid, "grid", path);
			parse.createJs(funid, "form", path);
		}
	}
}
