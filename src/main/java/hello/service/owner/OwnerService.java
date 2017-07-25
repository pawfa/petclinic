package hello.service.owner;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerService {

    Iterable<Owner> findAll();
}
