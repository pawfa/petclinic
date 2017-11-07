package hello;

import hello.entity.Vet;
import hello.service.owner.OwnerService;
import hello.service.pet.PetService;
import hello.service.vet.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class VetController {

    private VetService vetService;
    private PetService petService;
    private OwnerService ownerService;

    @Autowired
    public VetController(VetService vetService, PetService petService, OwnerService ownerService) {
        this.vetService = vetService;
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @GetMapping("/vet")
    public String vet(Model theModel){

        Map<String,Iterable<?>> allData = new HashMap<>();
        allData.put("vets", vetService.findAll());
        allData.put("owners", ownerService.findAll());
        allData.put("pets", petService.findAll());
        theModel.addAttribute("allData",allData);
        theModel.addAttribute("vet", new Vet());
        return "vet";
    }

    @GetMapping("/addVet")
    public String addVet(Model theModel){
        theModel.addAttribute("vet", new Vet());
        return "add_vet";
    }

    @PostMapping("/addVet")
    public String saveVet(@ModelAttribute("vet") @Valid Vet vet, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "add_vet";
        }
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
}
