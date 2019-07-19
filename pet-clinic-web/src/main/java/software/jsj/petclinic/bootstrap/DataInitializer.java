package software.jsj.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.model.Vet;
import software.jsj.petclinic.services.OwnerService;
import software.jsj.petclinic.services.VetService;
import software.jsj.petclinic.services.map.OwnerServiceMap;
import software.jsj.petclinic.services.map.VetServiceMap;

@Component
public class DataInitializer implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  
  public DataInitializer() {
    ownerService = new OwnerServiceMap();
    vetService = new VetServiceMap();
  }

  @Override
  public void run(String... args) throws Exception {
    
    Owner michael = new Owner();
    michael.setId(1L);
    michael.setFirstName("Michael");
    michael.setLastName("Weston");
    ownerService.save(michael);
    
    Owner fiona = new Owner();
    fiona.setId(1L);
    fiona.setFirstName("Fiona");
    fiona.setLastName("Glenanne");
    ownerService.save(fiona);
    
    System.out.println("Loaded Owners ...");
    
    Vet sam = new Vet();
    sam.setId(1L);
    sam.setFirstName("Sam");
    sam.setLastName("Axe");
    vetService.save(sam);
    
    Vet jessie = new Vet();
    jessie.setId(2L);
    jessie.setFirstName("Jessie");
    jessie.setLastName("Porter");
    vetService.save(jessie);
    
    System.out.println("Loaded Vets ...");
    
  }

}
