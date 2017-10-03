package hello;

import hello.entity.Owner;
import hello.entity.Pet;
import hello.entity.Vet;
import hello.security.service.SecurityService;
import hello.service.owner.OwnerService;
import hello.service.pet.PetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pawel on 2017-07-21.
 */

@Controller
public class HelloController {

    private final Log logger = LogFactory.getLog(getClass());

    private PetService petService;
    private OwnerService ownerService;
    private SecurityService securityService;

    @Autowired
    public HelloController(PetService petService, OwnerService ownerService, SecurityService securityService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.securityService = securityService;
    }

    /* main page part */

    @InitBinder
    public void initBinder(WebDataBinder binder) {
            binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        }

    @GetMapping("/")
    public String index() throws  IOException {
        return "index";
    }

    @GetMapping("/login-error")
    public String loginError(Model theModel)  {
        theModel.addAttribute("loginError", true);
        return "index";
    }

    /* registration page part */

    @GetMapping("/registration")
    public String registrationForm(Model theModel) {
        theModel.addAttribute("owner", new Owner());
        return "registrationForm";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute @Valid Owner owner, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        ownerService.saveOwner(owner);
        securityService.autologin(owner.getMail(), owner.getPassword());
        return "owner";
    }

    /* owner page part */

    @GetMapping("/owner")
    public String owner(Model theModel) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Owner user = ownerService.findByMail(auth.getName());
        Iterable<Pet> pets = petService.findByOwnerFirstNameAndOwnerLastName(user.getFirstName(),user.getLastName());
        theModel.addAttribute("pets",pets);
        return "owner";
    }

    /* pet page part */

    @GetMapping("/addPet")
    public String addPet(Model theModel){
        theModel.addAttribute("pet", new Pet());
        return "add_pet";
    }

    @PostMapping("/addPet")
    public String savePet(@ModelAttribute("pet") Pet pet){
        petService.save(pet);
        return "redirect:/vet";
    }

    @PostMapping("/updatePet")
    public String updatePet(@RequestParam(value = "petId") int petId, Model theModel){

        Pet pet = petService.findById(petId);
        theModel.addAttribute("pet", pet);
        return "add_pet";
    }

    @GetMapping("/deletePet")
    public String deletePet(@RequestParam(value = "petId") int theId){
        petService.deletePetById(theId);
        return "redirect:/vet";
    }
}
