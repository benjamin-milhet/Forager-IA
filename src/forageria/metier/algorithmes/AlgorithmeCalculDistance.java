package forageria.metier.algorithmes;

//import com.sun.deploy.cache.BaseLocalApplicationProperties;
import forageria.metier.TypeMouvement;
import forageria.metier.carte.Carte;
import forageria.metier.carte.cases.Case;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Benjamin Milhet
 */
public abstract class AlgorithmeCalculDistance {

    private Carte carte;
    private HashMap<Case,Integer> distances;

    /**
     * Recupere la carte donne en parametre et initialise la HashMap
     * @param carte du jeux
     */
    public AlgorithmeCalculDistance(Carte carte){
        this.carte = carte;
        this.distances = new HashMap<>();
    }

    protected Carte getCarte(){
        return this.carte;
    }

    protected void setDistance(Case position, int valeur){
        this.distances.put(position, valeur);
    }

    public Integer getDistance(Case arrivee){
        Integer i = this.distances.get(arrivee);
        return i;
    }

    /**
     * permet de supprimer le contenu de la HashMap : distances
     */
    protected void reinitialisationDistances(){
        this.distances.clear();
    }


    public abstract void calculerDistancesDepuis(Case depart);

    public abstract ArrayList<TypeMouvement> getChemin(Case arrivee);


}
