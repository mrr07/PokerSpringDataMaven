package it.poker.servlet.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.dto.UserDTO;
import it.poker.model.ruolo.Ruolo;
import it.poker.model.user.Stato;
import it.poker.model.user.User;
import it.poker.service.ruolo.RuoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private RuoloService ruoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idDaAggiornare = request.getParameter("idDaAggiornare");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String stato = request.getParameter("stato");
		String esperienza = request.getParameter("esperienza");
		String credito = request.getParameter("credito");
		String dataRegistrazione = request.getParameter("dataRegistrazione");
		String[] listaRuoli = request.getParameterValues("ruolo");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setNome(nome);
		userDTO.setCognome(cognome);
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		userDTO.setStato(stato);
		userDTO.setEsperienza(esperienza);
		userDTO.setCredito(credito);
		userDTO.setDataRegistrazione(dataRegistrazione);
		
		// ruoli che ho selezionato nella form di update
		List<String> listaRuoliDaPassareDTO = new ArrayList<String>();
		if(listaRuoli == null) {
			listaRuoliDaPassareDTO = null;
		} else {
			for(int i=0; i<listaRuoli.length; i++) {
				listaRuoliDaPassareDTO.add(listaRuoli[i]);
			}
		}
		userDTO.setRuoli(listaRuoliDaPassareDTO);
		
		List<String> userErrors = userDTO.validazioneFormUpdate();
		
		if(!userErrors.isEmpty()) {
			List<Ruolo> listaRuoliDaRitornareInPagina = ruoloService.listAllRuoli();
			
			List<Stato> listaStati = new ArrayList<>();
			listaStati.add(Stato.ATTIVO);
			listaStati.add(Stato.DISABILITATO);
			listaStati.add(Stato.CREATO);
			listaStati.add(Stato.EMPTY);
			
			Set<Ruolo> listaRuoliSelezionati = new HashSet<Ruolo>();
			if(userDTO.getRuoli() == null) {
				listaRuoliSelezionati = null;
			} else {
				List<String> listaTemp = userDTO.getRuoli();
				for(int i=0; i<listaTemp.size(); i++) {
					String nomer = listaTemp.get(i);
					Ruolo ruoloSelezionato = ruoloService.findByNome(nomer);
					ruoloSelezionato.getNome();
					listaRuoliSelezionati.add(ruoloSelezionato);
				}
			}
			
			
			
			User userDaPassareInForm = new User();
			userDaPassareInForm.setNome(userDTO.getNome());
			userDaPassareInForm.setCognome(userDTO.getCognome());
			userDaPassareInForm.setUsername(userDTO.getUsername());
			userDaPassareInForm.setPassword(userDTO.getPassword());
			userDaPassareInForm.setStato(Stato.valueOf(userDTO.getStato()));
			
			Date dataInserita = new Date();
			if(userDTO.getDataRegistrazione() != "") {
				try {
					dataInserita = new SimpleDateFormat("yyyy-MM-dd").parse(dataRegistrazione);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				dataInserita = null;
			}
			
			userDaPassareInForm.setDataRegistrazione(dataInserita);
			
			
			userDaPassareInForm.setEsperienza(Integer.parseInt(userDTO.getEsperienza()));
			userDaPassareInForm.setCredito(Double.parseDouble(userDTO.getCredito()));
			userDaPassareInForm.setRuoli(listaRuoliSelezionati);
			
			
			request.setAttribute("listaStati", listaStati);
			request.setAttribute("listaRuoli", listaRuoliDaRitornareInPagina);
			request.setAttribute("userErrors", userErrors);
			request.setAttribute("user", userDaPassareInForm);
			request.getRequestDispatcher("/user/updateUser.jsp").forward(request, response);
			return;
			
		}
		
		User userDaAggiornare = new User();
		
		Long id = Long.parseLong(idDaAggiornare);
		
		
		Date dataInserita = new Date();
		if(userDTO.getDataRegistrazione() != "") {
			try {
				dataInserita = new SimpleDateFormat("yyyy-MM-dd").parse(dataRegistrazione);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			dataInserita = null;
		}
		
		
		
		userDaAggiornare.setId(id);
		userDaAggiornare.setNome(userDTO.getNome());
		userDaAggiornare.setCognome(userDTO.getCognome());
		userDaAggiornare.setUsername(userDTO.getUsername());
		userDaAggiornare.setPassword(userDTO.getPassword());
		userDaAggiornare.setStato(Stato.valueOf(userDTO.getStato()));
		userDaAggiornare.setDataRegistrazione(dataInserita);
		userDaAggiornare.setEsperienza(Integer.parseInt(userDTO.getEsperienza()));
		userDaAggiornare.setCredito(Double.parseDouble(userDTO.getCredito()));
		
		Set<Ruolo> listaRuoliSelezionati = new HashSet<Ruolo>();
		List<String> listaTemp = userDTO.getRuoli();
		for(int i=0; i<listaTemp.size(); i++) {
			String nomer = listaTemp.get(i);
			Ruolo ruoloSelezionato = ruoloService.findByNome(nomer);
			ruoloSelezionato.getNome();
			listaRuoliSelezionati.add(ruoloSelezionato);
		}
		
		userDaAggiornare.setRuoli(listaRuoliSelezionati);
		
		userService.update(userDaAggiornare);
		
		
		List<User> listaUsers = userService.listAllUsersWithTavoliAndRuoli();
		request.setAttribute("successMessage", "L'user è stato modificato correttamente");
		request.setAttribute("listaUsers", listaUsers);
		request.getRequestDispatcher("gestioneUsers.jsp").forward(request, response);
		
		
		/*
		 * TODO commentare il codice e scriver eun metodo utils per il cast delle date
		 */
		
		
		
	}

}
