package ningbo.media.bean.tuan;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_groupbuy")
public class GroupBuy implements Serializable {

	private static final long serialVersionUID = 3053294129558071898L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;// 团购主题

	private float currentPrice; // 当前价格

	private float originalPrice;// 原始价格

	private float discount; // 折扣

	private float saved; // 节省

	private long startDate;// 开始时间

	private long endDate; // 结束时间

	private long orderMinimum;// 最少团购

	private long orderTotal;// 团购总数

	private String tip; // 提示

	private String light; // 亮点（特色）

	private String detail; // 团购详细介绍

	private boolean success;// 是否成功

	private boolean finished;// 是否完成
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "shopId")
	private Shop shop ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getSaved() {
		return saved;
	}

	public void setSaved(float saved) {
		this.saved = saved;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}


	public long getOrderMinimum() {
		return orderMinimum;
	}

	public void setOrderMinimum(long orderMinimum) {
		this.orderMinimum = orderMinimum;
	}

	public long getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(long orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
