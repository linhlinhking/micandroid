package ningbo.media.rest.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class RestConfig {
	
	public static final String CONFIG_PATH = "rest-config.xml" ; 
	
	private static RestConfig config = new RestConfig() ;
	
	private boolean isParsed = false;
	
	private Project project  = new Project() ;
	
	
	private RestConfig(){}
	
	public static RestConfig getInstance(){
		if(config == null){
			config = new RestConfig() ;
		}
		return config ;
	}
	
public Project parserRestConfig() {
		
		if(RestConfig.getInstance().isParsed) {
			return project;
		}
	
		String sConfigFilePath = this.getClass().getResource("/"+CONFIG_PATH).getFile();    
		SAXReader saxReader = new SAXReader();
	    Document document = null;
		try {
			document = saxReader.read(sConfigFilePath);
			Element rootEle = document.getRootElement();
			Element restPro = rootEle.element(RestConstant.REST_PROJECT);
			//set name
			project.setName(restPro.attributeValue(RestConstant.REST_NAME));
			Element remote = restPro.element(RestConstant.REST_REMOTE_ADDRESS);
			//set remote address
			project.getRemoteAddress().setAddress(remote.attributeValue(RestConstant.REST_ADDRESS));
			project.getRemoteAddress().setPort(remote.attributeValue(RestConstant.REST_PORT));
			
			//set context url
			project.setContextUrl(restPro.elementTextTrim(RestConstant.REST_CONTEXT_URL));
			project.setRestRootUrl(restPro.elementTextTrim(RestConstant.REST_ROOT_URL));
			Element modules = restPro.element(RestConstant.REST_MODULES);
			for(int i=0; i < modules.elements().size(); i++) {				
				Element moduleUrl = (Element)modules.elements().get(i);
				Module module = new Module();
				module.setName(moduleUrl.attributeValue(RestConstant.REST_NAME));
				module.setPath(moduleUrl.attributeValue(RestConstant.REST_MODULE_PATH));
				project.getModules().add(module);
			}
			getInstance().isParsed = true;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	  return project;
	}

	public String getUserMgrPath(){
		int temp = parserRestConfig().getModules().size() ;
		for(int i=0; i < temp;i++){
			Module m = parserRestConfig().getModules().get(i) ;
			if(m.getName().equalsIgnoreCase(RestConstant.REST_MODULE_USER_NAME)){
				return m.getPath() ;
			}
		}
		return null ;
	}
	
	public String getUserMgrName(){
		int temp = parserRestConfig().getModules().size() ;
		for(int i=0; i < temp;i++){
			Module m = parserRestConfig().getModules().get(i) ;
			if(m.getName().equalsIgnoreCase(RestConstant.REST_MODULE_USER_NAME)){
				return m.getName() ;
			}
		}
		return null ;
	}
	
}
