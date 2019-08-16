package software.jsj.petclinic.bootstrap;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.model.Pet;
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
    
    //Owner - Michael
    Owner michael = new Owner();
    michael.setFirstName("Michael");
    michael.setLastName("Weston");
    michael.setAddress("ichael123 Bickerl St");
    michael.setCity("Miami");
    michael.setTelephone("1231231234");
    
    //Michael Pets
    Pet mikesPet = new Pet();
    mikesPet.setPetType(savedDogPetType);
    mikesPet.setOwner(michael);
    mikesPet.setBirthDate(LocalDate.now());
    mikesPet.setName("Rosco");
    michael.getPets().add(mikesPet);
    
    ownerService.save(michael);
    
    //Owner - Fiona
    Owner fiona = new Owner();
    fiona.setFirstName("Fiona");
    fiona.setLastName("Glenanne");
    fiona.setAddress("123 Bickerl St");
    fiona.setCity("Miami");
    fiona.setTelephone("1231231234");
    
    //Fiona Pets
    Pet fionasCat = new Pet();
    fionasCat.setPetType(savedCatPetType);
    fionasCat.setOwner(fiona);
    fionasCat.setBirthDate(LocalDate.now());
    fionasCat.setName("Meow");
    fiona.getPets().add(fionasCat);
    
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
