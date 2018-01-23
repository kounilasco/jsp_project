package mesCommandes;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
class Util {
         
static public   String rechercheCookies(Cookie[] lescookies, String nom)  {
         String reponse =null;
         for (Cookie cookie : lescookies) {
			if(identique(cookie.getName(),nom)) {
				reponse = cookie.getValue();
			}
		}
               //  ********************************************************************************************        
               //  recherche si dans le tableau de cookies il en existe un avec le nom donn�.
               //       si oui  la valeur de ce cookie est donn�e en r�sultat (mot de passe)
               //  Cette m�thode sera appel�e par d�autres classes aussi elle est � public � 
               //  et � static � pour pouvoir  l�appeler directement par la classe  "Util.rechercheCookies(..)"
               //  ********************************************************************************************

         
	        //  ********************************************************************************************
        return reponse;
      }


static boolean identique (String recu, String cookie) {
    return ( (cookie != null) && (recu.equals(cookie) ));

}
}