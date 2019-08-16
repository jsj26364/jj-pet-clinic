package software.jsj.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.model.PetType;
import software.jsj.petclinic.model.Vet;
import software.jsj.petclinic.services.OwnerService;
import software.jsj.petclinic.services.PetTypeService;
import software.jsj.petclinic.services.VetService;

@Component
public class DataInitializer implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  
  public DataInitializer(OwnerService ownerService, VetService vetService,
      PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(String... args) throws Exception {
    
    //Pet types
    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);
    
    PetType cat = new PetType();
    dog.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);
    
    //Owners
    Owner michael = new Owner();
    michael.setFirstName("Michael");
    michael.setLastName("Weston");
    ownerService.save(michael);
    
    Owner fiona = new Owner();
    fiona.setFirstName("Fiona");
    fiona.setLastName("Glenanne");
    ownerService.save(fiona);
    
    System.out.println("Loaded Owners ...");
    
    //Vets
    Vet sam = new Vet();
    sam.setFirstName("Sam");
    sam.setLastName("Axe");
    vetService.save(sam);
    
    Vet jessie = new Vet();
    jessie.setFirstName("Jessie");
    jessie.setLastName("Porter");
    vetService.save(jessie);
    
    Vet john = new Vet();
    john.setFirstName("John");
    john.setLastName("Barnes");
    vetService.save(john);
    
    System.out.println("Loaded Vets ...");
    
  }

}
