package forageria.modules.etats;

import forageria.metier.actions.FabriqueAction;
import forageria.modules.ModuleDecision;

import static forageria.metier.actions.TypeDemande.WAIT;

/**
 *
 * @author Benjamin Milhet
 */
public class EtatAttendre extends Etat {
    public EtatAttendre(ModuleDecision module) {
        super(module);
    }

    @Override
    public Etat transition() {
        Etat e = new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * Demande au serveur d'attendre
     */
    @Override
    public void action() {
        this.getModule().ajouterAction(FabriqueAction.creerDemande(WAIT));
    }
}
