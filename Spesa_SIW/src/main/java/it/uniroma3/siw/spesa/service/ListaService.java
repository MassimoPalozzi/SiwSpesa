package it.uniroma3.siw.spesa.service;

import it.uniroma3.siw.spesa.model.Lista;
import it.uniroma3.siw.spesa.model.Prodotto;
import it.uniroma3.siw.spesa.model.User;
import it.uniroma3.siw.spesa.repository.ListaRepository;
import it.uniroma3.siw.spesa.repository.ProdottoRepository;
import it.uniroma3.siw.spesa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Lista save(Lista lista) {
        return listaRepository.save(lista);
    }

    @Transactional
    public List<Lista> findAll() {
        return (List<Lista>) listaRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){listaRepository.deleteById(id);}

    public Lista findById(Long id){return listaRepository.findById(id).get();}

    @Transactional
    public void editNome(Long id, String nome){
        Lista lista = listaRepository.findById(id).get();
        lista.setName(nome);
        listaRepository.save(lista);
    }

    @Transactional
    public void addNome(Lista lista, String nome){
       lista.setName(nome);
       listaRepository.save(lista);
    }

    public Boolean AlreadyExists(Lista lista){return listaRepository.existsByNameAndUser(lista.getName(),lista.getUser());}

    @Transactional
    public void addProdotto(Long listaId, Long prodottoId){
        Lista lista = listaRepository.findById(listaId).get();
        Prodotto prodotto = prodottoService.findById(prodottoId);

        List<Prodotto> prodotti = lista.getProdotti();
        prodotti.add(prodotto);
        lista.setProdotti(prodotti);

        List<Lista> liste = prodotto.getListe();
        liste.add(lista);
        prodotto.setListe(liste);

        listaRepository.save(lista);
        prodottoRepository.save(prodotto);
    }

    @Transactional
    public void deleteProdotto(Long listaId, Long prodottoId){
        Lista lista = listaRepository.findById(listaId).get();
        Prodotto prodotto = prodottoService.findById(prodottoId);

        List<Prodotto> prodotti = lista.getProdotti();
        prodotti.remove(prodotto);
        lista.setProdotti(prodotti);

        List<Lista> liste = prodotto.getListe();
        liste.remove(lista);
        prodotto.setListe(liste);

        listaRepository.save(lista);
        prodottoRepository.save(prodotto);
    }

    @Transactional
    public void addUserById(Long listaId, Long userId) {
        User user =  userService.getUser(userId);
        Lista lista = listaRepository.findById(listaId).get();
        lista.setUser(user);
        List<Lista> liste = user.getListe();
        liste.add(lista);
        listaRepository.save(lista);
        userRepository.save(user);
    }

    public List<Prodotto> prodotti(Long id){
        Lista lista = listaRepository.findById(id).get();
        return lista.getProdotti();
    }
    public List<Prodotto> getAllNotIn(Long id){
        Lista lista = listaRepository.findById(id).get();
        List<Prodotto> prodotti = prodottoService.findAll();
        prodotti.removeAll(lista.getProdotti());
        return prodotti;
    }
    public Lista createLista(){return new Lista();}

    public List<Lista> listaByUserId(Long id){return listaRepository.findByUser(userService.getUser(id));}

    public Double costoTotale(Long id) {
        Double costo = 0.0;
        Lista lista = listaRepository.findById(id).get();
        for(Prodotto prodotto : lista.getProdotti())
            costo += prodotto.getPrezzo();
        return costo;
    }
}

    /*
    @Transactional
    public Lista findById(Long id) {
        return listaRepository.findById(id);
    }*/
