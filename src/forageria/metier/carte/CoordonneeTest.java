/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte;


import static forageria.metier.TypeMouvement.*;
import static org.junit.Assert.assertEquals;

import forageria.metier.TypeMouvement;
import org.junit.Test;
//import static org.testng.Assert.*;

/**
 *
 * @author Matthieu
 */
public class CoordonneeTest {

    @Test
    public void testGetVoisinTOP() {
        System.out.println("getVoisin(TOP)");
        TypeMouvement mouvement = TOP;
        //First Test
        Coordonnee instance = new Coordonnee(0,0);
        Coordonnee expResult = new Coordonnee(-1,0);
        Coordonnee result = instance.getVoisin(mouvement);
        assertEquals(expResult, result);
        //Second Test
        Coordonnee instance2 = new Coordonnee(32,27);
        Coordonnee expResult2 = new Coordonnee(31,27);
        Coordonnee result2 = instance2.getVoisin(mouvement);
        assertEquals(expResult2, result2);
    }

    @Test
    public void testGetVoisinBOTTOM() {
        System.out.println("getVoisin(BOTTOM)");
        TypeMouvement mouvement = BOTTOM;
        //First Test
        Coordonnee instance = new Coordonnee(0,0);
        Coordonnee expResult = new Coordonnee(1,0);
        Coordonnee result = instance.getVoisin(mouvement);
        assertEquals(expResult, result);
        //Second Test
        Coordonnee instance2 = new Coordonnee(32,27);
        Coordonnee expResult2 = new Coordonnee(33,27);
        Coordonnee result2 = instance2.getVoisin(mouvement);
        assertEquals(expResult2, result2);
    }


    @Test
    public void testGetVoisinLEFT() {
        System.out.println("getVoisin(LEFT)");
        TypeMouvement mouvement = LEFT;
        //First Test
        Coordonnee instance = new Coordonnee(0,0);
        Coordonnee expResult = new Coordonnee(0,-1);
        Coordonnee result = instance.getVoisin(mouvement);
        assertEquals(expResult, result);
        //Second Test
        Coordonnee instance2 = new Coordonnee(32,27);
        Coordonnee expResult2 = new Coordonnee(32,26);
        Coordonnee result2 = instance2.getVoisin(mouvement);
        assertEquals(expResult2, result2);
    }


    @Test
    public void testGetVoisinRIGHT() {
        System.out.println("getVoisin(RIGHT)");
        TypeMouvement mouvement = RIGHT;
        //First Test
        Coordonnee instance = new Coordonnee(0,0);
        Coordonnee expResult = new Coordonnee(0,1);
        Coordonnee result = instance.getVoisin(mouvement);
        assertEquals(expResult, result);
        //Second Test
        Coordonnee instance2 = new Coordonnee(32,27);
        Coordonnee expResult2 = new Coordonnee(32,28);
        Coordonnee result2 = instance2.getVoisin(mouvement);
        assertEquals(expResult2, result2);
    }

    @Test
    public void testGetMouvementPourAllerTOP() {
        System.out.println("getMouvementPourAller(TOP)");
        Coordonnee instance = new Coordonnee(25,32);
        Coordonnee destination = new Coordonnee(24,32);
        TypeMouvement expResult = TOP;
        TypeMouvement result = instance.getMouvementPourAller(destination);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMouvementPourAllerBOTTOM() {
        System.out.println("getMouvementPourAller(BOTTOM)");
        Coordonnee instance = new Coordonnee(25,32);
        Coordonnee destination = new Coordonnee(26,32);
        TypeMouvement expResult = BOTTOM;
        TypeMouvement result = instance.getMouvementPourAller(destination);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMouvementPourAllerLEFT() {
        System.out.println("getMouvementPourAller(LEFT)");
        Coordonnee instance = new Coordonnee(25,32);
        Coordonnee destination = new Coordonnee(25,31);
        TypeMouvement expResult = LEFT;
        TypeMouvement result = instance.getMouvementPourAller(destination);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMouvementPourAllerRIGHT() {
        System.out.println("getMouvementPourAller(RIGHT)");
        Coordonnee instance = new Coordonnee(25,32);
        Coordonnee destination = new Coordonnee(25,33);
        TypeMouvement expResult = RIGHT;
        TypeMouvement result = instance.getMouvementPourAller(destination);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMouvementPourAllerNULL() {
        System.out.println("getMouvementPourAller(NULL)");
        Coordonnee instance = new Coordonnee(25,32);
        //First test
        Coordonnee destination1 = new Coordonnee(0,0);
        TypeMouvement expResult = null;
        TypeMouvement result1 = instance.getMouvementPourAller(destination1);
        assertEquals(expResult, result1);
        //Second test
        Coordonnee destination2 = new Coordonnee(24,31);
        TypeMouvement result2 = instance.getMouvementPourAller(destination1);
        assertEquals(expResult, result2);
        //Third test
        Coordonnee destination3 = new Coordonnee(24,33);
        TypeMouvement result3 = instance.getMouvementPourAller(destination1);
        assertEquals(expResult, result3);
        //Fourth test
        Coordonnee destination4 = new Coordonnee(26,31);
        TypeMouvement result4 = instance.getMouvementPourAller(destination1);
        assertEquals(expResult, result4);
        //Fifth test
        Coordonnee destination5 = new Coordonnee(26,32);
        TypeMouvement result5 = instance.getMouvementPourAller(destination1);
        assertEquals(expResult, result5);
    }
}

