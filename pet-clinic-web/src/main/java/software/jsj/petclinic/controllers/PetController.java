/**
 * 
 */
package software.jsj.petclinic.controllers;

import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.model.PetType;
import software.jsj.petclinic.services.OwnerService;
import software.jsj.petclinic.services.PetService;
import software.jsj.petclinic.services.PetTypeService;

/**
 * @author jsjackson
 *
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

  private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
  
  private final PetService petService;
  private final PetTypeService petTypeService;
  private final OwnerService ownerService;
  
  public PetController(PetService petService, PetTypeService petTypeService,
      OwnerService ownerService) {
    this.petService = petService;
    this.petTypeService = petTypeService;
    this.ownerService = ownerService;
  }

  @ModelAttribute("types")
  public Collection<PetType> populatePetTypes() {
      return petTypeService.findAll();
  }

  @ModelAttribute("owner")
  public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
      return ownerService.findById(ownerId);
  }

  @InitBinder("owner")
  public void initOwnerBinder(WebDataBinder dataBinder) {
      dataBinder.setDisallowedFields("id");
  }

}
