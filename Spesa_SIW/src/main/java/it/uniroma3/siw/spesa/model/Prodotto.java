package it.uniroma3.siw.spesa.model;


import javax.persistence.*;

@Entity
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
@Table(name = "Prodotto")
public class Prodotto{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
        private Long id;

        private String nome;
        private Double prezzo;

        @ManyToOne
        @JoinColumn(name = "lista_id")
        private Lista lista;

    public Prodotto(String nome, Double prezzo, Lista lista) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.lista = lista;
    }

    public Prodotto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }
}

