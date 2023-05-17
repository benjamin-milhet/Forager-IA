/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.cases;

import forageria.metier.carte.Coordonnee;
import static forageria.metier.carte.cases.TypeCase.HERBE;

/**
 * permet de creer une case HERBE
 * @author Benjamin Milhet
 */
public class CaseHerbe extends Case{
    TypeCase t;
    public CaseHerbe(Coordonnee coordonnee) {
        super(coordonnee);
        this.t = HERBE;
    }
    
    @Override
    public TypeCase getType(){
        return this.t;
    }

    @Override
    public boolean estAccessible() {
        boolean bool = false;
        if(this.estVide()){
            bool = true;
        }
        return bool;
    }
}
