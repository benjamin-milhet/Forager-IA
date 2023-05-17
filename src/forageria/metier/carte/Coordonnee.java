/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte;

import forageria.metier.TypeMouvement;

import java.util.Objects;

import static forageria.metier.TypeMouvement.*;

/**
 * Permet de recuperer les coordonnees d'une case
 * @author Benjamin Milhet
 */
public class Coordonnee {
    private int ligne;
    private int colonne;
    
    public Coordonnee(int ligne ,int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }
    
    public int getLigne(){
        return this.ligne;
    }
    
    public int getColonne(){
        return this.colonne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordonnee that = (Coordonnee) o;
        return ligne == that.ligne &&
                colonne == that.colonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne);
    }

    /**
     * permet de connaitre le voisin d'une case suivant le mouvement donne
     * @param mouvement effectue pour recuperer le voisin
     * @return les coordonnees du voisin
     */
    public Coordonnee getVoisin(TypeMouvement mouvement){
        Coordonnee c = new Coordonnee(this.getLigne(),this.getColonne());
        switch(mouvement){
            case LEFT:
                c.colonne -=1;
                break;
            case RIGHT:
                c.colonne +=1;
                break;
            case BOTTOM:
                c.ligne +=1;
                break;
            case TOP:
                c.ligne -=1;
                break;
      
        }
        return c;
    }

    /**
     * permet de savoir quelle mouvement pour aller d'une case a une autre
     * @param destination coordonne de la case ou l'on cherche quelle mouvement il faut effectuer pour l'atteindre
     * @return le mouvement a affectue pour atteindre la case donne en entree
     */
    public TypeMouvement getMouvementPourAller(Coordonnee destination){
        int c = this.colonne - destination.colonne;
        int l = this.ligne - destination.ligne;
        TypeMouvement t;
        
        if (c == -1){
            t = RIGHT;
        }
        else if (c == 1){
            t = LEFT;
            
        }
        else if (l == -1){
            t = BOTTOM;
        }
        else if (l == 1){
            t = TOP;
        }
        else{
            t = null;
            
        }
        
        return t;
    }

    @Override
    public String toString() {
        return "Coordonnee{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                '}';
    }
}
