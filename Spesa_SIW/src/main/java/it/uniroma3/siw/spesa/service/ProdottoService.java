package it.uniroma3.siw.spesa.service;

import it.uniroma3.siw.spesa.model.Prodotto;
import it.uniroma3.siw.spesa.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Transactional
    public Prodotto save(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }


    @Transactional
    public List<Prodotto> findAll() {
        return (List<Prodotto>) prodottoRepository.findAll();
    }

    @Transactional
    public Prodotto findById(Long id) {
        return prodottoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        prodottoRepository.deleteById(id);
    }
}
