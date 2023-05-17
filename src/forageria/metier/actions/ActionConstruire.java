package forageria.metier.actions;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.ressources.TypeMateriau;

public class ActionConstruire extends Action {
    private TypeBatiment typeBatiment;
    private TypeMouvement typeMouvement;
    /**
     * @author Benjamin Milhet
     * Permet de gerer la construction des batiments
     */
    public ActionConstruire(TypeBatiment typeBatiment, TypeMouvement typeMouvement){
        this.typeBatiment = typeBatiment;
        this.typeMouvement = typeMouvement;
    }

    /**
     *
     * @return le message a envoyer au serveur
     */
    @Override
    public String getMessage() {
        String action = "BUILD|" + this.typeMouvement + "|" + this.typeBatiment;
        return action;
    }


    @Override
    public TypeAction getType() {
        TypeAction TA = TypeAction.CONSTRUCTION;
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

