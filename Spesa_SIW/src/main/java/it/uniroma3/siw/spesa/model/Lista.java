package it.uniroma3.siw.spesa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
@Table(name = "Liste")
public class Lista{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "lista")
    private List<Prodotto> prodotti;

    public Lista(Long id, String name, User user, List<Prodotto> prodotti) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.prodotti = prodotti;
    }

    public Lista() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
}