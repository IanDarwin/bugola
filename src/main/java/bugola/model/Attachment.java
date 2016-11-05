package bugola.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents an uploaded file.
 */
@Entity
public class Attachment {
	long id;
	int bugId;
	String fileName;

	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getBugId() {
		return bugId;
	}
	public void setBugId(int bugId) {
		this.bugId = bugId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
