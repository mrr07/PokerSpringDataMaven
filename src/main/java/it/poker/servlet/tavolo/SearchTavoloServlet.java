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
 * Servlet implementation class SearchTavoloServlet
 */
@WebServlet("/tavolo/SearchTavoloServlet")
public class SearchTavoloServlet extends HttpServlet {
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
    public SearchTavoloServlet() {
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
		
		//prendo i dati dalla form
		String denominazione = request.getParameter("denominazione");
		String cifraMinima = request.getParameter("cifraMinima");
		String dataCreazione = request.getParameter("dataCreazione");
		
		// incomincia la validazione dei dati presi dalla form
		TavoloDTO tavoloDTO = new TavoloDTO();
		tavoloDTO.setDenominazione(denominazione);
		tavoloDTO.setDataCreazione(dataCreazione);
		tavoloDTO.setCifraMinima(cifraMinima);
		
		List<String> tavoloErrors = tavoloDTO.validazioneSearchTavolo();
		
		if(!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.setAttribute("tavoloCercato", tavoloDTO);
			request.getRequestDispatcher("/tavolo/searchTavolo.jsp").forward(request, response);
		}
		
		
		// la validazione è andata a buon fine
		Tavolo tavoloDaCercare = TavoloDTO.buildModelFromDto(tavoloDTO);
		
		
		//riprendo l'user in sessione
		HttpSession session = request.getSession();
		User userDelTavolo = (User)session.getAttribute("user");
		
		tavoloDaCercare.setUser(userDelTavolo);
		
		//effettuo la ricerca
		List<Tavolo> listaTavoliDiUser = tavoloService.findByExample(tavoloDaCercare);
		
		request.setAttribute("listaTavoliUser", listaTavoliDiUser);
		request.getRequestDispatcher("/tavolo/listTavoli.jsp").forward(request, response);
		
		
	
	}

}
