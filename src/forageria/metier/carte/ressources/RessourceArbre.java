/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.ressources;

import forageria.metier.carte.cases.Case;

import java.util.ArrayList;

import static forageria.metier.carte.ressources.TypeRessource.ARBRE;

/**
 * creer une ressource ARBRE
 * @author Benjamin Milhet
 */
public class RessourceArbre extends Ressource{
    TypeRessource r;
    public RessourceArbre(Case position) {
        super(position);
        this.r = ARBRE;
    }



    @Override
    public TypeRessource getType() {
        return this.r;
    }

    @Override
    public int nombreCoupsPioche() {
        int i = 1;
        return i;
    }


    @Override
    public ArrayList<TypeMateriau> getLoot() {
        ArrayList<TypeMateriau> typeMateriaus = new ArrayList<>();
        typeMateriaus.add(TypeMateriau.TREE);
        return typeMateriaus;
    }

}
