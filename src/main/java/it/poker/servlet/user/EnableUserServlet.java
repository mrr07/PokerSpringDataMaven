package it.poker.servlet.user;

import java.io.IOException;
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

import it.poker.model.user.Stato;
import it.poker.model.user.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class PrepareEnableUserServlet
 */
@WebServlet("/EnableUserServlet")
public class EnableUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
    public EnableUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// prendo l'id dalla pagina
		String idDaAttivare = request.getParameter("idDaAttivare");

		// valido l'id, se non è valido invalido la sessione
		if (idDaAttivare == null || idDaAttivare == "" || !StringUtils.isNumeric(idDaAttivare)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
			return;
		}

		Long id = Long.parseLong(idDaAttivare);

		User user = new User();
		user = userService.findById(id);

		// imposto lo stato su ATTIVO	
		user.setStato(Stato.ATTIVO);

		// attivo l'utente
		userService.update(user);

		List<User> listaUsers = userService.listAllUsersWithTavoliAndRuoli();
		request.setAttribute("successMessage", "L'user è stato disabilitato correttamente");
		request.setAttribute("listaUsers", listaUsers);
		request.getRequestDispatcher("/user/gestioneUsers.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
