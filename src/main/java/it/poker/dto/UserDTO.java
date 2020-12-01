package it.poker.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import it.poker.model.tavolo.Tavolo;
import it.poker.model.user.Stato;
import it.poker.model.user.User;

public class UserDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String dataRegistrazione;
	private String stato;
	private String esperienza;
	private String credito;
	private String tavolo;
	private List<String> ruoli;
	
	
	// questo costruttore mi serve per la login
	public UserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	
	public UserDTO() {
		
	}
	
	public UserDTO(String nome, String cognome, String username, String password, String dataRegistrazione,
			String stato, String esperienza, String credito, String tavolo) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
		this.esperienza = esperienza;
		this.credito = credito;
		this.tavolo = tavolo;
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
	public String getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getEsperienza() {
		return esperienza;
	}
	public void setEsperienza(String esperienza) {
		this.esperienza = esperienza;
	}
	public String getCredito() {
		return credito;
	}
	public void setCredito(String credito) {
		this.credito = credito;
	}
	public String getTavolo() {
		return tavolo;
	}
	public void setTavolo(String tavolo) {
		this.tavolo = tavolo;
	}
	public List<String> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
	}



	//validazione dell'input
	public List<String> validazioneLogin(){
		List<String> result = new ArrayList<String>();
		
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");
		
		return result;
	}
	
	// validazione dell'input
	public List<String> validazioneRegistrazione() {
		List<String> result = new ArrayList<String>();

		if (StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if (StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");

		return result;
	}
	
	// validazione per l'update
	public List<String> validazioneFormUpdate(){
		String regexData = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
		
		
		Pattern pattern = Pattern.compile(regexData);
		Matcher matcher = pattern.matcher(dataRegistrazione);
		
		
		List<String> result = new ArrayList<String>();
		
		
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");
		if(StringUtils.isBlank(this.dataRegistrazione) || matcher.matches())
			result.add("Il campo data di registrazione non può essere vuoto");
		if(StringUtils.isBlank(this.stato))
			result.add("Il campo stato non può essere vuoto");
		if(StringUtils.isBlank(this.esperienza) || !StringUtils.isNumeric(this.esperienza))
			result.add("Il campo esperienza non può essere vuoto");
		if(StringUtils.isBlank(this.credito) || !this.credito.matches("[+-]?\\d*\\.?\\d+"))
			result.add("Il campo credito non può essere vuoto");
		if(this.ruoli == null || this.ruoli.isEmpty())
			result.add("Il campo ruolo non può essere vuoto");
		
		
		return result;
	}
	
	
	
	public List<String> errors(){
		
		String regexData = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
		
		
		
		Pattern pattern = Pattern.compile(regexData);
		Matcher matcher = pattern.matcher(dataRegistrazione);
		
		
		List<String> result = new ArrayList<String>();
		
		
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");
		if(StringUtils.isBlank(this.dataRegistrazione) || !matcher.matches())
			result.add("Il campo data di registrazione non può essere vuoto");
		if(StringUtils.isBlank(this.stato))
			result.add("Il campo stato non può essere vuoto");
		if(StringUtils.isBlank(this.esperienza) || !StringUtils.isNumeric(this.esperienza))
			result.add("Il campo esperienza non può essere vuoto");
		if(StringUtils.isBlank(this.credito) || !StringUtils.isNumeric(this.credito))
			result.add("Il campo credito non può essere vuoto");
		if(StringUtils.isBlank(this.tavolo) || !StringUtils.isNumeric(this.tavolo))
			result.add("Il campo tavolo non può essere vuoto");
		
		
		return result;
	}
	
	
	
	public static User buildModelFromDto(UserDTO userDTO) {
		
		User result = new User();
		
		result.setId(userDTO.getId());
		result.setNome(userDTO.nome);
		result.setCognome(userDTO.cognome);
		result.setUsername(userDTO.username);
		result.setPassword(userDTO.password);
		
		//trasformo la stringa data in Date
		Date data = null;
		try {
			data=new SimpleDateFormat("yyyy/MM/dd").parse(userDTO.dataRegistrazione);
		} catch (ParseException e) {
			System.out.println("impossibile convertire la stringa in data");
		}  
		result.setDataRegistrazione(data);
		
		// stato dell'utente, da string a enum
		Stato stato = Stato.valueOf(userDTO.stato);
		result.setStato(stato);
		
		result.setEsperienza(Integer.parseInt(userDTO.esperienza));
		result.setCredito(Double.parseDouble(userDTO.credito));
		
		// imposto il tavolo
		Tavolo tavolo = new Tavolo();
		tavolo.setId(Long.parseLong(userDTO.tavolo));
		result.setTavolo(tavolo);
		
		return result;
	}
	
	
	
	
	
}
