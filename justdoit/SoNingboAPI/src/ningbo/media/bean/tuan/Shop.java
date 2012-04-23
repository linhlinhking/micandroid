package ningbo.media.bean.tuan;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ningbo.media.bean.Location;

@Entity
@Table(name = "tb_shop")
public class Shop implements Serializable {

	private static final long serialVersionUID = 7687820221139769023L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String shopArea; //商家区域

	private String shopTrafficinfo;//交通信息

	@OneToOne
	@JoinColumn(name = "loctionId")
	private Location location ;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "shop")
	private List<GroupBuy> groupBuys ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<GroupBuy> getGroupBuys() {
		return groupBuys;
	}

	public void setGroupBuys(List<GroupBuy> groupBuys) {
		this.groupBuys = groupBuys;
	}
	
	


}
