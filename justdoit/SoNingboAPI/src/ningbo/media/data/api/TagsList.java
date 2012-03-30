package ningbo.media.data.api;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tags")
public class TagsList {

	private Collection<TagsXml> tags;

	public TagsList() {
	}

	public TagsList(Collection<TagsXml> list, URI uri) {
		for (TagsXml entry : list) {
			entry.setUri(UriBuilder.fromUri(uri).path(entry.getId() + "/")
					.build());
		}
		this.tags = list;
	}

	@XmlElement(name = "tag")
	public Collection<TagsXml> getTags() {
		return tags;
	}
}
