package it.poker.servlet.tavolo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.dto.TavoloDTO;
import it.poker.model.tavolo.Tavolo;
import it.poker.model.user.User;
import it.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class UpdateTavoloServlet
 */
@WebServlet("/UpdateTavoloServlet")
public class UpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private TavoloService tavoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTavoloServlet() {
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
		String idDaAggiornare = request.getParameter("idDaAggiornare");
		Long id = Long.parseLong(idDaAggiornare);
		
		String denominazione = request.getParameter("denominazione");
		String cifraMinima = request.getParameter("cifraMinima");
		String esperienzaMinima = request.getParameter("esperienzaMinima");
		
		// comincia la validazione dei dati
		TavoloDTO tavoloDTO = new TavoloDTO();
		
		tavoloDTO.setId(id);
		tavoloDTO.setDenominazione(denominazione);
		tavoloDTO.setCifraMinima(cifraMinima);
		tavoloDTO.setEsperienzaMinima(esperienzaMinima);
		
		List<String> tavoloErrors = tavoloDTO.validazioneCreazioneTavolo();
		
		if(!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloDaAggiornare", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/updateTavolo.jsp").forward(request, response);
			return;
		}
		
		//se arrivo qui la validazione è andata a buon fine
		Tavolo tavoloDaAggiornare = TavoloDTO.buildModelFromDto(tavoloDTO);
		
		// al tavolo manca la data e l'user che ha creato il tavolo, dobbiamo recuperarli dal db
		Tavolo tavoloTemp = tavoloService.findById(tavoloDaAggiornare.getId());
		tavoloDaAggiornare.setDataCreazione(tavoloTemp.getDataCreazione());
		tavoloDaAggiornare.setUser(tavoloTemp.getUser());
		
		
		//aggiorno il tavolo
		tavoloService.update(tavoloDaAggiornare);
		
		//ritorno alla lista di tutti i tavoli con un messaggio di successo
		HttpSession session = request.getSession();
		
		User userInSessione = (User)session.getAttribute("user");
		
		List<Tavolo> listaTavoli = tavoloService.findByIDUserWithGiocatori(userInSessione.getId());
		request.setAttribute("listaTavoliUser", listaTavoli);
		request.setAttribute("successMessage", "modifica effettuata con successo");
		request.getRequestDispatcher("/tavolo/listTavoli.jsp").forward(request, response);

		return;
	}

}
