package forageria.metier.actions;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.ressources.TypeMateriau;

/**
 * @author Benjamin Milhet
 * Permet de gerer les mouvements du personnage avec le serveur
 */

import static forageria.metier.actions.TypeAction.*;

public class ActionMouvement extends Action{
    private TypeMouvement typeMouvement;

    /**
     * @author Benjamin Milhet
     * Permet de gerer les mesages de mouvement
     */
    public ActionMouvement(TypeMouvement typeMouvement){
        this.typeMouvement = typeMouvement;
    }

    /**
     *
     * @return le messge a envoyer au serveur
     */
    @Override
    public String getMessage() {
        String typeMouvement = "MOVE|" + this.typeMouvement;
        return typeMouvement;
    }

    @Override
    public TypeAction getType() {
        TypeAction TA = MOUVEMENT;
        return TA;
    }

    @Override
    public TypeMouvement getDirection() {
        return this.typeMouvement;
    }

    @Override
    public TypeMateriau getMateriau() {
        return null;
    }
}
