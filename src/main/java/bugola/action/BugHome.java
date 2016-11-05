package bugola.action;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import bugola.model.Bug;

/**
 * Implements Gateway, an Adam Bien pattern whose purpose is to expose
 * an Entity (and its relations) to the Client/Web tier, rather like a Seam2 "Home Object"
 * @author Ian Darwin
 */
@Stateful @Named @ConversationScoped
public class BugHome implements Serializable {

	private static final long serialVersionUID = -2284578724132631798L;

	private static final String LIST_PAGE = "BugList";
	private static final String FORCE_REDIRECT = "?faces-redirect=true";

	@Inject Conversation conv;

	// Must be Long (not long) so we can check for null
	private Long id;
	private Bug instance;

	@PersistenceContext(type=PersistenceContextType.EXTENDED) EntityManager em;

	public BugHome() {
		System.out.println("BugHome.BugHome()");
	}

	public void wire() {
		if (conv.isTransient()) {
			conv.begin();
		}
		System.out.println("Wire(): " + id);
		if (id == null) {
			instance = new Bug();
			return;
		}
		instance = em.find(Bug.class, id);
		if (instance == null) {
			throw new IllegalArgumentException("Bug not found by id! " + id);
		}
	}
	
	public void wire(Long id) {
		System.out.println("BugHome.wire(" + id + ")");
		setId(id);
		wire();
	}
	
	public boolean isWired() {
		return instance != null;
	}
	
	public boolean isManaged() {
		return instance != null && instance.getId() != null;
	}

	/** Save/update an existing object */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String update() {
		System.out.println("BugHome.update()");
		em.merge(instance);
		return LIST_PAGE + FORCE_REDIRECT;
	}

	/** Save a new object */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String save() {
		System.out.println("BugHome.save()");
		em.persist(instance);
		conv.end();
		return LIST_PAGE + FORCE_REDIRECT;
	}

	/** Create a new instance, store it in "instance" */
	public void newInstance() {
		System.out.println("BugHome.newInstance()");
		instance = new Bug();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		System.out.println("BugHome.setId(" + id + ")");
		this.id = id;
	}
	
	public Bug getInstance() {
		System.out.println("BugHome.getInstance(): " + instance);
		return instance;
	}
	public void setInstance(Bug instance) {
		this.instance = instance;
		if (instance != null)
			this.id = instance.getId();
	}
	
	/** Close an editing operation: just end conversation, return List page.
	 * @return The List Page
	 */
	public String cancel() {
		System.out.println("BugHome.cancel()");
		instance = null;
		conv.end();
		return LIST_PAGE + FORCE_REDIRECT;
	}
	
	/** Like Cancel but for e.g., View page, no conv end.
	 * @return The List Page
	 */
	public String done() {
		instance = null;
		return LIST_PAGE + FORCE_REDIRECT;
	}

	public String remove() {
		System.out.println("BugHome.remove()");
		em.remove(instance);
		conv.end();
		instance = null;
		return LIST_PAGE + FORCE_REDIRECT;
	}

	@PreDestroy
	public void bfn() {
		System.out.println("BugHome.bfn()");
	}
}
