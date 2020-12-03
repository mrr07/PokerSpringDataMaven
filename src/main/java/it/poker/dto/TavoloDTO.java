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
import it.poker.model.user.User;

public class TavoloDTO {

	private Long id;
	private String denominazione;
	private String esperienzaMinima;
	private String cifraMinima;
	private String dataCreazione;
	private String idUser;
	
	public TavoloDTO() {
	}
	
	public TavoloDTO(String denominazione, String esperienzaMinima, String cifraMinima, String dataCreazione,
			String idUser) {
		this.denominazione = denominazione;
		this.esperienzaMinima = esperienzaMinima;
		this.cifraMinima = cifraMinima;
		this.dataCreazione = dataCreazione;
		this.idUser = idUser;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getEsperienzaMinima() {
		return esperienzaMinima;
	}
	public void setEsperienzaMinima(String esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}
	public String getCifraMinima() {
		return cifraMinima;
	}
	public void setCifraMinima(String cifraMinima) {
		this.cifraMinima = cifraMinima;
	}
	public String getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	// validazione dell'input
	public List<String> validazioneCreazioneTavolo() {
		List<String> result = new ArrayList<String>();

		if (StringUtils.isBlank(this.denominazione))
			result.add("Il campo denominazione non può essere vuoto");
		if(StringUtils.isBlank(this.cifraMinima) || !StringUtils.isNumeric(this.cifraMinima))
			result.add("Il campo cifra minima non può essere vuoto");
		if(StringUtils.isBlank(this.esperienzaMinima) || !StringUtils.isNumeric(this.esperienzaMinima))
			result.add("Il campo esperienza minima non può essere vuoto");

		return result;
	}
	
	// validazione dell'input della ricerca del tavolo
		public List<String> validazioneSearchTavolo() {
			List<String> result = new ArrayList<String>();
			
			String regexData = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
			
			
			Pattern pattern = Pattern.compile(regexData);
			Matcher matcher = pattern.matcher(dataCreazione);

			if (StringUtils.isBlank(this.denominazione))
				result.add("Il campo denominazione non può essere vuoto");
			if(StringUtils.isBlank(this.cifraMinima) || !StringUtils.isNumeric(this.cifraMinima))
				result.add("Il campo cifra minima non può essere vuoto");
			if(StringUtils.isBlank(this.dataCreazione) || matcher.matches())
				result.add("Il campo data di registrazione non può essere vuoto");
			

			return result;
	}
		
	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		
		Tavolo result = new Tavolo();
		
		if(tavoloDTO.getId() != null) {
			result.setId(tavoloDTO.getId());
		} else {
			result.setId(null);
		}
		
		if(tavoloDTO.getDenominazione() != null || tavoloDTO.getDenominazione() != "") {
			result.setDenominazione(tavoloDTO.getDenominazione());
		} else {
			result.setDenominazione("");
		}
		
		if(tavoloDTO.getCifraMinima() != null || tavoloDTO.getCifraMinima() != "") {
			result.setCifraMinima(Integer.parseInt(tavoloDTO.getCifraMinima()));
		} else {
			result.setCifraMinima(null);
		}
		
		if(tavoloDTO.getEsperienzaMinima() != null || tavoloDTO.getEsperienzaMinima() != "") {
			result.setEsperienzaMinima(Integer.parseInt(tavoloDTO.getEsperienzaMinima()));
		} else {
			result.setEsperienzaMinima(null);
		}
		
		Date data = null;
		if(tavoloDTO.getDataCreazione() != null && tavoloDTO.getDataCreazione() != "") {
			
			try {
				data = new SimpleDateFormat("yyyy-MM-dd").parse(tavoloDTO.dataCreazione);
			} catch (ParseException e) {
				System.out.println("impossibile convertire la stringa in data");
			}
			
		}
		result.setDataCreazione(data);
		
		
		
		return result;
		
	}
	
	
	
	
}
