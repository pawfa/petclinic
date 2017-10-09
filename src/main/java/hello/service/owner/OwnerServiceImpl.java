package hello.service.owner;

import hello.dao.OwnerRepository;
import hello.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional
    public Iterable<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) throws Exception {
        if(ownerRepository.findByMail(owner.getMail()) != null){
            throw new Exception("Mail already exists in database");
        }
//        ownerRepository.save(owner);
    }

    @Override
    public Owner findByMail(String mail) {
        return ownerRepository.findByMail(mail);
    }

    @Override
    public Owner findByFirstNameAndLastName(String firstLastName) {
        String[] splitted = firstLastName.split("\\s");
        return ownerRepository.findByFirstNameAndLastName(splitted[0],splitted[1]);
    }
}
