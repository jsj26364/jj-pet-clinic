package software.jsj.petclinic.services;

import java.util.Set;
import software.jsj.petclinic.model.Vet;

public interface VetService {

  Vet findById(Long id);
  
  Vet save(Vet pet);
  
  Set<Vet> findAll();
  
}
