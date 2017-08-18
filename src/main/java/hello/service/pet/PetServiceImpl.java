package hello.service.pet;

import hello.dao.PetRepository;
import hello.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Iterable<Pet> findPetsByOwner(String id) {
        return petRepository.findPetsByOwner(id);
    }
}
