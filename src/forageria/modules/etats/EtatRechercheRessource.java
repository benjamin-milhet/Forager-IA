package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.algorithmes.AlgorithmeCalculDistance;
import forageria.metier.algorithmes.Dijkstra;
import forageria.metier.carte.Carte;
import forageria.metier.carte.cases.Case;
import forageria.metier.carte.ressources.Ressource;
import forageria.metier.carte.ressources.TypeRessource;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

/**
 * @author Benjamin Milhet
 */
public class EtatRechercheRessource extends Etat {
    private ArrayList<TypeMouvement> chemin;
    private Ressource ressource;
    private ArrayList<TypeRessource> ressourcesRecherches;

    public EtatRechercheRessource(ModuleDecision module, ArrayList<TypeRessource> ressourcesRecherches) {
        super(module);
        this.chemin = new ArrayList<>();
        this.ressourcesRecherches = ressourcesRecherches;

    }

    /**
     * Change d'état suivant si il a trouver le chemin pour atteindre la ressource ou non
     * @return le nouvel état
     */
    @Override
    public Etat transition() {
        Etat e;
        if (this.chemin == null){
            e = new EtatAttendre(this.getModule());
        }
        else{
            e = new EtatCollecter(this.getModule(),this.chemin,this.ressource);
        }
        return e;
    }

    /**
     * Cherche le chemin le plus court vers un arbre afin de recolter une ressource arbre
     */
    @Override
    public void action() {
        Carte carte = this.getMemoire().getCarte();
        AlgorithmeCalculDistance ACD = new Dijkstra(carte);
        ACD.calculerDistancesDepuis(this.getMemoire().getCase());

        int distanceMin = -1;
        Case arbreLePlusProche = null;

        for (Case C:carte.getCases()
             ) {
            if (C.getRessource() !=null && this.ressourcesRecherches.contains(C.getRessource().getType())){
                if(arbreLePlusProche == null || ACD.getDistance(C) < distanceMin){
                    distanceMin = ACD.getDistance(C);
                    arbreLePlusProche = C;
                    }
            }
        }

        if (arbreLePlusProche != null){
            this.ressource = arbreLePlusProche.getRessource();
            this.chemin = ACD.getChemin(arbreLePlusProche);
        }

    }
}
