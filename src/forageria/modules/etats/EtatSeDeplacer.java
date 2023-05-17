package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.actions.Action;
import forageria.metier.actions.FabriqueAction;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

public class EtatSeDeplacer extends Etat {
    private ArrayList<TypeMouvement> chemin;

    public EtatSeDeplacer(ModuleDecision module, ArrayList<TypeMouvement> chemin) {
        super(module);
        this.chemin = chemin;
    }

    @Override
    public Etat transition() {
        Etat e = new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * Permet d'ajouter les actions afin de se rendre a la zone du fourneau
     */
    @Override
    public void action() {
        for (TypeMouvement mouvement:this.chemin
        ) {
            Action action = FabriqueAction.creerMouvement(mouvement);
            this.getModule().ajouterAction(action);
            this.chemin.remove(action);
        }

    }
}
