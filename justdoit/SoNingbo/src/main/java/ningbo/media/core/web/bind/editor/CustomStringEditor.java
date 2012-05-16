package ningbo.media.core.web.bind.editor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class CustomStringEditor extends PropertyEditorSupport implements
		PropertyEditor {

	public CustomStringEditor(Class<String> clazz) {
		super(clazz);
	}

	@Override
	public void setAsText(String str) throws IllegalArgumentException {
		if (str == null || str.length() <= 0) {
			super.setValue(null);
		} else {
			super.setValue(str);
		}
	}

	@Override
	public String getAsText() {
		return (String) getValue();
	}
}
