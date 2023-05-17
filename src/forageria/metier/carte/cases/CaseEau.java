/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.cases;

import forageria.metier.carte.Coordonnee;
import static forageria.metier.carte.cases.TypeCase.EAU;

/**
 * permet de creer une case EAU
 * @author Benjamin Milhet
 */
public class CaseEau extends Case{
    TypeCase t;
    public CaseEau(Coordonnee coordonnee) {
        super(coordonnee);
        this.t = EAU;
    }
    
    @Override
    public TypeCase getType(){
        return this.t;
    }

    @Override
    public boolean estAccessible() {
        return false;
    }

}
