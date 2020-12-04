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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/user/UpdateUserServlet")
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
		String stato = request.getParameter("stato");
		String[] listaRuoli = request.getParameterValues("ruolo");
		
		// stiamo aggiornando un utente che ha uno stato attivo o disabilitato
		if(stato == null && listaRuoli == null) {
			
			UserDTO userDTO = new UserDTO();
			userDTO.setNome(nome);
			userDTO.setCognome(cognome);
			userDTO.setUsername(username);
			
			List<String> userErrors = userDTO.validazioneFormUpdate2();
			
			if(!userErrors.isEmpty()) {
				
				User userDaPassareInForm = new User();
				userDaPassareInForm.setId(Long.parseLong(idDaAggiornare));
				userDaPassareInForm.setNome(userDTO.getNome());
				userDaPassareInForm.setCognome(userDTO.getCognome());
				userDaPassareInForm.setUsername(userDTO.getUsername());
				userDaPassareInForm.setStato(null);
				
				request.setAttribute("userErrors", userErrors);
				request.setAttribute("userDaAggiornare", userDaPassareInForm);
				request.getRequestDispatcher("/user/updateUser.jsp").forward(request, response);
				return;
				
			}
			
			User userDaAggiornare = new User();
			
			Long id = Long.parseLong(idDaAggiornare);
			
			userDaAggiornare = userService.findById(id);
			
			userDaAggiornare.setId(id);
			userDaAggiornare.setNome(userDTO.getNome());
			userDaAggiornare.setCognome(userDTO.getCognome());
			userDaAggiornare.setUsername(userDTO.getUsername());
			
			userService.update(userDaAggiornare);
			
			List<User> listaUsers = userService.listAllUsersWithTavoliAndRuoli();
			request.setAttribute("successMessage", "L'user è stato modificato correttamente");
			request.setAttribute("listaUsers", listaUsers);
			request.getRequestDispatcher("/user/gestioneUsers.jsp").forward(request, response);
			return;
		} else {
			
			// stiamo aggiornando un utente che in stato di creato
			
			UserDTO userDTO = new UserDTO();
			userDTO.setNome(nome);
			userDTO.setCognome(cognome);
			userDTO.setUsername(username);
			userDTO.setStato(stato);
			
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
			
			List<String> userErrors = userDTO.validazioneFormUpdate1();
			
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
				userDaPassareInForm.setId(Long.parseLong(idDaAggiornare));
				userDaPassareInForm.setNome(userDTO.getNome());
				userDaPassareInForm.setCognome(userDTO.getCognome());
				userDaPassareInForm.setUsername(userDTO.getUsername());
				userDaPassareInForm.setStato(Stato.valueOf(userDTO.getStato()));
				
				userDaPassareInForm.setRuoli(listaRuoliSelezionati);
				
				
				request.setAttribute("listaStati", listaStati);
				request.setAttribute("listaRuoli", listaRuoliDaRitornareInPagina);
				request.setAttribute("userErrors", userErrors);
				request.setAttribute("userDaAggiornare", userDaPassareInForm);
				request.getRequestDispatcher("/user/updateUser.jsp").forward(request, response);
				return;
				
			}
			
			User userDaAggiornare = new User();
			
			Long id = Long.parseLong(idDaAggiornare);
			
			userDaAggiornare = userService.findById(id);
			
			
			userDaAggiornare.setId(id);
			userDaAggiornare.setNome(userDTO.getNome());
			userDaAggiornare.setCognome(userDTO.getCognome());
			userDaAggiornare.setUsername(userDTO.getUsername());
			userDaAggiornare.setStato(Stato.valueOf(userDTO.getStato()));
			
			Set<Ruolo> listaRuoliSelezionati = new HashSet<Ruolo>();
			List<String> listaTemp = userDTO.getRuoli();
			for(int i=0; i<listaTemp.size(); i++) {
				String nomeRuolo = listaTemp.get(i);
				Ruolo ruoloSelezionato = ruoloService.findByNome(nomeRuolo);
				ruoloSelezionato.getNome();
				listaRuoliSelezionati.add(ruoloSelezionato);
			}
			
			userDaAggiornare.setRuoli(listaRuoliSelezionati);
			
			userService.update(userDaAggiornare);
			
			
			List<User> listaUsers = userService.listAllUsersWithTavoliAndRuoli();
			request.setAttribute("successMessage", "L'user è stato modificato correttamente");
			request.setAttribute("listaUsers", listaUsers);
			request.getRequestDispatcher("/user/gestioneUsers.jsp").forward(request, response);
			
			
		}	
		
	}

}
