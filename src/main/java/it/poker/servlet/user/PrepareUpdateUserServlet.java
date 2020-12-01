package it.poker.servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.ruolo.Ruolo;
import it.poker.model.user.Stato;
import it.poker.model.user.User;
import it.poker.service.ruolo.RuoloService;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class PrepareUpdateUserServlet
 */
@WebServlet("/PrepareUpdateUserServlet")
public class PrepareUpdateUserServlet extends HttpServlet {
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
    public PrepareUpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// prendo l'id dalla pagina
		String idDaAggiornare = request.getParameter("idDaAggiornare");
		
		// valido l'id, se non è valido invalido la sessione
		if (idDaAggiornare == null || idDaAggiornare == "" || !StringUtils.isNumeric(idDaAggiornare)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
			return;
		}
		
		// se arrivo qui l'id va bene
		Long id = Long.parseLong(idDaAggiornare);

		User user = new User();
		user = userService.findById(id);
		
		if(user == null) {
			HttpSession session = request.getSession();
		    session.invalidate();
		    response.sendRedirect("login.jsp");
		}
		
		//lista degli stati 
		List<Stato> listaStati = new ArrayList<>();
		listaStati.add(Stato.ATTIVO);
		listaStati.add(Stato.DISABILITATO);
		listaStati.add(Stato.CREATO);
		listaStati.add(Stato.EMPTY);
		
		List<Ruolo> listaRuoli = ruoloService.listAllRuoli();
		
		request.setAttribute("user", user);
		request.setAttribute("listaStati", listaStati);
		request.setAttribute("listaRuoli", listaRuoli);
		
		request.getRequestDispatcher("/user/updateUser.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
