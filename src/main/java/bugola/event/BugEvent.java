package bugola.event;

import java.util.EventObject;

import bugola.model.Bug;
import bugola.model.BugStatus;

public class BugEvent extends EventObject {

	private static final long serialVersionUID = 4224017399542918433L;

	private BugStatus status;

	private Bug bug;

	BugEvent(Object source, Bug bug, BugStatus status) {
		super(source);
		this.bug = bug;
		this.status = status;
	}

	public BugStatus getStatus() {
		return status;
	}

	public Bug getBug() {
		return bug;
	}
}
