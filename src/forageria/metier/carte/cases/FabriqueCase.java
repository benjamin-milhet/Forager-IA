/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.cases;

import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.ressources.FabriqueRessource;

/**
 * Permet de creer les Cases en fonction de la lettre donne en entre et si besoin d'y ajouter une ressource
 * @author Benjamin Milhet
 */
public class FabriqueCase {
    
    public static Case creer(Coordonnee coordonnee, Character lettre){
        Case c = null;
        FabriqueRessource FR = new FabriqueRessource();

        switch(lettre){
            case('W'):
                c = new CaseEau(coordonnee);
                break;
            case('H'):
                c = new CaseHerbe(coordonnee);
                break;
            case('T'):
                c = new CaseHerbe(coordonnee);
                c.setRessource(FR.creer(c,lettre));
                break; 
            case('R'):
                c = new CaseHerbe(coordonnee);
                c.setRessource(FR.creer(c,lettre));
                break; 
            case('I'):
                c = new CaseHerbe(coordonnee);
                c.setRessource(FR.creer(c,lettre));
                break; 
            case('G'):
                c = new CaseHerbe(coordonnee);
                c.setRessource(FR.creer(c,lettre));
                break; 

        
        }
        

        
        
        return c;
    }
}
