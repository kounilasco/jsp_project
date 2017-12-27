package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
public class EntreeMagasinDisque extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	   {
			  
	     String nomRecu;
	     HttpSession session;
	//  ********************************************************************************************        
	//  Cr�ez deux variables de session : � nom � qui a pour valeur le nom de l�utilisateur  
	//  et � LeStock �  qui a pour valeur une instance de la classe Stock, 
	//  et appeler la servlet  AfficherLesDisques.java
	//  ********************************************************************************************
	     nomRecu = request.getParameter("nom");
	     if(nomRecu!=null){
	    	 session = request.getSession();
	    	 session.setAttribute("nomClient", nomRecu);
		     Stock stock = new Stock();
		     session.setAttribute("stockCourant", stock);
		     
		     
		     response.sendRedirect("achat");
	     }
	     
	     
	     
	     
	     
  //  ********************************************************************************************
		}
   
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
	      { 
	         doGet(request, response);
	      }
}
