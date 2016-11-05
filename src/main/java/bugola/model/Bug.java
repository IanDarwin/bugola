package bugola.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import model.Person;

/**
 * This represents one bug in the database.
 * @author Ian Darwin, http://darwinsys.com/
 */
@Entity
public class Bug implements Serializable {

	private static final long serialVersionUID = -4177047724541273562L;
	Long id;
	String title;
	String description;
	String url;
	Date creationDate;
	Date modificationdate;
	BugType type;
	BugStatus status;
	Priority priority;
	Product product;
	Platform platform;
	Person submittor;
    boolean approved = false;
	String fixRelease;
	String comments;
    
    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
	public Date getModificationDate() {
		return modificationdate;
	}
	public void setModificationDate(Date modificationdate) {
		this.modificationdate = modificationdate;
	}
	@PrePersist
	public void syncDateNew() {
		final Date d = new Date();
		setCreationDate(d);
		setModificationDate(d);
	}
	@PreUpdate
	public void syncDateUpdate() {
		setModificationDate(new Date());
	}
	
	@Enumerated(EnumType.STRING) 
	public BugType getType() {
		return type;
	}
	public void setType(BugType type) {
		this.type = type;
	}
	@Enumerated(EnumType.ORDINAL) 
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Enumerated(EnumType.STRING) 
	public BugStatus getStatus() {
		return status;
	}
	public void setStatus(BugStatus status) {
		this.status = status;
	}
	@ManyToOne
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		System.out.println("Bug.setProduct(): " + product);
		this.product = product;
	}
	@Enumerated(EnumType.STRING) 
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	@ManyToOne
	public Person getSubmittor() {
		return submittor;
	}
	public void setSubmittor(Person submittor) {
		this.submittor = submittor;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	@Transient
	public boolean isClosed() {
		return	status == BugStatus.CLOSED_REPAIRED ||
				status == BugStatus.CLOSED_INVALID ||
				status == BugStatus.CLOSED_DUP;
	}

	public String getFixRelease() {
		return fixRelease;
	}
	public void setFixRelease(String fixRelease) {
		this.fixRelease = fixRelease;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return getType() + ": " + getTitle();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bug other = (Bug) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
