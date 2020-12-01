package it.poker.servlet.user;
import java.io.IOException;

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

import it.poker.model.user.User;
import it.poker.service.user.UserService;

/**
 * Servlet implementation class ShowUserServlet
 */
@WebServlet("/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {
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
    public ShowUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//prendo l'id dalla pagina
		String idDaVisualizzare = request.getParameter("idDaVisualizzare");
		
		//valido l'id, se non è valido invalido la sessione
		if(idDaVisualizzare == null || idDaVisualizzare == "" || !StringUtils.isNumeric(idDaVisualizzare)) {
			HttpSession session = request.getSession();
		    session.invalidate();
		    response.sendRedirect("login.jsp");
		    return;
		}
		
		//se arrivo qui l'id va bene
		Long id = Long.parseLong(idDaVisualizzare);
		
		User user = new User();
		user = userService.findById(id);
		
		if(user == null) {
			HttpSession session = request.getSession();
		    session.invalidate();
		    response.sendRedirect("login.jsp");
		}
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("/user/showUser.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
