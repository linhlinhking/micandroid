package ningbo.media.bean.tuan;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_shop")
public class Shop implements Serializable {

	private static final long serialVersionUID = 7687820221139769023L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String shopName;

	private String shopAddress;

	private String shopArea;

	private String shopTrafficinfo;

	private Double shopLatitude;

	private Double shopLongitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopArea() {
		return shopArea;
	}

	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}

	public String getShopTrafficinfo() {
		return shopTrafficinfo;
	}

	public void setShopTrafficinfo(String shopTrafficinfo) {
		this.shopTrafficinfo = shopTrafficinfo;
	}

	public Double getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(Double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}

	public Double getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(Double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

}
