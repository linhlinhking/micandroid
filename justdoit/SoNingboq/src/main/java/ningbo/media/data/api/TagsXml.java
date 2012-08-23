package ningbo.media.data.api;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ningbo.media.data.entity.TagsEntity;


@XmlType(name = "tag")
public class TagsXml {
	private TagsEntity tag ;
	private URI uri ;
	
	public TagsXml(){}
	
	public TagsXml(TagsEntity tag){
		this.tag = tag ;
	}
	
	@XmlAttribute
	public Integer getId(){
		return tag.getId() ;
	}
	
	@XmlElement
	public String getValue(){
		return tag.getName() ;
	}

	@XmlAttribute
	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	
}
