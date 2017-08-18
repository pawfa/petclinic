package hello.service.owner;

import hello.dao.OwnerRepository;
import hello.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);
    }
}
