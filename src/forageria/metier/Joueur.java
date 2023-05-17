package forageria.metier;

import forageria.metier.carte.Coordonnee;
/**
 * class du joueur, elle permet de deplacer le joueur
 * @author Benjamin Milhet
 */
public class Joueur {
    private Coordonnee coordonnee;

    public Joueur(Coordonnee coordonnee){
        this.coordonnee = coordonnee;

    }

    /**
     * permet de deplacer le joueur sur une case voisine
     * @param mouvement a effectuer par le joueur
     */
    public void deplacer(TypeMouvement mouvement){
        this.coordonnee = this.coordonnee.getVoisin(mouvement);
    }

    public Coordonnee getCoordonnee() {
        return this.coordonnee;
    }

    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }
}
