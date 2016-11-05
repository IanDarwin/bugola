package bugola.model;

/**
 * The states a bug can be in.
 * @author ian
 */
public enum BugStatus {
	NEW,
	ACCEPT_FUTURE,
	ASSIGNED,
	CLOSED_DUP,
	CLOSED_INVALID,
	CLOSED_REPAIRED;
}
