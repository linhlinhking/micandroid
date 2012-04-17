package com.jshop.entity;

import java.sql.Timestamp;

/**
 * ElectronicMenuOrderT entity. @author MyEclipse Persistence Tools
 */

public class ElectronicMenuOrderT implements java.io.Serializable {

	// Fields    

	private String electronicMenuOrderid;
	private String userid;
	private String username;
	private String paymentid;
	private String paymentname;
	private String delivermode;
	private String deliverynumber;
	private String electronicorderstate;
	private String logisticsid;
	private Double freight;
	private Double amount;
	private Double points;
	private Timestamp purchasetime;
	private Timestamp deliverytime;
	private String invoice;
	private String shippingaddressid;
	private String customernotes;
	private String logisticswebaddress;
	private Timestamp paytime;
	private String orderTag;
	private String toBuyer;
	private Double shouldpay;
	private Double usepoints;
	private String vouchersid;
	private String goodid;
	private String goodsname;
	private Integer needquantity;
	private String paystate;
	private String shippingstate;
	private String deliveraddressid;
	private String shippingusername;
	private Timestamp createtime;
	private String hasprintexpress;
	private String hasprintinvoice;
	private String hasprintfpinvoice;
	private String expressnumber;
	private String tradeNo;
	private String tableNumber;
	private String roomName;
	private String tablestate;

	// Constructors

	/** default constructor */
	public ElectronicMenuOrderT() {
	}

	/** minimal constructor */
	public ElectronicMenuOrderT(String electronicMenuOrderid, String paymentid, String paymentname, String electronicorderstate, Double freight, Double amount, Timestamp purchasetime, String invoice, Double shouldpay, Double usepoints, String paystate, Timestamp createtime, String tableNumber, String tablestate) {
		this.electronicMenuOrderid = electronicMenuOrderid;
		this.paymentid = paymentid;
		this.paymentname = paymentname;
		this.electronicorderstate = electronicorderstate;
		this.freight = freight;
		this.amount = amount;
		this.purchasetime = purchasetime;
		this.invoice = invoice;
		this.shouldpay = shouldpay;
		this.usepoints = usepoints;
		this.paystate = paystate;
		this.createtime = createtime;
		this.tableNumber = tableNumber;
		this.tablestate = tablestate;
	}

	/** full constructor */
	public ElectronicMenuOrderT(String electronicMenuOrderid, String userid, String username, String paymentid, String paymentname, String delivermode, String deliverynumber, String electronicorderstate, String logisticsid, Double freight, Double amount, Double points, Timestamp purchasetime, Timestamp deliverytime, String invoice, String shippingaddressid, String customernotes, String logisticswebaddress, Timestamp paytime, String orderTag, String toBuyer, Double shouldpay, Double usepoints, String vouchersid, String goodid, String goodsname, Integer needquantity, String paystate, String shippingstate, String deliveraddressid, String shippingusername, Timestamp createtime, String hasprintexpress, String hasprintinvoice, String hasprintfpinvoice, String expressnumber, String tradeNo,
			String tableNumber, String roomName, String tablestate) {
		this.electronicMenuOrderid = electronicMenuOrderid;
		this.userid = userid;
		this.username = username;
		this.paymentid = paymentid;
		this.paymentname = paymentname;
		this.delivermode = delivermode;
		this.deliverynumber = deliverynumber;
		this.electronicorderstate = electronicorderstate;
		this.logisticsid = logisticsid;
		this.freight = freight;
		this.amount = amount;
		this.points = points;
		this.purchasetime = purchasetime;
		this.deliverytime = deliverytime;
		this.invoice = invoice;
		this.shippingaddressid = shippingaddressid;
		this.customernotes = customernotes;
		this.logisticswebaddress = logisticswebaddress;
		this.paytime = paytime;
		this.orderTag = orderTag;
		this.toBuyer = toBuyer;
		this.shouldpay = shouldpay;
		this.usepoints = usepoints;
		this.vouchersid = vouchersid;
		this.goodid = goodid;
		this.goodsname = goodsname;
		this.needquantity = needquantity;
		this.paystate = paystate;
		this.shippingstate = shippingstate;
		this.deliveraddressid = deliveraddressid;
		this.shippingusername = shippingusername;
		this.createtime = createtime;
		this.hasprintexpress = hasprintexpress;
		this.hasprintinvoice = hasprintinvoice;
		this.hasprintfpinvoice = hasprintfpinvoice;
		this.expressnumber = expressnumber;
		this.tradeNo = tradeNo;
		this.tableNumber = tableNumber;
		this.roomName = roomName;
		this.tablestate = tablestate;
	}

	// Property accessors

	public String getElectronicMenuOrderid() {
		return this.electronicMenuOrderid;
	}

