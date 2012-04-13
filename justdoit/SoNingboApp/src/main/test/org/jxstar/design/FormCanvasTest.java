package org.jxstar.design;


import org.jxstar.fun.design.FormCanvasBO;
import org.jxstar.test.AbstractTest;

public class FormCanvasTest extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FormCanvasBO form  = new FormCanvasBO();
		form.readDesign("work_mal_app", "2");
	}

}
