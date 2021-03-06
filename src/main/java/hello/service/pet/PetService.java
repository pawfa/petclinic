package hello.service.pet;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetService {

    Iterable<Pet> findAll();
    Pet save (Pet pet);
    Pet findById(int id);
    void deletePetById(int id);
    Iterable<Pet> findByOwnerFirstNameAndOwnerLastName(String ownerFirstName, String ownerLastName);
}
