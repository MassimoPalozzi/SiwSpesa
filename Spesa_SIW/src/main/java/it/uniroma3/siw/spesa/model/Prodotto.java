package it.uniroma3.siw.spesa.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
@Table(name = "Prodotto", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "descrizione"}))
public class Prodotto{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
        private Long id;

        @Column(unique = true)
        @NotBlank
        private String nome;

        @NotBlank
        private String descrizione;
        @NotNull
        private Double prezzo;

        @ManyToMany
        @JoinColumn(name = "lista_id")
        private List<Lista> lista;

    public Prodotto(Long id,String nome,String descrizione, Double prezzo, List<Lista> lista) {
        this.id=id;
        this.nome = nome;
        this.descrizione=descrizione;
        this.prezzo = prezzo;
        this.lista = lista;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}


    public Prodotto() {
        this.lista=new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione(){return descrizione;}

    public void setDescrizione(String descrizione){this.descrizione=descrizione;}

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public List<Lista> getListe() {
        return lista;
    }

    public void setListe(List<Lista> lista) {
        this.lista = lista;
    }
}

