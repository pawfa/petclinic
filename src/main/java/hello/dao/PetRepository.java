package hello.dao;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {

    Iterable<Pet> findAll();
    Iterable<Pet> findPetsByOwner(String id);

}
