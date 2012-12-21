package com.xero.core.api;

public class XeroXmlParam {

	private XeroXmlParam() {
	}

	public static String postContactXml(String contactId,String Name, String EmailAddress,
			String DefaultNumber) {
		String xml = String.format(CONTACT_XML, Name, EmailAddress,
				DefaultNumber);
		return xml;
	}
	
	 

	private final static String CONTACT_XML = "<Contacts><Contact><ContactID>%s</ContactID><Name>%s</Name><EmailAddress>%s</EmailAddress><Phones><Phone><PhoneType>DEFAULT</PhoneType><PhoneNumber>%s</PhoneNumber></Phone></Phones></Contact></Contacts>";
}
