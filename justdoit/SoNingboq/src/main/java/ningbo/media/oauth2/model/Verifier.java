package ningbo.media.oauth2.model;

import ningbo.media.oauth2.utils.Preconditions;

public class Verifier {
	
	private final String value;

	/**
	 * Default constructor.
	 * 
	 * @param value
	 *            verifier value
	 */
	public Verifier(String value) {
		Preconditions.checkNotNull(value,
				"Must provide a valid string as verifier");
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
