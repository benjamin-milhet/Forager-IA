package forageria.modules.etats;

import forageria.modules.ModuleDecision;

public class EtatSiteForge extends Etat {
    public EtatSiteForge(ModuleDecision module) {
        super(module);
    }
    /**
     * Permet de chercher le site de la forge sinon passe en etat pour le construire
     * @return
     */
    @Override
    public Etat transition() {
        Etat e;
        if (this.getMemoire().getSiteForge() == null){
            e = new EtatTrouverSiteForge(this.getModule());
        }
        else{
            e = new EtatConstruireForge(this.getModule());
        }
        return e;
    }

    @Override
    public void action() {
    }
}
