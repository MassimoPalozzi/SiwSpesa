package it.uniroma3.siw.spesa.service;

import it.uniroma3.siw.spesa.model.Credential;
import it.uniroma3.siw.spesa.model.User;
import it.uniroma3.siw.spesa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private CredentialService credentialService;


    @Transactional
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }


    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }


    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        for(User user : iterable)
            result.add(user);
        return result;
    }

    public Long getLocalUserId(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credential credential = credentialService.getCredential(userDetails.getUsername());
        return credentialService.getUser(credential).getId();
    }

    public boolean AlreadyExists(User user){return userRepository.findByNomeAndCognome(user.getNome(),user.getCognome());}


}