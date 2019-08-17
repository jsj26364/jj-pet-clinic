/**
 * 
 */
package software.jsj.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import software.jsj.petclinic.model.Vet;

/**
 * @author jsjackson
 *
 */
public interface VetRepository extends CrudRepository<Vet, Long> {

}
