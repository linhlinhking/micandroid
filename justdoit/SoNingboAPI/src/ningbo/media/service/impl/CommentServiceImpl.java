package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.Comment;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.CommentDao;
import ningbo.media.service.CommentService;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, Integer> implements CommentService{

	@Autowired
	public CommentServiceImpl(@Qualifier("commentDao")CommentDao commentDao) {
		super(commentDao);
	}

}
