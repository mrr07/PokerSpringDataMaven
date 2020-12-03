package it.poker.servlet.tavolo;

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

import it.poker.model.tavolo.Tavolo;
import it.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class DeleteTavoloServlet
 */
@WebServlet("/DeleteTavoloServlet")
public class DeleteTavoloServlet extends HttpServlet {
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
    public DeleteTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// prendo l'id dalla pagina
		String idDaEliminare = request.getParameter("idDaEliminare");
		Long id = Long.parseLong(idDaEliminare);

		// valido l'id, se non è valido invalido la sessione
		if (idDaEliminare == null || idDaEliminare == "" || !StringUtils.isNumeric(idDaEliminare)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
			return;
		}
		
		Tavolo tavoloDaEliminare = tavoloService.findById(id);
		
		tavoloService.delete(tavoloDaEliminare);
		
		request.setAttribute("successMessage", "Eliminazione effettuata");
		request.getRequestDispatcher("/tavolo/listTavoli.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
