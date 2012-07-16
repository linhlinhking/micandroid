package ningbo.media.service.impl;

import javax.annotation.Resource;

import ningbo.media.bean.Comment;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.CommentDao;
import ningbo.media.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, Integer>
		implements CommentService {

	@Resource
	private CommentDao commentDao;

	@Autowired
	public CommentServiceImpl(@Qualifier("commentDao")
	CommentDao commentDao) {
		super(commentDao);
	}

	public Comment getCommentByUser(String userId, String commentId) {
		return commentDao.getCommentByUser(userId, commentId);
	}

}
