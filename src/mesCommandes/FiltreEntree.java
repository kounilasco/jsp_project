package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class FiltreEntree implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String nom, motPasse = null;//
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		Cookie[] cookies = hrequest.getCookies();//
		HttpSession session = hrequest.getSession(); 
		nom = (String)session.getAttribute("nomClient");//
		Stock stockDisponible= (Stock) session.getAttribute("stockCourant");//
		//  ********************************************************************************************        
		//   s�il n�existe pas un cookie dont le nom est celui dans la variable de session � nomClient � 
		//         (vous pouvez utilisez la m�thode �  rechercheCookies � de la classe Util.java)
		//   et qu�il n�existe pas  la variable de session � stockCourant � : appel de la servlet "Renseigner" pour s'inscrire 
		//   Autrement on continue (chain.doFilter).
		//  ********************************************************************************************                   
		if(Util.rechercheCookies(cookies, nom) == null && stockDisponible == null) {
			hresponse.sendRedirect("formulaire?demande=connexion");
			/*System.out.println(nom);
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName());
			}*/
		}
		else {
			chain.doFilter(hrequest, hresponse); 
		}
	 }

	public void destroy() {
		this.filterConfig = null;
	}

}
