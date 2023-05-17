package forageria.modules;

import forageria.IA;

/**
 * Classe abstraite des différents modules de l'IA
 * @authors Matthieu
 */
public abstract class Module {
    private IA ia;
    
    public Module(IA ia) {
        this.ia = ia;
    }
    
    public IA getIA() {
        return this.ia;
    }
    
}
