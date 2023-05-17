/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.ressources;

import forageria.metier.carte.cases.Case;
import static forageria.metier.carte.ressources.TypeRessource.ARBRE;
import static forageria.metier.carte.ressources.TypeRessource.FER;
import static forageria.metier.carte.ressources.TypeRessource.OR;
import static forageria.metier.carte.ressources.TypeRessource.ROCHER;

/**
 * ajoute la ressource correspondante en fonction de la lattre donne
 * @author Benjamin Milhet
 */
public class FabriqueRessource {

    public static Ressource creer(Case position, Character lettre){
        Ressource RT;
        switch(lettre){
            case('T'):
                RT = new RessourceArbre(position);
                break;
            case('R'):
                RT = new RessourceRocher(position);
                break;
            case('I'):
                RT = new RessourceFer(position);
                break;
            case('G'):
                RT = new RessourceOr(position);
                break;
            default:
                RT = null;
                break;
                      
                
            
        }
        return RT;
    }

   
}
