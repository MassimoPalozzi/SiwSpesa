package it.uniroma3.siw.spesa.repository;

import it.uniroma3.siw.spesa.model.Prodotto;
import org.springframework.data.repository.CrudRepository;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long> {

    public Boolean existsByNome(String nome);

}