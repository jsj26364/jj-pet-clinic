/**
 * 
 */
package software.jsj.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import software.jsj.petclinic.model.Specialty;

/**
 * @author jsjackson
 *
 */
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

}
