package hello;

import hello.dao.OwnerRepository;
import hello.dao.PetRepository;
import hello.dao.VetRepository;
import hello.entity.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pawel on 2017-07-21.
 */

@Controller
@RequestMapping("/")
public class HelloController {


    private VetRepository vetRepository;
    private PetRepository petRepository;
    private OwnerRepository ownerRepository;

    @Autowired
    public HelloController(VetRepository vetRepository, PetRepository petRepository, OwnerRepository ownerRepository) {
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    public String index() {
        return "index";
    }

    @GetMapping("/vet")
    public String vet(Model theModel){

        Map<String,Iterable<?>> allData = new HashMap<>();

        allData.put("vets", vetRepository.findAll());
        allData.put("owners", ownerRepository.findAll());
        allData.put("pets", petRepository.findAll());

        theModel.addAttribute("allData",allData);

        return "vet";
    }

    @GetMapping("/addVet")
    public String addVet(Model theModel){

        Vet vet = new Vet("1","Pawcio", "paw","elo");
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
