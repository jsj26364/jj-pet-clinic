package software.jsj.petclinic.services.map;

import java.util.Set;
import org.springframework.stereotype.Service;
import software.jsj.petclinic.model.Vet;
import software.jsj.petclinic.services.VetService;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

  public Set<Vet> findAll() {
    return super.findAll();
  }
  
  @Override
  public Vet findById(Long id) {
    return super.findById(id);
  }
  
  @Override
  public Vet save(Vet object) {
    return super.save(object);
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
