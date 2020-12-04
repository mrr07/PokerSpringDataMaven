package it.poker.servlet.user;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.poker.model.user.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class PlayServlet
 */
@WebServlet("/PlayServlet")
public class PlayServlet extends HttpServlet {
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
    public PlayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Double segno = Math.random();
		if(segno >= 0.5) {
			segno = 1d;
		} else {
			segno = -1d;
		}
		
		Integer somma = (int)(Math.random()*1000);
		
		Integer risultato =(int)(segno*somma);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Integer nuovoCredito = user.getCredito()+risultato;
		
		if(nuovoCredito < 0) {
			user.setCredito(0);
			request.getRequestDispatcher("/management/managementHome.jsp").forward(request, response);
			return;
		} else {
			user.setCredito(nuovoCredito);
		}
		
		userService.update(user);
		
		request.getRequestDispatcher("/management/play.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
