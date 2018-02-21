package it.eng.projectwork.gruppo05.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

@Entity
@Table(name="USER")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length=20)
	private String username;
	
	@Column (name="NOME_U", nullable=false, length=80)// abbiamo la sintassi di column
	private String nome;
	
	private String cognome;
	
	private String email;
	
	//Costruttori
	
	public User() {}
	
	public User(String usern, String em) {
		this.username = usern;
		this.email = em;
	}
	
	public User(String usern, String name, String surname, String em) {
		this.username = usern;
		this.nome = name;
		this.cognome = surname;
		this.email = em;
	}
	
	//Getters e Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + "]";
	}
}
