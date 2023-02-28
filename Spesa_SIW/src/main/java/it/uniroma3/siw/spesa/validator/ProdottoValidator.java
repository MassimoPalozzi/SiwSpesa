package it.uniroma3.siw.spesa.validator;

import it.uniroma3.siw.spesa.model.Prodotto;
import it.uniroma3.siw.spesa.model.User;
import it.uniroma3.siw.spesa.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProdottoValidator implements Validator {

    @Autowired
    ProdottoService prodottoService;

    @Override
    public void validate(Object o, Errors errors) {
        if(prodottoService.AlreadyExists((Prodotto) o))
            errors.reject("prodotto.duplicato");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}

