package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servlet/voirCookies")
public class GererComptes extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String nomRecu, motPasseRecu;
         String inscription, connexion;
         String nomCookie, motPasseCookie;
         Cookie[] cookies ;
         boolean trouve;

//  ********************************************************************************************        
//  initialisation des diff�rents param�tres possibles.0
//  ********************************************************************************************    
         inscription = request.getParameter("inscription");
         connexion = request.getParameter("connexion");
         nomRecu = request.getParameter("nom");
         motPasseRecu = request.getParameter("mdp");
         nomCookie = nomRecu;
         motPasseCookie = motPasseRecu;
         trouve = false;
//   cas 1    param�tre inscrire  pr�sent
//    Si les informations pass�es sont acceptables (nom et mot de passe > 4 caract�res)
//      un cookie est cr�� avec comme nom, le nom pass� et comme valeur, le mot de passe pass�
//      puis appel � la servlet "Renseigner" pour la connexion avec les param�tres inscriptionFaite � true et le nom pass�
//    sinon appel � la servlet "Renseigner" pour l'inscription avec le param�tre erreur
//  ********************************************************************************************            
       if(inscription!=null && inscription.equals("inscrire")){
    	   if(nomCookie!=null && motPasseCookie!=null){
    		   Cookie cookie = new Cookie(nomCookie, motPasseCookie);
    		   cookie.setMaxAge(60000);
    		   response.addCookie(cookie);
    		   response.sendRedirect("formulaire?demande=connexion");
    	   }
       }
//   cas 2    param�tre connecter  pr�sent
//     si le parametre nom est absent appel � la servlet Renseigner avec demande = connexion
//     autrement, on v�rifie que le nom et le mot de passe pass�s se trouvent dans un cookie
//     
//      si oui  appel � la servlet "EntreeMagasinDisque" avec comme param�tre  le nom pass�
//      sinon appel � la servlet "Renseigner" pour la connexion avec le param�tre erreur
//  ********************************************************************************************            
       if(connexion!=null && connexion.equals("connexion")){
    	  if(nomRecu!=null && motPasseRecu!=null){
    		  cookies = request.getCookies();
    		 if(cookies!=null && cookies.length>0){
    			 for (Cookie cookie : cookies) {
					if(cookie.getName().equals(nomRecu) && cookie.getValue().equals(motPasseRecu)){
						trouve = true;
					}
    	    	}
    			 if(trouve){
    				 response.sendRedirect("entre?nom="+nomRecu);
    			 }else{
    				 response.sendRedirect("formulaire?demande=connexion&erreur=ereur");
    			 }
    			 
    			 
    		 }
    	  }
       }
        }       
  
     // doGet(HttpServletRequest
         
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
      { 
         doGet(request, response);
      }   

}
