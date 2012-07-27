package ningbo.media.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_feedback")
public class FeedBack implements Serializable{

	private static final long serialVersionUID = 2199720820237768671L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private String feedEmail ;
	
	private String content ;
	
	private String userMd5Value ;
	
	private Date date_time ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeedEmail() {
		return feedEmail;
	}

	public void setFeedEmail(String feedEmail) {
		this.feedEmail = feedEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserMd5Value() {
		return userMd5Value;
	}

	public void setUserMd5Value(String userMd5Value) {
		this.userMd5Value = userMd5Value;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	

}
