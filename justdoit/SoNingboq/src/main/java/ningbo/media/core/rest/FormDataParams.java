package ningbo.media.core.rest;



import javax.ws.rs.core.MediaType;

import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.MultiPart;

public class FormDataParams extends MultiPart{

	
	  /**
     * Instantiate a new {@link FormDataMultiPart} instance with
     * default characteristics.
     */
    public FormDataParams() {
    	super(MediaType.MULTIPART_FORM_DATA_TYPE);
    }
	
	   /**
     * Get a form data body part given a control name.
     * <p>
     * 
     * @param name the control name.
     * @return the form data body part, otherwise null if no part
     *         is present with the given control name. If more that one
     *         part is present with the same control name, then the first
     *         part that occurs is returned.
     */
    public FormDataBodyPart getField(String name) {
        FormDataBodyPart result = null;
        for (BodyPart bodyPart : getBodyParts()) {
            if (!(bodyPart instanceof FormDataBodyPart)) {
                continue;
            }
            if (name.equals(((FormDataBodyPart) bodyPart).getName())) {
                result = (FormDataBodyPart) bodyPart;
            }
        }
        return result;
    }
}
