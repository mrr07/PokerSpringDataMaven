package it.poker.model.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.poker.model.ruolo.Ruolo;
import it.poker.model.tavolo.Tavolo;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;
	@Enumerated(EnumType.STRING)
	private Stato stato = Stato.CREATO;
	private Integer esperienza;
	private Double credito;
	
	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavolo_id", nullable = true)
	private Tavolo tavolo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Tavolo> tavoli = new HashSet<>();

	public User() {
	}
	
	

	public User(Long id, String nome, String cognome, String username, String password, Date dataRegistrazione,
			Stato stato, Integer esperienza, Double credito, Set<it.poker.model.ruolo.Ruolo> ruoli, Tavolo tavoloDaGioco,
			Set<Tavolo> tavoliCreati) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.esperienza = esperienza;
		this.credito = credito;
		this.ruoli = ruoli;
		this.tavolo = tavoloDaGioco;
		this.tavoli = tavoliCreati;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Integer getEsperienza() {
		return esperienza;
	}

	public void setEsperienza(Integer esperienza) {
		this.esperienza = esperienza;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavoloDaGioco) {
		this.tavolo = tavoloDaGioco;
	}

	public Set<Tavolo> getTavoli() {
		return tavoli;
	}

	public void setTavoli(Set<Tavolo> tavoliCreati) {
		this.tavoli = tavoliCreati;
	}
	
	

}