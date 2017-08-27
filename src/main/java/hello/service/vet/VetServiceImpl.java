package hello.service.vet;

import hello.dao.VetRepository;
import hello.entity.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VetServiceImpl implements VetService {

    private VetRepository vetRepository;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    @Transactional
    public Iterable<Vet> findAll() {
        return vetRepository.findAll();
    }

    @Override
    @Transactional
    public <S extends Vet> S save(S s) {
        s.setMail();
        return vetRepository.save(s);
    }

    @Override
    public Vet getById(int id) {
        return vetRepository.getById(id);
    }

    @Override
    @Transactional
    public void deleteVetById(int id) {
        vetRepository.deleteVetById(id);
    }

    @Override
    @Transactional
    public Vet findByMail(String mail) {
        return vetRepository.findByMail(mail);
    }
}
