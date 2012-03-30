package ningbo.media.data.entity;

import ningbo.media.data.Formatter;
import ningbo.media.exception.DataFormatException;

public class TagsEntity implements EntityData {

	private Integer id;
	private String name;


	public TagsEntity() {
	}

	public TagsEntity(Integer id, String name) throws DataFormatException {
		setId(id);
		setName(name);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 * @throws DataFormatException
	 */
	public void setId(Integer id) throws DataFormatException {
		this.id = Formatter.checkId(id);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @throws DataFormatException
	 */
	public void setName(String name) throws DataFormatException {
		if (name == null)
			throw new DataFormatException("Tag name should not be null.");
		this.name = Formatter.removeSpace(name);
	}

}
