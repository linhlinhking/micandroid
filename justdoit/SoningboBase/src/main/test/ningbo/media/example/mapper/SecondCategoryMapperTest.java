package ningbo.media.example.mapper;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.core.util.Pinyin;
import ningbo.media.core.util.TranslateUtil;
import ningbo.media.website.entity.SecondCategory;
import ningbo.media.website.mapper.SecondCategoryMapper;

import org.junit.Test;

public class SecondCategoryMapperTest extends BaseTest {

	@Resource
	private SecondCategoryMapper secondCategoryMapper;

	public void getAllCategory2() {
		List<SecondCategory> lists = secondCategoryMapper
				.getSecondCategoryByFirstId(16);
		if (null != lists && lists.size() > 0) {
			for (SecondCategory sc : lists) {
				System.out.println(sc.getName_cn());
			}
		} else {
			System.out.println("No Id");
		}

	}

	@Test
	public void testAddSecondCategory2() {
		SecondCategory sc = new SecondCategory();
		String name_cn = "宁波商外文化传媒有限公司";
		String name_en = TranslateUtil.getEnglishByChinese(name_cn);
		String name_py = Pinyin.getPinYin(name_cn);
		String tags_cn = "软件开发，设计，技术服务，软件外包";
		String tags_en = TranslateUtil.getEnglishByChinese(tags_cn);

		sc.setName_cn(name_cn);
		sc.setName_en(name_en);
		sc.setName_py(name_py);
		sc.setKeywords_cn(tags_cn);
		sc.setKeywords_en(tags_en);
		sc.setDescription_cn("") ;
		sc.setDescription_en("") ;
		sc.setFirstId(1);
		

		try {
			secondCategoryMapper.addSecondCategory(sc);
			System.out.println("OK") ;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
