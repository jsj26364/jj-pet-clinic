package software.jsj.petclinic.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
  
  static final Long ID1 = 1L;
  static final String FIRST_NAME1 = "Steven";
  static final String LAST_NAME1 = "Smith";
  static final String TELEPHONE1 = "123456";
  static final String ADDRESS1 = "123 Main St";
  static final String CITY1 = "Big City";
  
  static final Long ID2 = 2L;
  static final String FIRST_NAME2 = "Jack";
  static final String LAST_NAME2 = "Bauer";
  static final String TELEPHONE2 = "654321";
  static final String ADDRESS2 = "321 North Avenue";
  static final String CITY2 = "Small City";
  
  
  @Mock
  OwnerService mockOwnerService;
  
  @Mock
  Model mockModel;
  
  //MockitoAnnotations.initMocks(this) in setUp method does the same thing
  @InjectMocks  
  OwnerController controller;
  
  Set<Owner> owners;
  Owner owner1;
  Owner owner2;
  
  MockMvc mockMvc;
  
  @BeforeEach
  void setUp() throws Exception {
    
    //initialise a mock mvc environment for the controller
    mockMvc = MockMvcBuilders
        .standaloneSetup(controller)
        .build();
    
    owner1 = Owner.builder()
        .id(ID1)
        .firstName(FIRST_NAME1)
        .lastName(LAST_NAME1)
        .address(ADDRESS1)
        .telephone(TELEPHONE1)
        .city(CITY1)
        .build();
    
    owner2 = Owner.builder()
        .id(ID1)
        .firstName(FIRST_NAME2)
        .lastName(LAST_NAME2)
        .address(ADDRESS2)
        .telephone(TELEPHONE2)
        .city(CITY2)
        .build();
    
    owners = new HashSet<>();
    owners.add(owner1);
    owners.add(owner2);
    
  }

  @Test
  void findOwners() throws Exception {
    
    mockMvc.perform(get("/owners/find"))
    .andExpect(status().isOk())
    .andExpect(view().name("owners/findOwners"))
    .andExpect(model().attributeExists("owner"))
    .andReturn();
    
    //verify zero interactions with the owner service mock
    verifyZeroInteractions(mockOwnerService);
  }
  
  @Test
  void processFormReturnMany() throws Exception {
    List<Owner> ownerList = new ArrayList<>(owners);
    
    when(mockOwnerService.findAllByLastNameLike(anyString())).thenReturn(ownerList);
    
    mockMvc.perform(get("/owners"))
    .andExpect(status().isOk())
    .andExpect(view().name("owners/ownersList"))
    .andExpect(model().attribute("selections", hasSize(2)))
    .andReturn();
    
    //verify zero interactions with the owner service mock
    verifyZeroInteractions(mockOwnerService);
  }
  
  @Test
  void processFormReturnOne() throws Exception {
    List<Owner> ownerList = new ArrayList<>();
    ownerList.add(owner1);
    
    when(mockOwnerService.findAllByLastNameLike(anyString())).thenReturn(ownerList);
    
    mockMvc.perform(get("/owners"))
    .andExpect(status().is3xxRedirection())
    .andExpect(view().name("redirect:/owners/1"))
    .andReturn();
    
    //verify zero interactions with the owner service mock
    verifyZeroInteractions(mockOwnerService);
  }
  
  @Test
  void displayOwner() throws Exception {
    
    when(mockOwnerService.findById(anyLong())).thenReturn(owner1);
    mockMvc.perform(get("/owners/1"))
    .andExpect(status().isOk())
    .andExpect(view().name("/owners/ownerDetails"))
    .andExpect(model().attribute("owner", hasProperty("id", is(1L))))
    .andReturn();
    
  }
  
  @Test
  void initCreationForm() throws Exception {
      mockMvc.perform(get("/owners/new"))
              .andExpect(status().isOk())
              .andExpect(view().name("owners/createOrUpdateOwnerForm"))
              .andExpect(model().attributeExists("owner"));

      verifyZeroInteractions(mockOwnerService);
  }
  
  @Test
  void processCreationForm() throws Exception {
      when(mockOwnerService.save(ArgumentMatchers.any())).thenReturn(owner1);

      mockMvc.perform(post("/owners/new"))
              .andExpect(status().is3xxRedirection())
              .andExpect(view().name("redirect:/owners/1"))
              .andExpect(model().attributeExists("owner"));

      verify(mockOwnerService).save(ArgumentMatchers.any());
  }

  @Test
  void initUpdateOwnerForm() throws Exception {
      when(mockOwnerService.findById(anyLong())).thenReturn(owner1);

      mockMvc.perform(get("/owners/1/edit"))
              .andExpect(status().isOk())
              .andExpect(view().name("owners/createOrUpdateOwnerForm"))
              .andExpect(model().attributeExists("owner"));

      verifyZeroInteractions(mockOwnerService);
  }

  @Test
  void processUpdateOwnerForm() throws Exception {
      when(mockOwnerService.save(ArgumentMatchers.any())).thenReturn(owner1);

      mockMvc.perform(post("/owners/1/edit"))
              .andExpect(status().is3xxRedirection())
              .andExpect(view().name("redirect:/owners/1"))
              .andExpect(model().attributeExists("owner"));

      verify(mockOwnerService).save(ArgumentMatchers.any());
  }
  
}
