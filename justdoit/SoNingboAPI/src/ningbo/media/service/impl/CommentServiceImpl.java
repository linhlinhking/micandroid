package ningbo.media.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.bean.Comment;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.enums.CommentType;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.CommentDao;
import ningbo.media.dao.LocationDao;
import ningbo.media.dao.SystemUserDao;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, Integer>
		implements CommentService {

	@Resource
	private CommentDao commentDao;

	@Resource
	private SystemUserDao systemUserDao;
	
	@Resource
	private LocationDao locationDao;

	@Autowired
	public CommentServiceImpl(@Qualifier("commentDao")
	CommentDao commentDao) {
		super(commentDao);
	}

	public Comment getCommentByUser(String userId, String commentId) {
		try {
			SystemUser u = systemUserDao.get(Constant.MD5_FIELD, userId);
			if (null == u) {
				return null;
			}
			return commentDao.getCommentByUser(String.valueOf(u.getId()),
					commentId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public List<Comment> getListByMd5(String md5Value, CommentType type) {
		List<Comment> list = null ;
		try {
			if (CommentType.LOCATION.equals(type)) {
				Location loc = locationDao.get(Constant.MD5_FIELD, md5Value) ;
				if(null == loc){
					return null ;
				}
				Integer id = loc.getId() ;
				list = commentDao.getList("location.id", id) ;
			} else if (CommentType.USER.equals(type)) {
				SystemUser u = systemUserDao.get(Constant.MD5_FIELD, md5Value) ;
				if(null == u){
					return null ;
				}
				Integer id = u.getId() ;
				list = commentDao.getList("systemUser.id", id) ;
				
			} else {
				return null ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return list ;
	}

}
