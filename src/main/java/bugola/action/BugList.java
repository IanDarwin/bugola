package bugola.action;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import bugola.model.Bug;

/**
 * Apache DeltaSpike Data "List Object"
 * @author Ian Darwin
 */
@SessionScoped
@Named("bugList")
@Repository
public interface BugList extends EntityRepository<Bug, Long>{

	@Query("SELECT b FROM Bug b ORDER BY b.creationDate DESC")
	List<Bug> findAll();
}
