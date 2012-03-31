package com.ssh2.vo;

public class BaseModel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -96124306794812602L;

	protected String id = null;
	
	/**
	 * Get the identity of the model
	 * @return Identity
	 * @author <a href="mailto:z405656232x@163.com">JeccyZhao</a>
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * Set identity to the model
	 * @param id Identify of model
	 * @author <a href="mailto:z405656232x@163.com">JeccyZhao</a>
	 */
	public void setId(String id){
		try{
			this.id = (id == null || id.equals("") ||Integer.parseInt(id) <= 0) ? null : id;
		} catch (NumberFormatException e){
			this.id = null;
		}
	}

}
