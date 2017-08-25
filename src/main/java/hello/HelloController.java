package hello;

import hello.entity.Owner;
import hello.entity.Pet;
import hello.entity.Vet;
import hello.security.service.SecurityService;
import hello.service.owner.OwnerService;
import hello.service.pet.PetService;
import hello.service.vet.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pawel on 2017-07-21.
 */

@Controller
public class HelloController {

    private VetService vetService;
    private PetService petService;
    private OwnerService ownerService;
    private SecurityService securityService;

    @Autowired
    public HelloController(VetService vetService, PetService petService, OwnerService ownerService, SecurityService securityService) {
        this.vetService = vetService;
        this.petService = petService;
        this.ownerService = ownerService;
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String registrationForm(Model theModel) {
        theModel.addAttribute("owner", new Owner());
        return "registrationForm";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute Owner owner) {
        ownerService.saveOwner(owner);
//        securityService.autologin(owner.getMail(), owner.getPassword());
        return "owner";
    }

    @GetMapping("/vet")
    public String vet(Model theModel){

        Map<String,Iterable<?>> allData = new HashMap<>();

        allData.put("vets", vetService.findAll());
        allData.put("owners", ownerService.findAll());
        allData.put("pets", petService.findAll());
        theModel.addAttribute("allData",allData);

        theModel.addAttribute("vet", new Vet());
        theModel.addAttribute("owner2", ownerService.findByMail("u"));

        return "vet";
    }

    @GetMapping("/addVet")
    public String addVet(Model theModel){
        theModel.addAttribute("vet", new Vet());
        return "add_vet";
    }

    @PostMapping("/addVet")
    public String saveVet(@ModelAttribute("vet") Vet vet){
        vetService.save(vet);
        return "redirect:/vet";
    }

    @PostMapping("/updateVet")
    public String updateVet(@RequestParam(value = "vetId") int theId, Model theModel){
        Vet vet = vetService.getById(theId);
        theModel.addAttribute("vet", vet);
        return "add_vet";
    }

    @GetMapping("/deleteVet")
    public String deleteVet(@RequestParam(value = "vetId") int theId){
        vetService.deleteVetById(theId);
        return "redirect:/vet";
    }
    @GetMapping("/owner")
    public String owner() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Owner user = ownerService.findByMail(auth.getName());
        System.out.println(user.getFirstName());
        return "owner";
    }

}
