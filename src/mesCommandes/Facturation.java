package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Facturation
 */
@WebServlet("/servlet/facturer")
public class Facturation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private EnregistrerCommande accesBase = new EnregistrerCommande();
	Connection connexion=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String nom = (String) session.getAttribute("nomClient");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		this.commandesBase(nom, out, request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	protected void commandesBase(String nom, PrintWriter out, String repertoire)
	{
		ResultSet rset = null;
	       //Disque leDisque=null;
	       int cle =0;
	       try {
 
	    	   this.OuvreBase();
	    	   cle = this.ObtenirReferenceDuClient(nom);
	    	   
	    	   this.pstmt = this.connexion.prepareStatement("SELECT * FROM commande WHERE client= ?");
	    	   this.pstmt.setInt(1, cle);
	    	   
	    	   rset = this.pstmt.executeQuery(); 	   
	    	   this.afficherFacture(rset, out, repertoire);
	    	   this.fermeBase();
	    	   
	    	   
	    	   
	       //  ********************************************************* 
	       }           
	           catch (Exception E) {   
		           out.println("erreur base");         
	               log(" - probeme ajoute " + E.getClass().getName() );
	               E.printStackTrace();
	  }	  
		
	}

    protected void OuvreBase() {
        try {
           Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
           connexion = DriverManager.getConnection(  "jdbc:mysql://localhost/magasin","root","ChuChu@de1");
           connexion.setAutoCommit(true);
           stmt = connexion.createStatement();
        }
            catch (Exception E) {         
              log(" -------- problème  " + E.getClass().getName() );
              E.printStackTrace();
           }	
     }
  
      protected void fermeBase() {
        try {
           stmt.close();        
           connexion.close();         
        }
            catch (Exception E) {         
              log(" -------- problème  " + E.getClass().getName() );
              E.printStackTrace();
           }	
     }
      
      protected int ObtenirReferenceDuClient(String nomClient){
     	 int reference = -1;
     	   try {
     	          ResultSet rset = null;  	          
     	          pstmt= connexion.prepareStatement("select id from client where nom=?");
     	          pstmt.setString(1,nomClient);
     	          rset=pstmt.executeQuery();
     	          if (rset.next()) {
     	        	  reference = rset.getInt("id");
     	          }   	           	          
     	             
     	       	}
     	       
     	           catch (Exception E) {
     	             log(" - probeme  " + E.getClass().getName() );
     	             E.printStackTrace();
     	          }
     	   
     	   return reference;
      }
      
      protected void afficherFacture(ResultSet rs, PrintWriter out, String repertoire)
      {
    	  Disque leDisque; 
    	  int prixTotal = 0;
    	  
    	  out.println("<html>");
          out.println("<head>");
          out.println("<title>  votre fcture </title>");
          out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
          out.println("</head>");
    	  out.println("<table border=1>");
    	  out.println("<tr>");
    	  out.println("<th>Article</th>");
    	  out.println("<th>Prix unitaire</th>");
    	  out.println("</tr>");
    	 
    	  
    	  try {
			while(rs.next()){
				leDisque = Stock.trouveDisque(rs.getString("nomarticle")); 
				 out.println("<tr>");				
				 out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> "); 
				 out.println("<td> "+ leDisque.getPrix() +" euros </td>");
	    	  	 out.println("</tr>");
	    	  	 prixTotal += leDisque.getPrix();
			  }
			out.println("<tr>"
					+ "<td>"
					+ " Total"
					+ "</td>"
					+ "<td>"
					+ prixTotal
					+ " euros</td>"
					+ "</tr>");
			
			out.println("</body>");
	          out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	 
      }
}
