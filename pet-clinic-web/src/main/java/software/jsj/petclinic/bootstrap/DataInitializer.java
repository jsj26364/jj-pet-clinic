package software.jsj.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.model.Vet;
import software.jsj.petclinic.services.OwnerService;
import software.jsj.petclinic.services.VetService;

@Component
public class DataInitializer implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  
  public DataInitializer(OwnerService ownerService, VetService vetService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
  }

  @Override
  public void run(String... args) throws Exception {
    
    Owner michael = new Owner();
    michael.setFirstName("Michael");
    michael.setLastName("Weston");
    ownerService.save(michael);
    
    Owner fiona = new Owner();
    fiona.setFirstName("Fiona");
    fiona.setLastName("Glenanne");
    ownerService.save(fiona);
    
    System.out.println("Loaded Owners ...");
    
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
