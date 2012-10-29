package ningbo.media.rest.resource;

import java.util.Date;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ningbo.media.bean.Location;
import ningbo.media.bean.LocationProduct;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.LocationProductService;
import ningbo.media.service.LocationService;
import ningbo.media.util.MD5;
import ningbo.media.util.StringUtil;
import ningbo.media.util.TranslateUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/locationProduct")
@Component
@Scope("request")
public class LocationProductRest {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private LocationProductService locationProductService;

	@Resource
	private LocationService locationService;

	/**
	 * @FormParam("name_en") String nameEn,
	 * @FormParam("description_en") String descriptioinEn,
	 * @FormParam("tags_en") String tagsEn,
	 * @throws JSONException
	 */
	@Path("/saveOrUpdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOrEditLocationProduct(@FormParam("key") String key,
			@FormParam("name_cn") String nameCn,
			@FormParam("description_cn") String descriptionCn,
			@FormParam("tags_cn") String tagsCn,
			@FormParam("price") String price,
			@FormParam("locationId") String locationMd5Value,
			@FormParam("productId") String productMd5Value)
			throws JSONException {
		JSONObject json = new JSONObject();
		LocationProduct locProduct = null;
		try {
			if (key.isEmpty()) {
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_ISNULL);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();

			} else if (!Constant.KEY.equals(key)) {
				json.put(Constant.MESSAGE, JSONCode.MSG_KEY_INVALID);
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				return Response.ok(json.toString()).build();
			}
			Location tempLocation = locationService.get(Constant.MD5_FIELD,
					locationMd5Value);
			if (null == tempLocation) {
				json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
				json.put(Constant.MESSAGE, JSONCode.MSG_LOCATION_MD5_NOEXISTS);
				return Response.ok(json.toString()).build();
			}

			if (StringUtils.hasText(productMd5Value)) {
				locProduct = locationProductService.get(Constant.MD5_FIELD,
						productMd5Value);
				locProduct.setUpdateDateTime(new Date());
			} else {
				locProduct = new LocationProduct();
				locProduct.setCreateDateTime(new Date());
				locProduct.setIsDeleted(false);
			}

			if (null != nameCn && nameCn.length() > 0) {
				locProduct.setNameCn(nameCn);
				String name_en = TranslateUtil.getEnglishByChinese(nameCn);
				locProduct.setNameEn(name_en);
			}
			if (null != tagsCn && tagsCn.length() > 0) {
				locProduct.setTagsCn(tagsCn);
				String tags_en = TranslateUtil.getEnglishByChinese(tagsCn);
				locProduct.setTagsEn(tags_en);
			}

			if (null != descriptionCn && descriptionCn.length() > 0) {
				locProduct.setDescriptionCn(descriptionCn);
				String desc_en = TranslateUtil
						.getEnglishByChinese(descriptionCn);
				locProduct.setDescriptionEn(desc_en);
			}

			if (StringUtil.isNumeric(price)) {
				locProduct.setPrice(Double.valueOf(price));
			} else {
				locProduct.setPrice(0.0);
			}
			locProduct.setLocation(tempLocation);

			locProduct = locationProductService.saveOrUpdate(locProduct);
			Integer proIds = locProduct.getId();
			String proMd5 = MD5.calcMD5(String.valueOf(proIds));
			locProduct.setMd5Value(proMd5);
			locationProductService.saveOrUpdate(locProduct);

			json.put(Constant.RESULT, JSONCode.RESULT_SUCCESS);
			json.put(Constant.PRODUCTID, proIds);

		} catch (Exception ex) {
			logger.error("SaveOrEdit Product Information Error.", ex);
			json.put(Constant.RESULT, JSONCode.RESULT_FAIL);
			json.put(Constant.MESSAGE, "SaveOrEdit Product Information Error.");
		}

		return Response.ok(json.toString()).build();
	}

	@Path("/products/{md5Value}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProductsByLocation(
			@PathParam("md5Value") String md5Value) {
		
		return null;
	}

}
