package hello.dao;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {

    Iterable<Pet> findAll();
    @Override
    <S extends Pet> S save(S s);
    Pet findById(int id);
    void deletePetById(int id);
    Iterable<Pet> findByOwnerFirstNameAndOwnerLastName(String ownerFirstName, String ownerLastName);
}
