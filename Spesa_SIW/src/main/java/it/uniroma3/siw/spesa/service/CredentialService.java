package it.uniroma3.siw.spesa.service;

import it.uniroma3.siw.spesa.model.Credential;
import it.uniroma3.siw.spesa.model.User;
import it.uniroma3.siw.spesa.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CredentialService {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected CredentialRepository credentialRepository;

    @Transactional
    public Credential getCredential(Long id) {
        Optional<Credential> result = this.credentialRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public Credential getCredential(String username) {
        Optional<Credential> result = this.credentialRepository.findByUsername(username);
        return result.orElse(null);
    }

    @Transactional
    public Credential saveCredential(Credential credential) {
        credential.setRole(Credential.DEFAULT_ROLE);
        credential.setPassword(this.passwordEncoder.encode(credential.getPassword()));
        return this.credentialRepository.save(credential);
    }

    public User getUser(Credential credential){return credential.getUser();
    }



    public boolean alreadyExists(Credential credential){return credentialRepository.existsByUsername(credential.getUsername());}
}