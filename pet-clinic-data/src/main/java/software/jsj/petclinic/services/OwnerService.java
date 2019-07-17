package software.jsj.petclinic.services;

import software.jsj.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);
  
}
