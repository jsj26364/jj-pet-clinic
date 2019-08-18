package software.jsj.petclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.repositories.OwnerRepository;
import software.jsj.petclinic.repositories.PetRepository;
import software.jsj.petclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
  
  static final String FIRST_NAME = "Steven";
  static final String LAST_NAME = "Smith";
  static final String TELEPHONE = "123456";
  static final String ADDRESS = "123 Main St";
  static final String CITY = "Big City";
  
  @Mock
  OwnerRepository mockOwnerRepository;
  
  @Mock
  PetRepository mockPetRepository;
  
  @Mock
  PetTypeRepository mockPetTypeRepository;
  
  @InjectMocks
  OwnerSDJpaService service;

  Owner returnOwner;
  
  @BeforeEach
  void setUp() throws Exception {
    returnOwner = Owner.builder()
        .id(1L)
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .address(ADDRESS)
        .telephone(TELEPHONE)
        .city(CITY)
        .build();
  }

  @Test
  void testFindAll() {
    
    //Given
    Owner returnOwner2 = Owner.builder()
        .id(1L)
        .firstName("Owner2Name")
        .lastName("Owner2LastName")
        .address("Owner2Address")
        .telephone("654321")
        .city("Owner2City")
        .build();
    
    Set<Owner> returnOwnerSet = new HashSet<>();
    returnOwnerSet.add(returnOwner);
    returnOwnerSet.add(returnOwner2);
    
    when(mockOwnerRepository.findAll()).thenReturn(returnOwnerSet);
    
    //When
    Set<Owner> owners = service.findAll();
    
    //Then
    assertNotNull(owners);
    assertEquals(2, owners.size());
    
  }

  @Test
  void testFindById() {
    //Given
    //returnOwner (as defined in setUp())
    
    when(mockOwnerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
    
    //When
    Owner owner = service.findById(1L);
    
    //Then
    assertNotNull(owner);
    assertEquals(FIRST_NAME, owner.getFirstName());
    assertEquals(ADDRESS, owner.getAddress());
    
  }
  
  @Test
  void testFindByIdNotFound() {
    //Given
    //returnOwner (as defined in setUp())
    
    when(mockOwnerRepository.findById(anyLong())).thenReturn(Optional.empty());
    
    //When
    Owner owner = service.findById(0L);
    
    //Then
    assertNull(owner);
    
  }

  @Test
  void testSave() {
    
    //Given
    Owner ownerToSave = Owner.builder().build();
    
    when(mockOwnerRepository.save(any())).thenReturn(returnOwner);
    
    //When
    Owner savedOwner = service.save(ownerToSave);
    
    //Then
    assertNotNull(savedOwner);
    assertEquals(FIRST_NAME, savedOwner.getFirstName());
    
    verify(mockOwnerRepository).save(any());
    //default is 1 times
    verify(mockOwnerRepository, times(1)).save(any());
    
  }

  @Test
  void testDelete() {
    //Given returnOwner (as defined in setUp())
    
    //When
    service.delete(returnOwner);
    
    //Then
    verify(mockOwnerRepository).delete(any());

  }

  @Test
  void testDeleteById() {
    //Given returnOwner (as defined in setUp())
    
    //When
    service.deleteById(1L);
    
    //Then
    verify(mockOwnerRepository).deleteById(anyLong());
    
  }
  
  @Test
  void testFindByLastName() {
    
    //Given returnOwner (as defined in setUp())
    when(mockOwnerRepository.findByLastName(any())).thenReturn(returnOwner);
    
    //When
    Owner smith = service.findByLastName(LAST_NAME);
    
    //Then
    assertEquals(FIRST_NAME, smith.getFirstName());
    assertEquals(CITY, smith.getCity());
    
    verify(mockOwnerRepository).findByLastName(any());
    verify(mockOwnerRepository, times(1)).findByLastName(any());
    
  }
  
}
