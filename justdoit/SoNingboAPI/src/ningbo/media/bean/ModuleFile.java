package ningbo.media.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_module_file")
public class ModuleFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String fileHash;

	private String fileName;

	private Date uploadTime;
	
	private Date createTime;
	

	@OneToOne
	@JoinColumn(name = "inforId")
	private ImageInformation imageInfo;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_files_user", joinColumns = @JoinColumn(name = "fileId"),inverseJoinColumns=@JoinColumn(name = "userId"))
	private List<SystemUser> systemUsers ;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_files_location", joinColumns = @JoinColumn(name = "fileId"),inverseJoinColumns=@JoinColumn(name = "locationId"))
	private List<Location> loctions ;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public ImageInformation getImageInfo() {
		return imageInfo;
	}

	public void setImageInfo(ImageInformation imageInfo) {
		this.imageInfo = imageInfo;
	}

	public List<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(List<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}

	public List<Location> getLoctions() {
		return loctions;
	}

	public void setLoctions(List<Location> loctions) {
		this.loctions = loctions;
	}
	
	
}
