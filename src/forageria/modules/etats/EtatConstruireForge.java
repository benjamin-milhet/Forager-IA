package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.actions.FabriqueAction;
import forageria.metier.carte.TypeBatiment;
import forageria.modules.ModuleDecision;

public class EtatConstruireForge extends Etat {
    public EtatConstruireForge(ModuleDecision module) {
        super(module);
    }

    @Override
    public Etat transition() {
        Etat e = new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * Verifie si l'emplacement est bien vide et creer la forge
     */
    @Override
    public void action() {
        this.getModule().ajouterAction(FabriqueAction.creerMouvement(TypeMouvement.RIGHT));
        this.getModule().ajouterAction(FabriqueAction.creerMouvement(TypeMouvement.TOP));
        this.getModule().ajouterAction(FabriqueAction.creerMouvement(TypeMouvement.LEFT));
        this.getModule().ajouterAction(FabriqueAction.creerMouvement(TypeMouvement.BOTTOM));
        this.getModule().ajouterAction(FabriqueAction.creerMouvement(TypeMouvement.LEFT));
        this.getModule().ajouterAction(FabriqueAction.creerConstruire(TypeMouvement.RIGHT, TypeBatiment.FORGE));
    }
}
