package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Comment;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.CommentDao;


@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment, Integer> implements
		CommentDao {
	
	public CommentDaoImpl(){
		super(Comment.class) ;
	}
	
}
