package it.uniroma3.siw.spesa.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spesa.model.User;


public interface UserRepository extends CrudRepository<User, Long> {
}