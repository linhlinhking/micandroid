package ningbo.media.core.web.bind;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;

import ningbo.media.core.utils.FormatConstants;
import ningbo.media.core.web.bind.editor.CustomStringEditor;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;


public class DefaultBindingInitializer implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {
		DateFormat dateFormat = FormatConstants.DATE_TIME_FORMAT;
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Integer.class,new CustomNumberEditor(Integer.class,true));
		binder.registerCustomEditor(Long.class,new CustomNumberEditor(Long.class,true));
		binder.registerCustomEditor(String.class,new CustomStringEditor(String.class));
		binder.registerCustomEditor(Collection.class, new CustomCollectionEditor(Collection.class, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
	}

}
