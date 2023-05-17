/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte;

import forageria.metier.carte.cases.Case;
import java.util.Collection;
import java.util.Random;
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
public class CarteTest {

    @Test
    public void testEstZoneValide1() {
        System.out.println("estZoneValide - 1 -");

        Carte instance = new Carte(
                "HHHHH"+
                        "HHHHH"+
                        "HHHHH"+
                        "HHHHH"+
                        "HHHHH"
        );

        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                if((i==2 || i==3) && (j==1 ||j==2)) {

                    assertEquals(true,instance.estZoneValide(instance.getCase(new Coordonnee(i,j))));
                }
                else {
                    assertEquals(false,instance.estZoneValide(instance.getCase(new Coordonnee(i,j))));
                }
            }
        }
    }

    @Test
    public void testEstZoneValide2() {
        System.out.println("estZoneValide - 2 -");
        String[] carac = {"H","T","R","I","G"};
        Random generateur = new Random();

        for(int numMap=1;numMap<Math.pow(2, 16);numMap++) {
            String binaryMap = Integer.toBinaryString(numMap);
            while(binaryMap.length() < 16) {
                binaryMap = "0"+binaryMap;
            }
            String StringMap = "";
            for(int numCase=0;numCase<16;numCase++) {
                if (binaryMap.charAt(numCase) == '1') {
                    StringMap += "W";
                } else {
                    StringMap += carac[generateur.nextInt(carac.length)];
                }
            }
            Carte instance = new Carte(StringMap);
            for(int i=0;i<4;i++) {
                for(int j=0;j<4;j++) {
                    assertEquals(false,instance.estZoneValide(instance.getCase(new Coordonnee(i,j))));
                }
            }
        }


    }

}
