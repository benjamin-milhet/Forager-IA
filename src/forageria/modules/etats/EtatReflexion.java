package forageria.modules.etats;

import forageria.modules.ModuleDecision;

/**
 * @author Benjamin Milhet
 */
public class EtatReflexion extends Etat {
    public EtatReflexion(ModuleDecision module) {
        super(module);
    }

    /**
     * Choisit le prochain état en fonction de s'il y a la carte puis réalise les actions de la listedesActionEnCour puis cherche des ressources
     * @return
     */
    @Override
    public Etat transition() {
        Etat e;
        if(!this.getMemoire().hasCarte()){
            e = new EtatCarte(this.getModule());
        }
        else if (this.getMemoire().hasCarte() && this.getModule().hasAction()){
            e = new EtatReflexion(this.getModule());
        }
        else{
            e = new EtatInnocupe(this.getModule());
        }
        return e;
    }

    /**
     * Réalise les actions dans la listesDesActionsEnCours
     */
    @Override
    public void action() {
        if(this.getModule().hasAction()){
            this.getModule().realiserAction();

        }
    }
}
