package forageria;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Matthieu
 */
public class ForagerIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            IA nouvelleIA = new IA();
            nouvelleIA.start();
        } catch (IOException ex) {
            System.err.println("Problème de connexion avec le serveur");
        }
    }
    
}
