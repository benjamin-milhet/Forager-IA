package forageria.modules.etats;

import forageria.modules.ModuleDecision;
import forageria.modules.ModuleMemoire;

/**
 * Classe mère des Etats pour l'automate
 * @author Benjamin Milhet
 */
public abstract class Etat {
    private ModuleDecision module;
    protected ModuleMemoire memoire;

    public Etat(ModuleDecision module){
        this.module = module;
        this.memoire = this.module.getIA().getModuleMemoire();
    }


    public ModuleDecision getModule() {
        return module;
    }

    public void setModule(ModuleDecision module) {
        this.module = module;
    }

    public ModuleMemoire getMemoire() {
        return memoire;
    }


    /**
     * Permet de passer d'un état a un autre
     * @return le nouvel état
     */
    public abstract Etat transition();

    /**
     * Réalise l'action de l'état en cours
     */
    public abstract void action();
}
