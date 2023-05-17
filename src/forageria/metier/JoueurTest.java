package forageria.metier;

import forageria.metier.carte.Coordonnee;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Matthieu
 */
public class JoueurTest {



    /**
     * Test of getCoordonnee method, of class Joueur.
     */
    @Test
    public void testGetCoordonnee() {
        System.out.println("getCoordonnee");
        Coordonnee expResult = new Coordonnee(5,4);
        Joueur instance = new Joueur(expResult);
        Coordonnee result = instance.getCoordonnee();
        assertEquals(expResult, result);
    }


    /**
     * Test of deplacer method, of class Joueur.
     */
    @Test
    public void testDeplacer() {
        System.out.println("deplacer");
        Joueur instance = new Joueur(new Coordonnee(5,4));
        instance.deplacer(TypeMouvement.TOP);
        assertEquals(new Coordonnee(4,4), instance.getCoordonnee());
        instance.deplacer(TypeMouvement.BOTTOM);
        assertEquals(new Coordonnee(5,4), instance.getCoordonnee());
        instance.deplacer(TypeMouvement.LEFT);
        assertEquals(new Coordonnee(5,3), instance.getCoordonnee());
        instance.deplacer(TypeMouvement.RIGHT);
        assertEquals(new Coordonnee(5,4), instance.getCoordonnee());
    }

}
