/**
 * 
 */
package software.jsj.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import software.jsj.petclinic.model.Owner;

/**
 * @author jsjackson
 *
 */
class OwnerMapServiceTest {

  OwnerMapService ownerMapService;
  
  final Long ownerId = 1L;
  final String lastName = "Smith";
  
  @BeforeEach
  void setUp() throws Exception {
    ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
    
    //add an owner to the db
    ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
  }

  @Test
  void testFindAll() {
    Set<Owner> ownerSet = ownerMapService.findAll();

    assertEquals(1, ownerSet.size());
  }

  @Test
  void testFindByIdLong() {
    Owner owner = ownerMapService.findById(ownerId);
    
    assertEquals(ownerId, owner.getId());
  }

  @Test
  void testSaveOwnerExistingId() {
    Long id = 2L;
    
    Owner owner = Owner.builder().firstName("Owner").build();
    Owner savedOwner = ownerMapService.save(owner);
    
    assertEquals(id, savedOwner.getId());
    assertEquals("Owner", savedOwner.getFirstName());
  }

  @Test
  void testSaveOwnerNoId() {
   
    Owner savedOwner = ownerMapService.save(Owner.builder().build());
    
    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());
     
  }
  
  @Test
  void testDeleteOwner() {
    Owner owner = ownerMapService.findById(ownerId);
    ownerMapService.delete(owner);
    
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void testDeleteByIdLong() {
    ownerMapService.deleteById(ownerId);
    
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void testFindByLastName() {
    Owner owner = ownerMapService.findByLastName(lastName);
    
    assertNotNull(owner);
    assertEquals(ownerId, owner.getId());
  }
  
  @Test
  void testFindByLastNameNotFound() {
    Owner owner = ownerMapService.findByLastName("foo");
    
    assertNull(owner);
  }
  

}
