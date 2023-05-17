package forageria.modules.etats;

import forageria.metier.actions.FabriqueAction;
import forageria.modules.ModuleDecision;

import static forageria.metier.actions.TypeDemande.MAP;

/**
 *
 * @author Benjamin Milhet
 */
public class EtatCarte extends Etat {
    public EtatCarte(ModuleDecision module) {
        super(module);
    }

    @Override
    public Etat transition() {
        Etat e =  new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * Demande au serveur la carte
     */
    @Override
    public void action() {
        this.getModule().ajouterActionPrioritaire(FabriqueAction.creerDemande(MAP));
    }
}
