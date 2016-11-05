package bugola.model;

/**
 * @author Ian Darwin
 */
public enum BugType {
	BUG("B"), 
	RFE("R"),
	TODO("T"),
	IDEA("I");
	
	BugType(String name) {
		this.name = name;
	}
	String name;
}
