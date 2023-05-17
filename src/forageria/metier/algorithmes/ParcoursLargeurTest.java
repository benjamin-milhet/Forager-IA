package forageria.metier.algorithmes;

import static forageria.metier.TypeMouvement.*;
import static forageria.metier.TypeMouvement.TOP;
import static org.junit.Assert.assertEquals;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.Carte;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.cases.Case;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Matthieu
 */
public class ParcoursLargeurTest {

    /**
     * Test of calculerDistancesDepuis method, of class ParcoursLargeur.
     */
    @Test
    public void testCalculerDistancesDepuis() {
        System.out.println("calculerDistancesDepuis");
        Carte carte = new Carte(
                "HWWWH"
                        +"HRRRH"
                        +"HTTTH"
                        +"HHHHH"
                        +"HWWWH"
        );

        Case depart = carte.getCase(new Coordonnee(0,0));
        ParcoursLargeur instance = new ParcoursLargeur(carte);
        instance.calculerDistancesDepuis(depart);


        assertEquals(new Integer(0), instance.getDistance(carte.getCase(new Coordonnee(0,0))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(0,1))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(0,2))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(0,3))));
        assertEquals(new Integer(10), instance.getDistance(carte.getCase(new Coordonnee(0,4))));

        assertEquals(new Integer(1), instance.getDistance(carte.getCase(new Coordonnee(1,0))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(1,1))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(1,2))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(1,3))));
        assertEquals(new Integer(9), instance.getDistance(carte.getCase(new Coordonnee(1,4))));

        assertEquals(new Integer(2), instance.getDistance(carte.getCase(new Coordonnee(2,0))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(2,1))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(2,2))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(2,3))));
        assertEquals(new Integer(8), instance.getDistance(carte.getCase(new Coordonnee(2,4))));

        assertEquals(new Integer(3), instance.getDistance(carte.getCase(new Coordonnee(3,0))));
        assertEquals(new Integer(4), instance.getDistance(carte.getCase(new Coordonnee(3,1))));
        assertEquals(new Integer(5), instance.getDistance(carte.getCase(new Coordonnee(3,2))));
        assertEquals(new Integer(6), instance.getDistance(carte.getCase(new Coordonnee(3,3))));
        assertEquals(new Integer(7), instance.getDistance(carte.getCase(new Coordonnee(3,4))));

        assertEquals(new Integer(4), instance.getDistance(carte.getCase(new Coordonnee(4,0))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(4,1))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(4,2))));
        assertEquals(null, instance.getDistance(carte.getCase(new Coordonnee(4,3))));
        assertEquals(new Integer(8), instance.getDistance(carte.getCase(new Coordonnee(4,4))));
    }


    /**
     * Test of calculerDistancesDepuis method, of class ParcoursLargeur.
     */
    @Test
    public void testGetChemin() {
        System.out.println("getChemin");
        Carte carte = new Carte(
                "HWWWH"
                        +"HRRRH"
                        +"HTTTH"
                        +"HHHHH"
                        +"HWWWH"
        );

        Case depart = carte.getCase(new Coordonnee(0,0));
        ParcoursLargeur instance = new ParcoursLargeur(carte);
        instance.calculerDistancesDepuis(depart);

        ArrayList<TypeMouvement> result = instance.getChemin(carte.getCase(new Coordonnee(0,4)));
        TypeMouvement[] expResultArray = {BOTTOM,BOTTOM,BOTTOM,RIGHT,RIGHT,RIGHT,RIGHT,TOP,TOP,TOP};
        ArrayList<TypeMouvement> expResult = new ArrayList<>(Arrays.asList(expResultArray));

        assertEquals(expResult,result);

    }

}