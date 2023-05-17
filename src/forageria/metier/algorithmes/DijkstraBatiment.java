package forageria.metier.algorithmes;

import forageria.metier.carte.Carte;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.cases.Case;
import forageria.metier.carte.cases.TypeCase;

public class DijkstraBatiment extends Dijkstra {
    private TypeBatiment typeBatiment;

    /**
     * Recupere la carte donne en parametre et initialise la HashMap
     *
     * @param carte du jeux
     */
    public DijkstraBatiment(Carte carte, TypeBatiment typeBatiment) {
        super(carte);
        this.typeBatiment = typeBatiment;
    }

    /**
     * Permet de calculer le cout pour aller a une case en prenant compte des batiments
     * @param destination on cherche le nombre de mouvement pour atteindre cette case
     * @return le nombre de cout et mouvement
     */
    protected int coutMouvementVers(Case destination){
        int nbDeplacement = 0;

        if (destination.getBatiment() != null && destination.getBatiment() != this.typeBatiment){
            nbDeplacement = this.getInfini();
        }
        else if(destination.getType() == TypeCase.HERBE){
            nbDeplacement = 1;
            if(destination.getRessource()!=null) {
                nbDeplacement += destination.getRessource().nombreCoupsPioche();
            }


        }
        else {
            nbDeplacement = this.getInfini();
        }
        return nbDeplacement;
    }
}
