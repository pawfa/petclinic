package hello;

import hello.entity.Owner;
import hello.entity.Pet;
import hello.security.validation.UserExistsException;
import hello.service.owner.OwnerService;
import hello.service.pet.PetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by Pawel on 2017-07-21.
 */

@Controller
public class HelloController {

    private final Log logger = LogFactory.getLog(getClass());

    private PetService petService;
    private OwnerService ownerService;

    @Autowired
    public HelloController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    /* main page part */

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/error")
    public String error() throws IOException {
        return "index";
    }

    @GetMapping("/")
    public String index() throws IOException {
        return "index";
    }

    @GetMapping("/?error")
    public String loginError() {
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
        try {
            ownerService.saveOwner(owner);
        } catch (UserExistsException e) {
            ObjectError er = new ObjectError("mailExists", "User already exists in database");
            bindingResult.addError(er);
            logger.error(e);
            return "registrationForm";
        }

        return "owner";
    }

    /* owner page part */

    @GetMapping("/owner")
    public String owner(Model theModel) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Owner user = ownerService.findByMail(auth.getName());
        Iterable<Pet> pets = petService.findByOwnerFirstNameAndOwnerLastName(user.getFirstName(), user.getLastName());
        theModel.addAttribute("pets", pets);
        return "owner";
    }

    /* pet page part */

    @GetMapping("/addPet")
    public String addPet(Model theModel) {
        theModel.addAttribute("pet", new Pet());
        return "add_pet";
    }

    @PostMapping("/addPet")
    public String savePet(@ModelAttribute @Valid Pet pet, BindingResult bindingResult) {
        System.out.println(pet.getDataIn());
        if (bindingResult.hasErrors()) {
            System.out.println("tutaj dziala");
            return "add_pet";
        }
        try {
            petService.save(pet);
        } catch (Exception e) {
            System.out.println(bindingResult.hasErrors());
            return "add_pet";
        }

        return "redirect:/vet";
    }

    @PostMapping("/updatePet")
    public String updatePet(@RequestParam(value = "petId") int petId, Model theModel) {
        Pet pet = petService.findById(petId);
        theModel.addAttribute("pet", pet);
        return "add_pet";
    }

    @GetMapping("/deletePet")
    public String deletePet(@RequestParam(value = "petId") int theId) {
        petService.deletePetById(theId);
        return "redirect:/vet";
    }
}
