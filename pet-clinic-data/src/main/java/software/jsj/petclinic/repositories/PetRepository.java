/**
 * 
 */
package software.jsj.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import software.jsj.petclinic.model.Pet;

/**
 * @author jsjackson
 *
 */
public interface PetRepository extends CrudRepository<Pet, Long> {

}
