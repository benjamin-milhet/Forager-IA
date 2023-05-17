/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.ressources;

import forageria.metier.carte.cases.Case;

import java.util.ArrayList;

import static forageria.metier.carte.ressources.TypeRessource.OR;

/**
 * creer une ressource OR
 * @author Benjamin Milhet
 */
public class RessourceOr extends Ressource{
    TypeRessource r;
    public RessourceOr(Case position) {
        super(position);
        this.r = OR;
    }

    @Override
    public TypeRessource getType() {
        return this.r;
    }

    @Override
    public int nombreCoupsPioche() {
        int i = 3;
        return i;
    }

    @Override
    public ArrayList<TypeMateriau> getLoot() {
        ArrayList<TypeMateriau> typeMateriaus = new ArrayList<>();
        typeMateriaus.add(TypeMateriau.GOLD);
        typeMateriaus.add(TypeMateriau.GOLD);
        return typeMateriaus;
    }


}
