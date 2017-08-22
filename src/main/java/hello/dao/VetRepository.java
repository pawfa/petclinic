package hello.dao;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Pet;
import hello.entity.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {

    Iterable<Vet> findAll();

    @Override
    <S extends Vet> S save(S s);

    Vet getById(int id);

    void deleteVetById(int id);
}
