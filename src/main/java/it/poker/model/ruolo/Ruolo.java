package it.poker.model.ruolo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ruolo {
	
	public static final String ADMIN_ROLE = "ADMIN_ROLE";
	public static final String PLAYER_USER_ROLE = "PLAYER_USER_ROLE";
	public static final String SPECIAL_PLAYER_USER_ROLE = "SPECIAL_PLAYER_USER_ROLE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String codice;

	public Ruolo() {
	}

	public Ruolo(String nome, String codice) {
		this.nome = nome;
		this.codice = codice;
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

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

}
