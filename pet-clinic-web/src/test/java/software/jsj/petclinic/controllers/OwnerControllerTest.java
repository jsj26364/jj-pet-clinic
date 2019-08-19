package software.jsj.petclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;

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
  
  MockMvc mockMvc;
  
  @BeforeEach
  void setUp() throws Exception {
    
    //initialise a mock mvc environment for the controller
    mockMvc = MockMvcBuilders
        .standaloneSetup(controller)
        .build();
    
    Owner owner1 = Owner.builder()
        .id(ID1)
        .firstName(FIRST_NAME1)
        .lastName(LAST_NAME1)
        .address(ADDRESS1)
        .telephone(TELEPHONE1)
        .city(CITY1)
        .build();
    
    Owner owner2 = Owner.builder()
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
  void shouldReturnOwnersGivenRootUrl() throws Exception {
    
    when(mockOwnerService.findAll()).thenReturn(owners);
    
    mockMvc.perform(get("/owners"))
    .andExpect(status().isOk())
    .andExpect(view().name("owners/index"))
    .andExpect(model().attributeExists("owners"))
    .andExpect(model().attribute("owners", hasSize(2)))
    
    .andReturn();
    
  }
  
  @Test
  void shouldReturnOwnersGivenIndexUrl() throws Exception {
    
    when(mockOwnerService.findAll()).thenReturn(owners);
    
    mockMvc.perform(get("/owners/index"))
    .andExpect(status().isOk())
    .andExpect(view().name("owners/index"))
    .andExpect(model().attributeExists("owners"))
    .andExpect(model().attribute("owners", hasSize(2)))
    
    .andReturn();
    
  }
  
  @Test
  void shouldReturnOwnersGivenIndexHtmlUrl() throws Exception {
    
    when(mockOwnerService.findAll()).thenReturn(owners);
    
    mockMvc.perform(get("/owners/index.html"))
    .andExpect(status().isOk())
    .andExpect(view().name("owners/index"))
    .andExpect(model().attributeExists("owners"))
    .andExpect(model().attribute("owners", hasSize(2)))
    
    .andReturn();
    
  }

  @Test
  void shouldReturnNotImplementedView() throws Exception {
    mockMvc.perform(get("/owners/find"))
    .andExpect(status().isOk())
    .andExpect(view().name("notimplemented"))
    .andReturn();
    
    //verify zero interactions with the owner service mock
    verifyZeroInteractions(mockOwnerService);
  }

}
