/**
 * 
 */
package software.jsj.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import software.jsj.petclinic.model.PetType;

/**
 * @author jsjackson
 *
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
