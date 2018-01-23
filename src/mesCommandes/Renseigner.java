package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/servlet/formulaire")
public class Renseigner extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String demande, nomRecu, erreur, inscriptionFaite;


     //  ********************************************************************************************        
     //  initialisation des diff�rents param�tres possibles
     //  ********************************************************************************************    
         demande = request.getParameter("demande");
         nomRecu = request.getParameter("nom");
         erreur = request.getParameter("erreur");
         inscriptionFaite = request.getParameter("inscriptionFaite");
         

     //  ********************************************************************************************           
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
        // out.println("vous êtes dans la servlet Renseignement, modifiez la");
        // out.println("<p>"+erreur+"</p>");
         
        //  ********************************************************************************************            		 
       	//   cas 1   inscription       param�tre demande = "inscription"        
        //       Si le param�tre "erreur" est pr�sent, informez que les informations transmises ne sont pas acceptables.
     	//   Demandez des informations (nom, mot de passe, email, t�l�phone) par un formulaire
     	//   et rappel de la  servlet   "GererComptes" avec ces informations en param�tre pour enregistrer ces valeurs
        //   le param�tre inscrire est envoy� avec comme valeur inscrire (bouton submit)
        //  ********************************************************************************************       
              if(demande!=null && demande.equals("inscription")){
            	  if(erreur !=null){
            		  out.println("<p style='colore: red'> Une erreur s'est produite : Les informations transmises ne sont pas correctes</p>");
            	  }else{
            		  out.println(" <!DOCTYPE html>");
            		  out.println("<html>");
            		  out.println("<head>");
            		  out.println("<title>Inscription</title>");	
            		  out.println("</head>");
            		  out.println("<body>");
            		  out.println("<form action=\"voirCookies\" method=\"GET\">");	
            		  out.println("<caption>");
            		  out.println("Inscription");
            		  out.println("</caption>");
            		  out.println("<p>");		
            		  out.println("<label for=\"nom\">Nom:</label>");			
            		  out.println("<input type=\"text\" name=\"nom\"/>");			
            		  out.println("</p>");		
            		  out.println("<p>");		
            		  out.println("<label for=\"mdp\"> Mot de passe</label>");			
            		  out.println("<input type=\"password\" name=\"mdp\"/>");			
            		  out.println("</p>");		
            		  out.println("<p>");		
            		  out.println("<label for=\"email\"> E-mail</label>");			
            		  out.println("<input type=\"email\" name=\"email\"/>");			
            		  out.println("</p>");		
//            		  out.println("<p>");		
//            		  out.println("<label for=\"mdp\"> Mot de passe</label>");			
//            		  out.println("<input type=\"password\" name=\"mdp\">");			
//            		  out.println("</p>");		
            		  out.println("<p>");	
            		  out.println("<label for=\"phone\"> Phone</label>");			
            		  out.println("<input type=\"text\" name=\"phone\">");			
            		  out.println("</p>");		
            		  out.println("<p>");		
									
            		  out.println("<input type=\"submit\" name=\"inscription\" value=\"inscrire\">");				
            		  out.println("</p>");			
            		  out.println("</form>");		
            		  out.println("</body>"); 
//            		  out.println("<p>");		
//            		  out.println("<label for=\"mdp\"> Mot de passe</label>");			
//            		  out.println("<input type=\"password\" name=\"mdp\">");			
//            		  out.println("</p>");	);
            		  out.println("</html>");
            		  out.close();
            		  
            	  }
            	  
              }
              
          //  ********************************************************************************************            		 
          //   cas 2   connection     param�tre demande = "connexion"        
          //       Si le param�tre "erreur" est pr�sent, informez que les informations transmises ne sont pas acceptables,
          //     et sortir la valeur de erreur
          //       Si le param�tre inscriptionFaite est pr�sent, il vient de s'inscrire, on rajoute un message comme quoi 
          //    l'inscription  s'est bien r�alis�e et dans le formulaire on initialise le nom avec le nom 
          //       re�u en param�tre.
          //   Demandez des informations (nom, mot de passe) par un formulaire
          //   et rappel de la  servlet   "GererComptes" avec ces informations en param�tres pour v�rifier ces valeurs
          //   le param�tre connecter est envoy� avec comme valeur connecter (bouton submit)
          //  ********************************************************************************************         
    
              else if(demande.equals("connexion")){
            	  if(erreur != null){
            		  out.println("<p style='colore: red'> Nom ou mot de passe incorrect</p>");
            	  }else{
	            	  out.println(" <!DOCTYPE html>");
	        		  out.println("<html>");
	        		  out.println("<head>");
	        		  out.println("<title>Inscription</title>");	
	        		  out.println("</head>");
	        		  out.println("<body>");
	        		  if(inscriptionFaite != null){
	        			  out.println("<div style='colore:green;font-weigth:bold'>");
		        		  out.println("Votre inscription s'est bien réalisée.");
		        		  out.println("</div>");
	        		  }
	        		  out.println("<form action=\"voirCookies\" method=\"POST\">");	
	        		  out.println("<caption>");
	        		  out.println("Connexion");
	        		  out.println("</caption>");
	        		  if(inscriptionFaite != null){
	        			  out.println("<p>");		
		        		  out.println("<label for=\"nom\">Nom:</label>");			
		        		  out.println("<input type=\"text\" name=\"nom\" value=\""+nomRecu+"\"/>");			
		        		  out.println("</p>");	
	        		  }else{
	        			  out.println("<p>");		
		        		  out.println("<label for=\"nom\">Nom:</label>");			
		        		  out.println("<input type=\"text\" name=\"nom\"/>");			
		        		  out.println("</p>");	
	        		  }
	        		  	
	        		  out.println("<p>");		
	        		  out.println("<label for=\"mdp\"> Mot de passe</label>");			
	        		  out.println("<input type=\"password\" name=\"mdp\"/>");			
	        		  out.println("</p>");											
	        		  out.println("<input type=\"submit\" name=\"connexion\" value=\"connexion\">");				
	        		  out.println("</p>");			
	        		  out.println("</form>");		
	        		  out.println("</body>"); 
	        		  out.println("</html>");
	        		  out.close();
            	  }
              }
   }  // doGet(HttpServletRequest
         
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
      { 
         doGet(request, response);
      }   

}
