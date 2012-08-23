package ningbo.media.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ningbo.media.bean.Comment;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.CommentDao;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment, Integer> implements
		CommentDao {

	private final Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);

	public CommentDaoImpl() {
		super(Comment.class);
	}

	public Comment getCommentByUser(String userId, String commentId) {
		try {
			String hql = "from Comment as m where 1=1 and m.id = ? and m.systemUser.id = ? ";
			

			Comment comment = (Comment) findUnique(hql, Integer
					.valueOf(commentId), Integer.valueOf(userId));

			if (null != comment) {
				return comment;
			}
		} catch (Exception ex) {
			logger.error("Get commnet Error.userId:" + userId + ",commentId:"
					+ commentId);
		}
		return null;
	}

}
