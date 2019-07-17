package software.jsj.petclinic.services;

import java.util.Set;
import software.jsj.petclinic.model.Owner;

public interface OwnerService {

  Owner findById(Long id);
  
  Owner findByLastName(String lastName);
  
  Owner save(Owner owner);
  
  Set<Owner> findAll();
}
