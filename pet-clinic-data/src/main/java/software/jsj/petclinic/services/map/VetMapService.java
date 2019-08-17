package software.jsj.petclinic.services.map;

import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.jsj.petclinic.model.Specialty;
import software.jsj.petclinic.model.Vet;
import software.jsj.petclinic.services.SpecialtyService;
import software.jsj.petclinic.services.VetService;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

  private final SpecialtyService specialtyService;
  
  public VetMapService(SpecialtyService specialtyService) {
    this.specialtyService = specialtyService;
  }
  
  public Set<Vet> findAll() {
    return super.findAll();
  }
  
  @Override
  public Vet findById(Long id) {
    return super.findById(id);
  }
  
  @Override
  public Vet save(Vet object) {
    
    if (object.getSpecialties().size() > 0) {
      object.getSpecialties().forEach(specialty -> {
        if (specialty.getId() == null) {
          Specialty savedSpecialty = specialtyService.save(specialty);
          specialty.setId(savedSpecialty.getId()); // defensive coding
        }
      });
      
    }
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
