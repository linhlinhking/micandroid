/*
 * Licensed to csti consulting 
 * You may obtain a copy of the License at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-Aug 25, 2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.salesmanager.catalog.product;

import java.io.Serializable;

/**
 * Object used for Ajax requests. This is a simplification of
 * com.salesmanager.core.entity.catalog.ProductAttribute
 * @author Carl Samson
 *
 */
public class ProductAttribute implements Serializable {

	private String name;
	private String textValue;

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value;
	private boolean stringValue = false;

	public boolean isStringValue() {
		return stringValue;
	}

	public void setStringValue(boolean stringValue) {
		this.stringValue = stringValue;
	}

}
