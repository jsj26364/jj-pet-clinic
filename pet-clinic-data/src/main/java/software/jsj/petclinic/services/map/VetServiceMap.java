package software.jsj.petclinic.services.map;

import java.util.Set;
import software.jsj.petclinic.model.Vet;
import software.jsj.petclinic.services.CrudService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {

  public Set<Vet> findAll() {
    return super.findAll();
  }
  
  @Override
  public Vet findById(Long id) {
    return super.findById(id);
  }
  
  @Override
  public Vet save(Vet object) {
    return super.save(object.getId(), object);
  }
  
  @Override
  public void delete(Vet object) {
    super.delete(object);
    
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }
  
  
}