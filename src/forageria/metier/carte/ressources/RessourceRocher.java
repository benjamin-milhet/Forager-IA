/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.ressources;

import forageria.metier.carte.cases.Case;

import java.util.ArrayList;

import static forageria.metier.carte.ressources.TypeRessource.ROCHER;

/**
 * creer une ressource ROCHER
 * @author Benjamin Milhet
 */
public class RessourceRocher extends Ressource{
    TypeRessource r;
    public RessourceRocher(Case position) {
        super(position);
        this.r = ROCHER;
    }

    @Override
    public TypeRessource getType() {
        return this.r;
    }

    @Override
    public int nombreCoupsPioche() {
        int i = 2;
        return i;
    }

    @Override
    public ArrayList<TypeMateriau> getLoot() {
        ArrayList<TypeMateriau> typeMateriaus = new ArrayList<>();
        typeMateriaus.add(TypeMateriau.ROCK);
        return typeMateriaus;
    }
}
