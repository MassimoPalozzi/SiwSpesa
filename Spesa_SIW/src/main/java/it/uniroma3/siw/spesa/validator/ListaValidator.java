package it.uniroma3.siw.spesa.validator;

import it.uniroma3.siw.spesa.model.Lista;
import it.uniroma3.siw.spesa.model.Prodotto;
import it.uniroma3.siw.spesa.model.User;
import it.uniroma3.siw.spesa.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ListaValidator implements Validator {

    @Autowired
    private ListaService listaService;

    @Override
    public void validate(Object o, Errors errors) {
        Lista lista = (Lista) o;
        if(listaService.AlreadyExists(lista))
            errors.reject("lista.duplicato");
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}
