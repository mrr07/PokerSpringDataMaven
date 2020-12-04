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

import it.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class PrepareDeleteUserServlet
 */
@WebServlet("/user/PrepareDeleteUserServlet")
public class PrepareDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//prendo l'id dalla pagina
		String idDaEliminare = request.getParameter("idDaEliminare");
		
		// valido l'id, se non è valido invalido la sessione
		if (idDaEliminare == null || idDaEliminare == "" || !StringUtils.isNumeric(idDaEliminare)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
			return;
		}
		
		request.setAttribute("idDaEliminare", idDaEliminare);
		request.getRequestDispatcher("/user/confermaEliminazioneUser.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
