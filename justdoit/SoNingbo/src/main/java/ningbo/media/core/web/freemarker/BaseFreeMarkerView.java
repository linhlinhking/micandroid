package ningbo.media.core.web.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * Description:模板上下文路径
 * 
 * @author Devon.Ning
 * @2012-4-16下午03:41:08
 * @version 1.0 Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.
 */
public class BaseFreeMarkerView extends FreeMarkerView {

	public static final String BASE_PATH = "ctx";

	@Override
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		super.exposeHelpers(model, request);
		model.put(BASE_PATH, request.getContextPath());

	}

}