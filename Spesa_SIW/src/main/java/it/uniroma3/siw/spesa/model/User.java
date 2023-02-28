package it.uniroma3.siw.spesa.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "cognome"}))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQUENCE_GENERATOR")
	private Long id;
	private String nome;
	private String cognome;

	@OneToMany(mappedBy = "user")
	private List<Lista> liste;

	public User(Long id, String nome, String cognome, List<Lista> liste) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.liste = liste;
	}

	public User() {
		this.liste=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Lista> getListe() {
		return liste;
	}

	public void setListe(List<Lista> liste) {
		this.liste = liste;
	}
}