package it.poker.servlet.tavolo;

import java.io.IOException;
import java.util.Date;
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
 * Servlet implementation class InsertTavoloServlet
 */
@WebServlet("/InsertTavoloServlet")
public class InsertTavoloServlet extends HttpServlet {
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
    public InsertTavoloServlet() {
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
		String denominazione = request.getParameter("denominazione");
		String cifraMinima = request.getParameter("cifraMinima");
		String esperienzaMinima = request.getParameter("esperienzaMinima");
		
		//creo e dto e valido i dati della form
		TavoloDTO tavoloDTO = new TavoloDTO();
		tavoloDTO.setDenominazione(denominazione);
		tavoloDTO.setCifraMinima(cifraMinima);
		tavoloDTO.setEsperienzaMinima(esperienzaMinima);
		
		List<String> tavoloErrors = tavoloDTO.validazioneCreazioneTavolo();
		
		if(!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/insertTavolo.jsp").forward(request, response);
			return;
		}
		
		Tavolo tavoloDaCreare = new Tavolo();
		Date dataCreazione = new Date();
		
		//imposto i dati al tavolo da inserire nel db
		tavoloDaCreare.setDenominazione(tavoloDTO.getDenominazione());
		tavoloDaCreare.setCifraMinima(Integer.parseInt(tavoloDTO.getCifraMinima()));
		tavoloDaCreare.setEsperienzaMinima(Integer.parseInt(tavoloDTO.getEsperienzaMinima()));
		tavoloDaCreare.setDataCreazione(dataCreazione);
		
		//riprendo l'utente in sessione in quanto sarà lui a inserire il tavolo
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		tavoloDaCreare.setUser(user);
		
		
		//inserisco il tavolo
		tavoloService.addNew(tavoloDaCreare);
		
		//cerco tutti i tavoli dell'utente
		List<Tavolo> listaTavoli = tavoloService.findByIDUser(user.getId());
		
		// vado nella pagina con tutti i tavoli dell'utente
		request.setAttribute("listaTavoliUser", listaTavoli);
		request.getRequestDispatcher("/tavolo/listTavoli.jsp").forward(request, response);
		
		
	}

}
