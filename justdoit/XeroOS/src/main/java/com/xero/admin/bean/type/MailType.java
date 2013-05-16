package com.xero.admin.bean.type;

public enum MailType {

	MAILNEWUSER(1), MAILSUPPLIERS(2),MAILCUSTOMER(3),MAILREGISTER(4),MAILFORGOTPASSWORD(5),MAILREMIND(6);

	private Integer value;

	MailType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	

}
