package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.actions.Action;
import forageria.metier.actions.FabriqueAction;
import forageria.metier.algorithmes.AlgorithmeCalculDistance;
import forageria.metier.algorithmes.Dijkstra;
import forageria.metier.algorithmes.DijkstraBatiment;
import forageria.metier.carte.Carte;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.cases.Case;
import forageria.metier.carte.ressources.TypeMateriau;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

public class EtatCrafterAuFourneau extends Etat {
    private TypeMateriau materiau;

    public EtatCrafterAuFourneau(ModuleDecision module, TypeMateriau materiau) {
        super(module);
        this.materiau = materiau;
    }

    @Override
    public Etat transition() {
        Etat e = new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * Cherche l'emplacement du fourneau, s'y rend puis y craft un matériau
     */
    @Override
    public void action() {
        //On recupere la carte et on lance Dijkstra
        Carte carte = this.getMemoire().getCarte();
        AlgorithmeCalculDistance dijkstraBatiment = new DijkstraBatiment(carte, TypeBatiment.FURNACE);
        dijkstraBatiment.calculerDistancesDepuis(this.getMemoire().getCase());

        //On recherche la case du fourneau la plus proche
        int distanceMin = -1;
        Case fourneauLePlusProche = null;
        for(Coordonnee coo : this.getMemoire().getFourneaux()) {
            Case c = carte.getCase(coo) ;
            if (fourneauLePlusProche == null || dijkstraBatiment.getDistance(c) < distanceMin) {
                distanceMin = dijkstraBatiment.getDistance(c) ;
                fourneauLePlusProche = c ;
            }
        }
            //On s'y rend (on enlève le dernier mouvement pour le replacer par CRAFT)
            ArrayList<TypeMouvement> chemin = dijkstraBatiment.getChemin(fourneauLePlusProche);
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
