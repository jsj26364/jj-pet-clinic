/**
 * 
 */
package software.jsj.petclinic.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import software.jsj.petclinic.model.Owner;

/**
 * @author jsjackson
 *
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

  Owner findByLastName(String lastName);
  
  List<Owner> findAllByLastNameLike(String lastName);
}
