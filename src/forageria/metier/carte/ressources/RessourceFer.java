/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.ressources;

import forageria.metier.carte.cases.Case;

import java.util.ArrayList;

import static forageria.metier.carte.ressources.TypeRessource.FER;

/**
 * creer une ressource FER
 * @author Benjamin Milhet
 */
public class RessourceFer extends Ressource{
    TypeRessource r;
    public RessourceFer(Case position) {
        super(position);
        this.r = FER;
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
        typeMateriaus.add(TypeMateriau.IRON);
        typeMateriaus.add(TypeMateriau.IRON);
        return typeMateriaus;
    }
}
