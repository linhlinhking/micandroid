package ningbo.media.rest.ws.result;

import ningbo.media.rest.base.WSResult;
import ningbo.media.rest.dto.FirstCategoryData;

public class FirstCategoryResult extends WSResult {

	private FirstCategoryData firstCategory;

	public FirstCategoryResult() {
	}

	public FirstCategoryResult(FirstCategoryData firstCategory) {
		this.firstCategory = firstCategory;
	}

	public FirstCategoryData getFirstCategory() {
		return firstCategory;
	}

}
