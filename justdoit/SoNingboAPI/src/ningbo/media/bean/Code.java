package ningbo.media.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "code")
@XmlRootElement
public class Code implements Serializable{

	private static final long serialVersionUID = -4773354848366126719L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id ;
	/**
	 * 自定义的code
	 */
	private Integer codeNum; 
	/**
	 * 自定义code的描述
	 */
	private String message; 
	/**
	 * http响应码
	 */
	private Integer httpCode; 
	
	public Code(){}
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getCodeNum() {
		return codeNum;
	}



	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Integer getHttpCode() {
		return httpCode;
	}



	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}



	@Override
	public String toString() {
		return "Code [id=" + id + ", codeNum=" + codeNum + ", message="
				+ message + ", httpCode=" + httpCode + "]";
	}


	public String toJson(List<Code> list){
		JSONObject json = new JSONObject() ;
		try {
			if(list != null && list.size() > 0){
				for(Code code : list){
					json.put("id", code.getId()) ;
					json.put("codeNum", code.getCodeNum()) ;
					json.put("message", code.getMessage()) ;
					json.put("httpCode", code.getHttpCode()) ;
				}
			}
			return json.toString() ;
		} catch (JSONException e) {
			e.printStackTrace();
			return null ;
		}
		
	}
}
