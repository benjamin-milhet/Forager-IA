package forageria.metier.actions;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.ressources.TypeMateriau;

/**
 * @author Benjamin Milhet
 * Permet de gerer la collecte des ressources
 */
public class ActionCollecte extends Action {
    private TypeMouvement typeMouvement;

    public ActionCollecte(TypeMouvement TM){
        this.typeMouvement = TM;
    }

    /**
     *
     * @return le message a envoyer au serveur
     */
    @Override
    public String getMessage() {
        String typeMouvement = "COLLECT|" + this.typeMouvement;
        return typeMouvement;
    }


    @Override
    public TypeAction getType() {
        TypeAction TA = TypeAction.COLLECTE;
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
