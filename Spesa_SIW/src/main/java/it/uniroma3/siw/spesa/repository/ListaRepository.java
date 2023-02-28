package it.uniroma3.siw.spesa.repository;

import it.uniroma3.siw.spesa.model.Lista;
import it.uniroma3.siw.spesa.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListaRepository extends CrudRepository<Lista, Long> {

    public Boolean existsByNameAndUser(String name, User user);

    public List<Lista> findByUser(User user);
}
