/**
 * 
 */
package software.jsj.petclinic.controllers;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.util.StringUtils;
import software.jsj.petclinic.model.Owner;
import software.jsj.petclinic.model.Pet;
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
  
  @GetMapping("/pets/new")
  public String initCreationForm(Owner owner, Model model) {
      Pet pet = Pet.builder().build();
      owner.getPets().add(pet);
      pet.setOwner(owner);
      model.addAttribute("pet", pet);
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }
  
  @PostMapping("/pets/new")
  public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
      if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
          result.rejectValue("name", "duplicate", "already exists");
      }
      owner.getPets().add(pet);
      if (result.hasErrors()) {
          model.addAttribute("pet", pet);
          return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
      } else {
        //TODO: review this logic. Pet may not have been successfully saved. savedPet is returning null
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
        /*Pet savedPet = petService.save(pet);
        return "redirect:/owners/" + savedPet.getOwner().getId();*/
      }
  }
  
  @GetMapping("/pets/{petId}/edit")
  public String initUpdateForm(@PathVariable Long petId, Model model) {
      model.addAttribute("pet", petService.findById(petId));
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("/pets/{petId}/edit")
  public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
      if (result.hasErrors()) {
          pet.setOwner(owner);
          model.addAttribute("pet", pet);
          return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
      } else {
          owner.getPets().add(pet);
          petService.save(pet);
          return "redirect:/owners/" + owner.getId();
      }
  }
  

}
