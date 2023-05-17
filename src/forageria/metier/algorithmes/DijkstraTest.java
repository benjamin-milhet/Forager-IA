package forageria.metier.algorithmes;

import static org.junit.Assert.assertEquals;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import forageria.metier.TypeMouvement;
import static forageria.metier.TypeMouvement.*;
import forageria.metier.carte.Carte;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.cases.Case;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author Matthieu
 */
public class DijkstraTest {

    /**
     * Test of calculerDistancesDepuis method, of class Dijkstra.
     */
    @Test
    public void testCalculerDistancesDepuis() {
        System.out.println("calculerDistancesDepuis");
        Carte carte = new Carte(
                "HWWWH"
                        +"HTGTH"
                        +"HTRIH"
                        +"WWHWW"
                        +"WWWWW"
        );

        Case depart = carte.getCase(new Coordonnee(0,0));
        Dijkstra instance = new Dijkstra(carte);
        instance.calculerDistancesDepuis(depart);

        int infini = 1+carte.getTaille()*carte.getTaille()*16;

        assertEquals(new Integer(0), instance.getDistance(carte.getCase(new Coordonnee(0,0))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(0,1))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(0,2))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(0,3))));
        assertEquals(new Integer(11), instance.getDistance(carte.getCase(new Coordonnee(0,4))));

        assertEquals(new Integer(1), instance.getDistance(carte.getCase(new Coordonnee(1,0))));
        assertEquals(new Integer(3), instance.getDistance(carte.getCase(new Coordonnee(1,1))));
        assertEquals(new Integer(7), instance.getDistance(carte.getCase(new Coordonnee(1,2))));
        assertEquals(new Integer(9), instance.getDistance(carte.getCase(new Coordonnee(1,3))));
        assertEquals(new Integer(10), instance.getDistance(carte.getCase(new Coordonnee(1,4))));

        assertEquals(new Integer(2), instance.getDistance(carte.getCase(new Coordonnee(2,0))));
        assertEquals(new Integer(4), instance.getDistance(carte.getCase(new Coordonnee(2,1))));
        assertEquals(new Integer(7), instance.getDistance(carte.getCase(new Coordonnee(2,2))));
        assertEquals(new Integer(11), instance.getDistance(carte.getCase(new Coordonnee(2,3))));
        assertEquals(new Integer(11), instance.getDistance(carte.getCase(new Coordonnee(2,4))));

        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(3,0))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(3,1))));
        assertEquals(new Integer(8), instance.getDistance(carte.getCase(new Coordonnee(3,2))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(3,3))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(3,4))));

        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(4,0))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(4,1))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(4,2))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(4,3))));
        assertEquals(new Integer(infini), instance.getDistance(carte.getCase(new Coordonnee(4,4))));
    }

    /**
     * Test of getChemin method, of class Dijkstra.
     */
    @Test
    public void testGetChemin() {
        System.out.println("getChemin");
        Carte carte = new Carte(
                "HWWWH"
                        +"HTGTH"
                        +"HTRIH"
                        +"WWHWW"
                        +"WWWWW"
        );

        Case depart = carte.getCase(new Coordonnee(0,0));
        Dijkstra instance = new Dijkstra(carte);
        instance.calculerDistancesDepuis(depart);

        System.out.println(" - Chemin vers (0,4)");
        ArrayList<TypeMouvement> result = instance.getChemin(carte.getCase(new Coordonnee(0,4)));
        TypeMouvement[] expResultArray = {BOTTOM,RIGHT,RIGHT,RIGHT,RIGHT,TOP};
        ArrayList<TypeMouvement> expResult = new ArrayList<>(Arrays.asList(expResultArray));
        assertEquals(expResult,result);

        System.out.println(" - Chemin vers (2,4)");
        ArrayList<TypeMouvement> result2 = instance.getChemin(carte.getCase(new Coordonnee(2,4)));
        TypeMouvement[] expResultArray2 = {BOTTOM,RIGHT,RIGHT,RIGHT,RIGHT,BOTTOM};
        ArrayList<TypeMouvement> expResult2 = new ArrayList<>(Arrays.asList(expResultArray2));
        assertEquals(expResult2,result2);

        System.out.println(" - Chemin vers (3,2)");
        ArrayList<TypeMouvement> result3 = instance.getChemin(carte.getCase(new Coordonnee(3,2)));
        TypeMouvement[] expResultArray3 = {BOTTOM,BOTTOM,RIGHT,RIGHT,BOTTOM};
        ArrayList<TypeMouvement> expResult3 = new ArrayList<>(Arrays.asList(expResultArray3));
        assertEquals(expResult3,result3);

        System.out.println(" - Chemin vers (0,2)");
        ArrayList<TypeMouvement> result4 = instance.getChemin(carte.getCase(new Coordonnee(0,2)));
        TypeMouvement[] expResultArray4 = {};
        ArrayList<TypeMouvement> expResult4 = new ArrayList<>(Arrays.asList(expResultArray4));
        assertEquals(expResult4,result4);
    }
}
