package hello.service.vet;

import hello.entity.Vet;

public interface VetService {

    Iterable<Vet> findAll();
    <S extends Vet> S save(S s);
    Vet getById(int id);

}
