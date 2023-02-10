package it.uniroma3.siw.spesa.service;

import it.uniroma3.siw.spesa.model.Lista;
import it.uniroma3.siw.spesa.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Transactional
    public Lista save(Lista lista) {
        return listaRepository.save(lista);
    }

    @Transactional
    public List<Lista> findAll() {
        return (List<Lista>) listaRepository.findAll();
    }

    @Override
    @Transactional
    public Lista findById(Long id) {
        return lista:
