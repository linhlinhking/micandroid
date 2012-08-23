package ningbo.media.service;

import java.util.List;

import ningbo.media.bean.Comment;
import ningbo.media.bean.enums.CommentType;
import ningbo.media.core.service.BaseService;

public interface CommentService extends BaseService<Comment, Integer>{

	public Comment getCommentByUser(String userId,String commentId) ;
	
	public List<Comment> getListByMd5(String md5Value,CommentType type) ;
}
