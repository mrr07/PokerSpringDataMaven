package it.poker.servlet.user;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.dto.UserDTO;
import it.poker.model.user.Stato;
import it.poker.model.user.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ExecuteInsertUserServlet
 */
@WebServlet("/ExecuteInsertUserServlet")
public class ExecuteInsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
    private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public ExecuteInsertUserServlet() {
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
		
		// dati dalla form
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setNome(nome);
		userDTO.setCognome(cognome);
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		
		List<String> userErrors = userDTO.validazioneRegistrazione();
		
		if(!userErrors.isEmpty()) {
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("/signin.jsp").forward(request, response);
			return;
		}
		
		User userDaInserire = new User();
		userDaInserire.setNome(nome);
		userDaInserire.setCognome(cognome);
		userDaInserire.setUsername(username);
		userDaInserire.setPassword(password);
		
		Date dataInserimento = new Date();
		userDaInserire.setDataRegistrazione(dataInserimento);
		
		userDaInserire.setEsperienza(0);
		userDaInserire.setCredito(0);
		userDaInserire.setStato(Stato.CREATO);
		
		userDaInserire.setTavolo(null);
		userDaInserire.setRuoli(null);
		userDaInserire.setTavoli(null);
		
		// aggiungo il nuovo user
		userService.addNew(userDaInserire);
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
		
	}

}
