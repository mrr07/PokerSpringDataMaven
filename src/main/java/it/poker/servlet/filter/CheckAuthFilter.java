package it.poker.servlet.filter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.poker.model.ruolo.Ruolo;
import it.poker.model.user.User;



@WebFilter(filterName = "CheckAuthFilter", urlPatterns = { "/*" })
public class CheckAuthFilter implements Filter {

	private static final String HOME_PATH = "";
	
	// tutti i percorsi che il filtro deve escludere
	private static final String[] EXCLUDED_URLS = {"/login.jsp","/LoginServlet","/LogoutServlet","/assets/","/navbar.jsp"
			,"/header.jsp","/footer.jsp","/signin.jsp","PrepareInsertUserServlet","ExecuteInsertUserServlet"};
	
	// tutti i percorsi a cui solo l'admin può accedere
	private static final String[] PROTECTED_URLS = {"/user/"};

	public CheckAuthFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		//prendo il path della request che sta passando in questo momento
		String pathAttuale = httpRequest.getServletPath();
		
		//vediamo se il path risulta tra quelli 'liberi di passare'
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);
		
		//se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {
			User utenteInSession = (User)httpRequest.getSession().getAttribute("user");
			//intanto verifico se utente in sessione
			if (utenteInSession == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath());
				return;
			}
			
			Set<Ruolo> listaRuoliUser = utenteInSession.getRuoli();
			boolean admin = false;
			boolean classic = false;
			boolean special = false;
			
			for(Ruolo ruolo : listaRuoliUser) {
				if(ruolo.getNome() == "Amministratore")
					admin = true;
				if(ruolo.getNome() == "Giocatore Speciale")
					special = true;
				if(ruolo.getNome() == "Giocatore Semplice")
					classic = true;
			}
			
			
			//controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if(isPathForOnlyAdministrators(pathAttuale) && !admin) {
				
				httpRequest.getRequestDispatcher("/home.jsp").forward(httpRequest, httpResponse);
				return;
			}
		}

		chain.doFilter(request, response);
	}
	
	private boolean isPathInWhiteList(String requestPath) {
		//bisogna controllare che se il path risulta proprio "" oppure se 
		//siamo in presenza un url 'libero'
		if(requestPath.equals(HOME_PATH))
			return true;
		
		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}

}
