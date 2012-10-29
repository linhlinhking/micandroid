package ningbo.media.bean;



import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product_file")
public class ProductFile extends BaseEntity{
	
	private static final long serialVersionUID = 2174464570407820468L;

	private Integer productId ;
	
	private Integer fileId ;
	
	private Integer userId ;
	
	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
