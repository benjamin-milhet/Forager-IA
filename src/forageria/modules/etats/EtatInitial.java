package forageria.modules.etats;

import forageria.modules.ModuleDecision;

/**
 * @author Benjamin Milhet
 * Permet de lancer l'automate
 */
public class EtatInitial extends Etat {
    public EtatInitial(ModuleDecision module) {
        super(module);
    }

    @Override
    public Etat transition() {
        Etat e = new EtatInitialisation(this.getModule());
        return e;
    }

    @Override
    public void action() {

    }
}
