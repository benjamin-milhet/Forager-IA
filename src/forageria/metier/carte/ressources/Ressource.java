/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.ressources;

import forageria.metier.carte.cases.Case;

import java.util.ArrayList;

/**
 * class abstraite permettamt d'ajouter une ressource sur une case donne
 * @author Benjamin Milhet
 */
public abstract class Ressource {
    private Case position;
    
    public Ressource (Case position){
        this.position = position;
    }
    
    public abstract TypeRessource getType();

    /**
     *
     * @return nombrer de couts de pioche necessairz afin de récuperer cette ressource
     */
    public abstract int nombreCoupsPioche();

    /**
     *
     * @return les loots données par une ressource
     */
    public abstract ArrayList<TypeMateriau> getLoot();
}
