package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.actions.Action;
import forageria.metier.actions.FabriqueAction;
import forageria.metier.algorithmes.AlgorithmeCalculDistance;
import forageria.metier.algorithmes.DijkstraBatiment;
import forageria.metier.carte.Carte;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.cases.Case;
import forageria.metier.carte.ressources.TypeMateriau;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

public class EtatCrafterALaForge extends Etat {
    private TypeMateriau materiau;

    public EtatCrafterALaForge(ModuleDecision module, TypeMateriau materiau) {
        super(module);
        this.materiau = materiau;
    }

    @Override
    public Etat transition() {
        Etat e = new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * Cherche l'emplacement de la forge, s'y rend puis y craft un matériau
     */
    @Override
    public void action() {
        //On recupere la carte et on lance Dijkstra
        Carte carte = this.getMemoire().getCarte();
        AlgorithmeCalculDistance dijkstraBatiment = new DijkstraBatiment(carte, TypeBatiment.FORGE);
        dijkstraBatiment.calculerDistancesDepuis(this.getMemoire().getCase());

        //On recherche la case du fourneau la plus proche
        int distanceMin = -1;
        Case forgeLaPlusProche = null;
        for(Coordonnee coo : this.getMemoire().getForges()) {
            Case c = carte.getCase(coo) ;
            if (forgeLaPlusProche == null || dijkstraBatiment.getDistance(c) < distanceMin) {
                distanceMin = dijkstraBatiment.getDistance(c) ;
                forgeLaPlusProche = c ;
            }
        }
        //On s'y rend (on enlève le dernier mouvement pour le replacer par CRAFT)
        ArrayList<TypeMouvement> chemin = dijkstraBatiment.getChemin(forgeLaPlusProche);
        TypeMouvement dernierMouvement = chemin.get(chemin.size()-1);
        chemin.remove(chemin.size()-1);

        for(TypeMouvement mouvement : chemin) {
            Action action = FabriqueAction.creerMouvement(mouvement);
            this.getModule().ajouterAction(action);
        }

        Action action = FabriqueAction.creerCraft(dernierMouvement,materiau);
        this.getModule().ajouterAction(action);
    }
}
