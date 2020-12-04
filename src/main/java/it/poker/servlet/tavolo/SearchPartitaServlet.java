package it.poker.servlet.tavolo;

import java.io.IOException;
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

import it.poker.dto.TavoloDTO;
import it.poker.model.tavolo.Tavolo;
import it.poker.model.user.User;
import it.poker.service.tavolo.TavoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class SearchPartitaServlet
 */
@WebServlet("/SearchPartitaServlet")
public class SearchPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private TavoloService tavoloService;
	
	@Autowired
    private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPartitaServlet() {
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
		
		String denominazione = request.getParameter("denominazione");
		String cifraMinima = request.getParameter("cifraMinima");
		String dataCreazione = request.getParameter("dataCreazione");
		String utenteId = request.getParameter("utenteId");
		String creatoreId = request.getParameter("creatoreId");
		
		TavoloDTO tavoloDTO = new TavoloDTO();
		
		tavoloDTO.setDenominazione(denominazione);
		tavoloDTO.setCifraMinima(cifraMinima);
		tavoloDTO.setDataCreazione(dataCreazione);
		
		List<String> tavoloErrors = tavoloDTO.validazioneSearchPartita();
		
		if(!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/searchPartita.jsp").forward(request, response);
		}
		
		Tavolo tavoloDaCercare = TavoloDTO.buildModelFromDto(tavoloDTO);
		if(creatoreId != "") {
			Long id = Long.parseLong(creatoreId);
			User user = userService.findById(id);
			tavoloDaCercare.setUser(user);
		} else {
			tavoloDaCercare.setUser(null);
		}
		
		Set<User> users = new HashSet<>();
		if(utenteId != "") {
			Long id = Long.parseLong(utenteId);
			User user = userService.findById(id);
			users.add(user);
			tavoloDaCercare.setUsers(users);
		} else {
			tavoloDaCercare.setUsers(users);
		}
		
		
		
		List<Tavolo> listaPartite = tavoloService.findByExample2(tavoloDaCercare);
		
		request.setAttribute("listaPartite", listaPartite);
		request.getRequestDispatcher("/management/listPartite.jsp").forward(request, response);
		
	}

}
