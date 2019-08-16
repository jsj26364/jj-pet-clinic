package software.jsj.petclinic.bootstrap;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.model.Pet;
import software.jsj.petclinic.model.PetType;
import software.jsj.petclinic.model.Specialty;
import software.jsj.petclinic.model.Vet;
import software.jsj.petclinic.services.OwnerService;
import software.jsj.petclinic.services.PetTypeService;
import software.jsj.petclinic.services.SpecialtyService;
import software.jsj.petclinic.services.VetService;

@Component
public class DataInitializer implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialtyService specialtyService;
  
  public DataInitializer(OwnerService ownerService, VetService vetService,
      PetTypeService petTypeService, SpecialtyService specialtyService) {
    super();
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
  }

  @Override
  public void run(String... args) throws Exception {
    
    int count = petTypeService.findAll().size();
    if (count == 0) {
      loadData();
    }
    
  }

  /**
   * Load some startup datainto the map objects.
   */
  private void loadData() {
    //Pet types
    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);
    
    PetType cat = new PetType();
    dog.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);
    
    
    //Specialties
    Specialty radiology = new Specialty();
    radiology.setDescription("Radiology");
    Specialty savedRadiology = specialtyService.save(radiology);
    
    Specialty surgery = new Specialty();
    surgery.setDescription("Surgery");
    Specialty savedSurgery = specialtyService.save(surgery);
    
    Specialty dentistry = new Specialty();
    dentistry.setDescription("Dentistry");
    Specialty savedDentistry = specialtyService.save(dentistry);
    
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
    sam.getSpecialties().add(savedRadiology);
    vetService.save(sam);
    
    Vet jessie = new Vet();
    jessie.setFirstName("Jessie");
    jessie.setLastName("Porter");
    jessie.getSpecialties().add(savedSurgery);
    vetService.save(jessie);
    
    Vet john = new Vet();
    john.setFirstName("John");
    john.setLastName("Barnes");
    john.getSpecialties().add(savedDentistry);
    vetService.save(john);
    
    System.out.println("Loaded Vets ...");
  }

}
