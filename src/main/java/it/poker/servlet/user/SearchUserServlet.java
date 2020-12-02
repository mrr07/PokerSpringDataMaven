package it.poker.servlet.user;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
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
    public SearchUserServlet() {
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
		
		// prendo i dati dalla form
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String credito = request.getParameter("credito");
		String esperienza = request.getParameter("esperienza");
		String dataRegistrazione = request.getParameter("dataRegistrazione");
		String stato = request.getParameter("stato");
		String[] ruoli = request.getParameterValues("ruolo");	
		
		//imposto il DTO per la validazione
		UserDTO userDTO = new UserDTO();
		userDTO.setNome(nome);
		userDTO.setCognome(cognome);
		userDTO.setUsername(username);
		userDTO.setCredito(credito);
		userDTO.setEsperienza(esperienza);
		userDTO.setDataRegistrazione(dataRegistrazione);
		userDTO.setStato(stato);
		
		List<String> listaRuoli = new ArrayList<>();
		if(ruoli != null) {
			for(int i=0; i<ruoli.length; i++) {
				listaRuoli.add(ruoli[i]);
			}
		}
		userDTO.setRuoli(listaRuoli);
		
		// valido i dati della form
		List<String> userErrors = userDTO.validazioneSearchUser();
		
		if(!userErrors.isEmpty()) {
			
			List<Stato> listaStati = new ArrayList<>();
			listaStati.add(Stato.ATTIVO);
			listaStati.add(Stato.DISABILITATO);
			listaStati.add(Stato.CREATO);
			listaStati.add(Stato.EMPTY);

			List<Ruolo> listaRuoliDaRitornareInPagina = ruoloService.listAllRuoli();

			request.setAttribute("listaStati", listaStati);
			request.setAttribute("listaRuoli", listaRuoliDaRitornareInPagina);
			
			request.setAttribute("userCercato", userDTO);
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("/user/searchUser.jsp").forward(request, response);
			return;
		}
		
		// se arrivo qui la validazione è andata a buon fine
		User userDaCercare = userDTO.buildModelFromDto(userDTO);
		
		Set<Ruolo> ruoliUser = new HashSet<>();
		
		
		List<String> listaRuoli1 = userDTO.getRuoli();
		for(String ruoloDaArray : listaRuoli1) {
			Ruolo ruolo = ruoloService.findByNome(ruoloDaArray);
			ruoliUser.add(ruolo);
			
		}
		userDaCercare.setRuoli(ruoliUser);
		
		List<User> listaUsers = userService.findByExample(userDaCercare);
		
		request.setAttribute("listaUsers", listaUsers);
		request.getRequestDispatcher("/user/gestioneUsers.jsp").forward(request, response);
		
		
		
		
	}

}
