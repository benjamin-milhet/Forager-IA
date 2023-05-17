package forageria.metier.algorithmes;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.Carte;
import forageria.metier.carte.cases.Case;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/**
 *class extends de AlgorithmeCalculDistance
 * @author Benjamin Milhet
 */
public class ParcoursLargeur extends AlgorithmeCalculDistance {
    public ParcoursLargeur(Carte carte) {
        super(carte);
    }

    /**
     * Permet de calculer la distance entre 2 cases
     * @param depart : case de depart a laquelle il faut calculer la distance
     */
    @Override
    public void calculerDistancesDepuis(Case depart) {
        //Remise a zero
        ArrayList<Case> aTraiter = new ArrayList<>();
        this.reinitialisationDistances();
        //Initialisation
        aTraiter.add(depart);
        this.setDistance(depart,0);

        //Calcul
        while (!aTraiter.isEmpty()){
            Case caseEnCours = aTraiter.get(0);
            aTraiter.remove(0);
            for (Case v:caseEnCours.getVoisins()
                 ) {
                if(this.getDistance(v) == null){
                    if(v.estAccessible()){
                        this.setDistance(v,this.getDistance(caseEnCours)+1);
                        aTraiter.add(v);

                    }
                }
            }
        }

    }

    /**
     * Permet de recuperer les mouvements necessaire afin de fait le mouvement d'une case a une autre
     * @param arrivee : case qu'il faut atteindre
     * @return les mouvements a faire pour le chemin
     */
    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        //Initialisation
        ArrayList<TypeMouvement> resultat = new ArrayList<>();
        Case caseEnCours = arrivee;
        Case casePrecedente = null;

        //Calcul
        if(this.getDistance(caseEnCours) != null){
            while(this.getDistance(caseEnCours)>0){
                for (Case v : caseEnCours.getVoisins()) {
                    if (this.getDistance(v) != null) {
                        if (this.getDistance(v) == (this.getDistance(caseEnCours) - 1)) {
                            casePrecedente = v;
                        }
                    }
                }
                casePrecedente.getMouvementPourAller(caseEnCours);
                resultat.add(casePrecedente.getMouvementPourAller(caseEnCours));
                caseEnCours = casePrecedente;
            }
            Collections.reverse(resultat);
        }
        return resultat;
    }


}
