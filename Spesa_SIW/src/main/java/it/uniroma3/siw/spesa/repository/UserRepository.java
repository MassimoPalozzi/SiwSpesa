package it.uniroma3.siw.spesa.repository;

import it.uniroma3.siw.spesa.model.Lista;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spesa.model.User;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    public boolean findByNomeAndCognome(String nome, String cognome);

}