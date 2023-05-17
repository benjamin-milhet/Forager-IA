package forageria.modules.etats;

import forageria.modules.ModuleDecision;

public class EtatSiteFourneau extends Etat {
    public EtatSiteFourneau(ModuleDecision module) {
        super(module);
    }

    /**
     * Permet de chercher le site du fourneau sinon passe en etat pour le construire
     * @return
     */
    @Override
    public Etat transition() {
        Etat e;
        if (this.getMemoire().getSiteFourneau() == null){
            e = new EtatTrouverSiteFourneau(this.getModule());
        }
        else{
            e = new EtatConstruireFourneau(this.getModule());
        }
        return e;
    }

    @Override
    public void action() {
    }
}
