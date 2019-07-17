package software.jsj.petclinic.services;

import java.util.Set;
import software.jsj.petclinic.model.Pet;

public interface PetService {

  Pet findById(Long id);
  
  Pet save(Pet pet);
  
  Set<Pet> findAll();
  
}