	public void setElectronicMenuOrderid(String electronicMenuOrderid) {
		this.electronicMenuOrderid = electronicMenuOrderid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPaymentid() {
		return this.paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public String getPaymentname() {
		return this.paymentname;
	}

	public void setPaymentname(String paymentname) {
		this.paymentname = paymentname;
	}

	public String getDelivermode() {
		return this.delivermode;
	}

	public void setDelivermode(String delivermode) {
		this.delivermode = delivermode;
	}

	public String getDeliverynumber() {
		return this.deliverynumber;
	}

	public void setDeliverynumber(String deliverynumber) {
		this.deliverynumber = deliverynumber;
	}

	public String getElectronicorderstate() {
		return this.electronicorderstate;
	}

	public void setElectronicorderstate(String electronicorderstate) {
		this.electronicorderstate = electronicorderstate;
	}

	public String getLogisticsid() {
		return this.logisticsid;
	}

	public void setLogisticsid(String logisticsid) {
		this.logisticsid = logisticsid;
	}

	public Double getFreight() {
		return this.freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPoints() {
		return this.points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public Timestamp getPurchasetime() {
		return this.purchasetime;
	}

	public void setPurchasetime(Timestamp purchasetime) {
		this.purchasetime = purchasetime;
	}

	public Timestamp getDeliverytime() {
		return this.deliverytime;
	}

	public void setDeliverytime(Timestamp deliverytime) {
		this.deliverytime = deliverytime;
	}

	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getShippingaddressid() {
		return this.shippingaddressid;
	}

	public void setShippingaddressid(String shippingaddressid) {
		this.shippingaddressid = shippingaddressid;
	}

	public String getCustomernotes() {
		return this.customernotes;
	}

	public void setCustomernotes(String customernotes) {
		this.customernotes = customernotes;
	}

	public String getLogisticswebaddress() {
		return this.logisticswebaddress;
	}

	public void setLogisticswebaddress(String logisticswebaddress) {
		this.logisticswebaddress = logisticswebaddress;
	}

	public Timestamp getPaytime() {
		return this.paytime;
	}

	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	public String getOrderTag() {
		return this.orderTag;
	}

	public void setOrderTag(String orderTag) {
		this.orderTag = orderTag;
	}

	public String getToBuyer() {
		return this.toBuyer;
	}

	public void setToBuyer(String toBuyer) {
		this.toBuyer = toBuyer;
	}

	public Double getShouldpay() {
		return this.shouldpay;
	}

	public void setShouldpay(Double shouldpay) {
		this.shouldpay = shouldpay;
	}

	public Double getUsepoints() {
		return this.usepoints;
	}

	public void setUsepoints(Double usepoints) {
		this.usepoints = usepoints;
	}

	public String getVouchersid() {
		return this.vouchersid;
	}

	public void setVouchersid(String vouchersid) {
		this.vouchersid = vouchersid;
	}

	public String getGoodid() {
		return this.goodid;
	}

	public void setGoodid(String goodid) {
		this.goodid = goodid;
	}

	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Integer getNeedquantity() {
		return this.needquantity;
	}

	public void setNeedquantity(Integer needquantity) {
		this.needquantity = needquantity;
	}

	public String getPaystate() {
		return this.paystate;
	}

	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}

	public String getShippingstate() {
		return this.shippingstate;
	}

	public void setShippingstate(String shippingstate) {
		this.shippingstate = shippingstate;
	}

	public String getDeliveraddressid() {
		return this.deliveraddressid;
	}

	public void setDeliveraddressid(String deliveraddressid) {
		this.deliveraddressid = deliveraddressid;
	}

	public String getShippingusername() {
		return this.shippingusername;
	}

	public void setShippingusername(String shippingusername) {
		this.shippingusername = shippingusername;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getHasprintexpress() {
		return this.hasprintexpress;
	}

	public void setHasprintexpress(String hasprintexpress) {
		this.hasprintexpress = hasprintexpress;
	}

	public String getHasprintinvoice() {
		return this.hasprintinvoice;
	}

	public void setHasprintinvoice(String hasprintinvoice) {
		this.hasprintinvoice = hasprintinvoice;
	}

	public String getHasprintfpinvoice() {
		return this.hasprintfpinvoice;
	}

	public void setHasprintfpinvoice(String hasprintfpinvoice) {
		this.hasprintfpinvoice = hasprintfpinvoice;
	}

	public String getExpressnumber() {
		return this.expressnumber;
	}

	public void setExpressnumber(String expressnumber) {
		this.expressnumber = expressnumber;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTableNumber() {
		return this.tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getTablestate() {
		return this.tablestate;
	}

	public void setTablestate(String tablestate) {
		this.tablestate = tablestate;
	}

}