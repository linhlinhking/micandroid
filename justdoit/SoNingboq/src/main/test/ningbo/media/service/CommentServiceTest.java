package ningbo.media.service;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.Comment;

import org.junit.Test;

public class CommentServiceTest extends BaseTest{

	@Resource
	private CommentService commentService ;
	
	@Test
	public void testSysteUser(){
		List<Comment> list = commentService.getList("systemUser.id", 27) ;
		for(Comment c : list){
			System.out.println(c.getLocation().getId()) ;
		}
	}
}
