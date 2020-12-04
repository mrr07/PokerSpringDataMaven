package it.poker.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

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
	
	// validazione per l'update basico
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
//		if(StringUtils.isBlank(this.password))
//			result.add("Il campo password non può essere vuoto");
		if(StringUtils.isBlank(this.dataRegistrazione) || matcher.matches())
			result.add("Il campo data di registrazione non può essere vuoto");
//		if(StringUtils.isBlank(this.stato))
//			result.add("Il campo stato non può essere vuoto");
//		if(StringUtils.isBlank(this.esperienza) || !StringUtils.isNumeric(this.esperienza))
//			result.add("Il campo esperienza non può essere vuoto");
//		if(StringUtils.isBlank(this.credito) || !this.credito.matches("[+-]?\\d*\\.?\\d+"))
//			result.add("Il campo credito non può essere vuoto");
//		if(this.ruoli == null || this.ruoli.isEmpty())
//			result.add("Il campo ruolo non può essere vuoto");
		
		
		return result;
	}
	
	
	// validazione per passare l'user in uno stato diverso da CREATO
	public List<String> validazioneFormUpdate1() {
		
		List<String> result = new ArrayList<String>();

		if (StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if (StringUtils.isBlank(this.stato))
			result.add("Il campo stato non può essere vuoto");
		if (this.ruoli == null || this.ruoli.isEmpty())
			result.add("Il campo ruolo non può essere vuoto");

		return result;
	}
	
	// validazione per passare l'user in uno stato diverso da CREATO
		public List<String> validazioneFormUpdate2() {
			
			List<String> result = new ArrayList<String>();

			if (StringUtils.isBlank(this.nome))
				result.add("Il campo nome non può essere vuoto");
			if (StringUtils.isBlank(this.cognome))
				result.add("Il campo cognome non può essere vuoto");
			if (StringUtils.isBlank(this.username))
				result.add("Il campo username non può essere vuoto");

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
		if(StringUtils.isBlank(this.dataRegistrazione) || matcher.matches())
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
	
	public List<String> validazioneSearchUser(){
		
		
		String regexData = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";

		Pattern pattern = Pattern.compile(regexData);
		Matcher matcher = pattern.matcher(dataRegistrazione);

		List<String> result = new ArrayList<String>();

		if (!this.nome.matches("^[a-zA-Z]*$") && !this.nome.matches("^$"))
			result.add("Il campo nome non è valido");
		if (!this.cognome.matches("^[a-zA-Z]*$") && !this.cognome.matches("^$"))
			result.add("Il campo cognome non è valido");
		if (this.username.matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$"))
			result.add("Il campo username non è valido");
		if (matcher.matches())
			result.add("Il campo data di registrazione non è valido");
		if (!StringUtils.isNumeric(this.esperienza) && !this.esperienza.matches("^$"))
			result.add("Il campo esperienza non è valido");
		if (!StringUtils.isNumeric(this.credito) && !this.credito.matches("^$"))
			result.add("Il campo credito non è valido");

		return result;
		
	}
	
	
	
	public static User buildModelFromDto(UserDTO userDTO) {
		
		User result = new User();
		
		if(userDTO.getId() != null) {
			result.setId(userDTO.getId());
		} else {
			result.setId(null);
		}
		
		if(userDTO.getNome() != null && userDTO.getNome() != "") {
			result.setNome(userDTO.nome);
		} else {
			result.setNome("");
		}
		
		if(userDTO.getCognome() != null && userDTO.getCognome() != "") {
			result.setCognome(userDTO.cognome);
		} else {
			result.setCognome("");
		}
		
		if(userDTO.getUsername() != null && userDTO.getUsername() != "") {
			result.setUsername(userDTO.username);
		} else {
			result.setUsername("");
		}
		
		if(userDTO.getPassword() != null && userDTO.getPassword() != "") {
			result.setPassword(userDTO.password);
		} else {
			result.setPassword("");
		}
		
		//trasformo la stringa data in Date
		Date data = null;
		if(userDTO.getDataRegistrazione() != null && userDTO.getDataRegistrazione() != "") {
			
			try {
				data = new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.dataRegistrazione);
			} catch (ParseException e) {
				System.out.println("impossibile convertire la stringa in data");
			}
			
		}
		result.setDataRegistrazione(data);
		
		
		if(userDTO.getStato() != null && userDTO.getStato() != "") {
			Stato stato = Stato.valueOf(userDTO.stato);
			result.setStato(stato);
		}else {
			result.setStato(null);
		}
		
		// stato dell'utente, da string a enum
		
		if(userDTO.getCredito() != null && userDTO.getCredito() != "") {
			result.setCredito(Integer.parseInt(userDTO.getCredito()));
		} else {
			result.setCredito(null);
		}
		
		if(userDTO.getEsperienza() != null && userDTO.getEsperienza() != "") {
			result.setEsperienza(Integer.parseInt(userDTO.getEsperienza()));
		} else {
			result.setEsperienza(null);
		}
		
		
		return result;
	}
	
	
	
	
	
}
