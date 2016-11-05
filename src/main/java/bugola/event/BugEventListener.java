package bugola.event;

public interface BugEventListener {

	public void bugAdded(BugEvent e);

	public void bugStateChanged(BugEvent e);
}
