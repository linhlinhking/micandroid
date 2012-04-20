package a.simple.ui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * @author junhong.c
 *
 */
public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 3054078831412440478L;

	JTextField field = new JTextField("                                    ");
	/**
	 * 
	 */
	public StatusPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		field.setEditable(false);
		add(field);
	}

	/**
	 * @param time
	 */
	public void setCostTimeMillis(long time) {
		field.setText("搜索耗时 : "+time+" 毫秒");
	}
	
}