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
 * Servlet implementation class RicaricaCreditoServlet
 */
@WebServlet("/RicaricaCreditoServlet")
public class RicaricaCreditoServlet extends HttpServlet {
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
    public RicaricaCreditoServlet() {
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
		String creditoDaForm = request.getParameter("credito");
		
		
		if(creditoDaForm == null || creditoDaForm == "" || !StringUtils.isNumeric(creditoDaForm)) {
			request.setAttribute("errorMessagge", "credito non valido");
			request.getRequestDispatcher("/management/ricaricaCredito.jsp").forward(request, response);
			return;
		}
		
		Integer credito = Integer.parseInt(creditoDaForm);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		user.setCredito(user.getCredito()+credito);
		
		userService.update(user);
		
		request.setAttribute("successMessage", "credito ricaricato");
		request.getRequestDispatcher("/management/managementHome.jsp").forward(request, response);
		
		
	}

}
