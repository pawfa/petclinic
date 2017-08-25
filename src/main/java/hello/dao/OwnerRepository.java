package hello.dao;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Iterable<Owner> findAll();
    @Override
    <S extends Owner> S save(S s);
    Owner findByMail(String mail);
}
