package it.poker.servlet.tavolo;

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

import it.poker.model.tavolo.Tavolo;
import it.poker.model.user.User;
import it.poker.service.tavolo.TavoloService;

/**
 * Servlet implementation class PrepareUpdateTavoloServlet
 */
@WebServlet("/tavolo/PrepareUpdateTavoloServlet")
public class PrepareUpdateTavoloServlet extends HttpServlet {
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
    public PrepareUpdateTavoloServlet() {
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
		
		Long id = Long.parseLong(idDaAggiornare);
		
		
		
		Tavolo tavoloDaAggiornare = tavoloService.findByIDWithUsers(id);
		
		if(tavoloDaAggiornare.getUsers().isEmpty()) {
			request.setAttribute("tavoloDaAggiornare", tavoloDaAggiornare);
			request.getRequestDispatcher("/tavolo/updateTavolo.jsp").forward(request, response);
			return;
		} else {
			
			HttpSession session = request.getSession();

			User userInSessione = (User) session.getAttribute("user");

			List<Tavolo> listaTavoli = tavoloService.findByIDUserWithGiocatori(userInSessione.getId());
			request.setAttribute("listaTavoliUser", listaTavoli);
			request.setAttribute("errorMessage",
					"Impossibile modificare il tavolo, alcuni giocatori sono ancora attivi sul tavolo da modificare");
			request.getRequestDispatcher("/tavolo/listTavoli.jsp").forward(request, response);

			return;
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
