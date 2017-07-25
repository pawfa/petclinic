package hello.service.pet;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetService {

    Iterable<Pet> findAll();
}
