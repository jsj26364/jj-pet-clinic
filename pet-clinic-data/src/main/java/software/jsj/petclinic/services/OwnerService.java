package software.jsj.petclinic.services;

import java.util.List;
import software.jsj.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);
  
   List<Owner> findAllByLastNameLike(String lastN);
}
