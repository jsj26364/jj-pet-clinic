package software.jsj.petclinic.services.map;

import java.util.Set;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.services.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

  public Set<Owner> findAll() {
    return super.findAll();
  }
  
  @Override
  public Owner findById(Long id) {
    return super.findById(id);
  }
  
  @Override
  public Owner save(Owner object) {
    return super.save(object.getId(), object);
  }
  
  @Override
  public void delete(Owner object) {
    super.delete(object);
    
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public Owner findByLastName(String lastName) {
    return this.findByLastName(lastName);
  }
  
}
