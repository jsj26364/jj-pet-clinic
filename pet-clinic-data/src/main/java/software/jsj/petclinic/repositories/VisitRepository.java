/**
 * 
 */
package software.jsj.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import software.jsj.petclinic.model.Visit;

/**
 * @author jsjackson
 *
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {

}
