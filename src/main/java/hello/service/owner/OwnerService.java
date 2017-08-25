package hello.service.owner;

/**
 * Created by Pawel on 2017-07-21.
 */


import hello.entity.Owner;

public interface OwnerService {

    Iterable<Owner> findAll();
    void saveOwner(Owner owner);
    Owner findByMail(String mail);
}
