package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.algorithmes.AlgorithmeCalculDistance;
import forageria.metier.algorithmes.Dijkstra;
import forageria.metier.carte.Carte;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.cases.Case;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

public class EtatTrouverSiteFourneau extends Etat {
    private ArrayList<TypeMouvement> chemin;
    private AlgorithmeCalculDistance dijkstra;


    public EtatTrouverSiteFourneau(ModuleDecision module) {
        super(module);
        this.chemin = new ArrayList<>();
        this.dijkstra = new Dijkstra(this.getMemoire().getCarte());
        this.dijkstra.calculerDistancesDepuis(this.getMemoire().getCase());
    }

    @Override
    public Etat transition() {
        Etat e;
        if(this.chemin == null){
            e = new EtatAttendre(this.getModule());
        }
        else{
            e = new EtatSeDeplacer(this.getModule(),this.chemin);
        }
        return e;
    }

    /**
     * Permet de calculer la distance entre l'ia et la position du fourneau
     */
    @Override
    public void action() {
        Carte carte = this.getMemoire().getCarte();
        AlgorithmeCalculDistance dijkstra = new Dijkstra(carte);
        dijkstra.calculerDistancesDepuis(this.getMemoire().getCase());

        int coutMin = 1;
        Case zoneRetenue = null;
        for (Case C:carte.getCases()
        ) {
            if (carte.estZoneValide(C)){
                int cout = this.coutZone(C);
                    if (zoneRetenue == null || cout < coutMin) {
                        coutMin = cout;
                        zoneRetenue = C;
                    }

            }

        }
        this.chemin = dijkstra.getChemin(zoneRetenue);
        this.getMemoire().setSiteFourneau(zoneRetenue.getCoordonnee());
    }


    /**
     * cout pour se deblayer la zone
     * @param coinBasGauche
     * @return le cout de ces actions
     */
    private int coutZone(Case coinBasGauche){
        int cout = this.dijkstra.getDistance(coinBasGauche);
        for (int i = 0 ; i < 2 ; i++){
            for (int j = 0 ; j < 2 ; j++){
                if(i != 0 || j != 0){
                    Coordonnee coordonnee = coinBasGauche.getCoordonnee();
                    Case position = this.getMemoire().getCarte().getCase(new Coordonnee(coordonnee.getLigne()-i,coordonnee.getColonne()+j));
                    if(position.getRessource() != null){
                        cout += position.getRessource().nombreCoupsPioche();
                    }
                }
            }
        }
        return cout;
    }
}
