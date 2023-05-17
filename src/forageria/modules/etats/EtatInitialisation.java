package forageria.modules.etats;

import forageria.metier.actions.FabriqueAction;
import forageria.modules.ModuleDecision;

import static forageria.metier.actions.TypeDemande.PLAYER;

/**
 * @author Benjamin Milhet
 */
public class EtatInitialisation extends Etat {
    public EtatInitialisation(ModuleDecision module) {
        super(module);
    }

    @Override
    public Etat transition() {
        Etat e = new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * Demande au serveur la position du joueur
     */
    @Override
    public void action() {
        this.getModule().ajouterAction(FabriqueAction.creerDemande(PLAYER));
    }
}
