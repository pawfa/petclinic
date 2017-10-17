package hello.service.owner;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Owner;
import hello.security.validation.UserExistsException;

public interface OwnerService {

    Iterable<Owner> findAll();
    void saveOwner(Owner owner) throws UserExistsException;
    Owner findByMail(String mail);
    Owner findByFirstNameAndLastName(String firstLastName);
}
