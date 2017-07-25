package hello;

import hello.entity.Vet;
import hello.service.owner.OwnerService;
import hello.service.pet.PetService;
import hello.service.vet.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    public HelloController(VetService vetService, PetService petService, OwnerService ownerService) {
        this.vetService = vetService;
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/vet")
    public String vet(Model theModel){

        Map<String,Iterable<?>> allData = new HashMap<>();

        allData.put("vets", vetService.findAll());
        allData.put("owners", ownerService.findAll());
        allData.put("pets", petService.findAll());

        theModel.addAttribute("allData",allData);
        return "vet";
    }

    @GetMapping("/addVet")
    public String addVet(Model theModel){

        Vet vet = new Vet("1","Paw", "paw","elo");
        theModel.addAttribute("vet",vet);

        System.out.println(vet.getFirstName());
        return "save_vet";
    }

    @PostMapping("/saveVet")
    public String saveVet(@ModelAttribute("vet") Vet vet, Model theModel){

//        vetService.addVet(vet);
        return "redirect:/";
    }

}
