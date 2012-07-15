package ningbo.media.core.kernel;

import ningbo.media.core.kernel.generate.GenerateService;
import ningbo.media.core.kernel.generate.IGenerateService;
import ningbo.media.core.kernel.util.ResourceUtils;

public class App {
	public static void main(String[] args) {

		//System.out.println(System.getProperty("user.dir"));

		ResourceUtils.getInstance();
		IGenerateService service = new GenerateService();
		service.generateFile("src/main");
	}
}
