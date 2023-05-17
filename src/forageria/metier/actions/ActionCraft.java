package forageria.metier.actions;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.ressources.TypeMateriau;

import java.lang.reflect.Type;

public class ActionCraft extends Action {
    private TypeMouvement typeMouvement;
    private TypeMateriau materiau;
    /**
     * @author Benjamin Milhet
     * Permet de gerer la création de certaine ressource
     */
    public ActionCraft(TypeMouvement typeMouvement, TypeMateriau materiau){
        this.typeMouvement = typeMouvement;
        this.materiau = materiau;
    }

    /**
     *
     * @return le message a envoyer au serveur
     */
    @Override
    public String getMessage() {
        String action = "CRAFT|" + this.typeMouvement + "|" + this.materiau;
        return action;
    }


    @Override
    public TypeAction getType() {
        TypeAction TA = TypeAction.CRAFT;
        return TA;
    }

    @Override
    public TypeMouvement getDirection() {
        return this.typeMouvement;
    }

    @Override
    public TypeMateriau getMateriau() {
        return this.materiau;
    }
}
