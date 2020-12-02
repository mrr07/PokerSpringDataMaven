package it.poker.servlet.login;

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

import it.poker.dto.UserDTO;
import it.poker.model.tavolo.Tavolo;
import it.poker.model.user.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
    public LoginServlet() {
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
		
		//prendo i valori dalla pagina
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//creo il dto per la validazione
		UserDTO userDTO = new UserDTO(username, password);
		
		//validazione
		List<String> userErrors = userDTO.validazioneLogin();
		if(!userErrors.isEmpty()) {
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//validazione completata se arrivo qui
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		User userDaDB = new User();
		userDaDB = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(userDaDB == null || userDaDB.getStato().name() != "ATTIVO") {
			userErrors.add("Utente non trovato");
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", userDaDB);
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		
		
		
		
	}

}
