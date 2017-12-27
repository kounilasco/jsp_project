package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
class Util {
         
static public   String rechercheCookies(Cookie[] lescookies, String nom)  {
         String reponse =null;
         
               //  ********************************************************************************************        
               //  recherche si dans le tableau de cookies il en existe un avec le nom donné.
               //       si oui  la valeur de ce cookie est donnée en résultat (mot de passe)
               //  Cette méthode sera appelée par d’autres classes aussi elle est « public » 
               //  et « static » pour pouvoir  l’appeler directement par la classe  "Util.rechercheCookies(..)"
               //  ********************************************************************************************

         
	        //  ********************************************************************************************
        return reponse;
      }


static boolean identique (String recu, String cookie) {
    return ( (cookie != null) && (recu.equals(cookie) ));

}
}