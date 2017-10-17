package hello.service.pet;

import hello.dao.PetRepository;
import hello.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class PetServiceImpl implements PetService {

    private PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
    public Iterable<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet findById(int id) {
        return petRepository.findById(id);
    }

    @Override
    @Transactional
    public void deletePetById(int id) {
        petRepository.deletePetById(id);
    }

    @Override
    public Iterable<Pet> findByOwnerFirstNameAndOwnerLastName(String ownerFirstName, String ownerLastName) {
        return petRepository.findByOwnerFirstNameAndOwnerLastName(ownerFirstName, ownerLastName);
    }

}
