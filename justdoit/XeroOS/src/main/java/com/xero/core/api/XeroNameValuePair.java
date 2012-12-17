package com.xero.core.api;

import java.io.Serializable;

public class XeroNameValuePair implements Serializable {

	private static final long serialVersionUID = -661365934169535615L;

	/**
	 * Default constructor.
	 * 
	 */
	public XeroNameValuePair() {
		this(null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The name.
	 * @param value
	 *            The value.
	 */
	public XeroNameValuePair(String name, String value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Name.
	 */
	private String name = null;

	/**
	 * Value.
	 */
	private String value = null;

	/**
	 * Set the name.
	 * 
	 * @param name
	 *            The new name
	 * @see #getName()
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the name.
	 * 
	 * @return String name The name
	 * @see #setName(String)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value.
	 * 
	 * @param value
	 *            The new value.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Return the current value.
	 * 
	 * @return String value The current value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Get a String representation of this pair.
	 * 
	 * @return A string representation.
	 */
	public String toString() {
		return ("name=" + name + ", " + "value=" + value);
	}

}
