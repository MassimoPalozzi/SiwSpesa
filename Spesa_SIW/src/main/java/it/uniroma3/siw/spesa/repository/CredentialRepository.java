package it.uniroma3.siw.spesa.repository;

import it.uniroma3.siw.spesa.model.Credential;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialRepository extends CrudRepository<Credential, Long> {
    public Optional<Credential> findByUsername(String username);

    public Boolean existsByUsername(String username);
}
