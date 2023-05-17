package forageria.metier.actions;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.ressources.TypeMateriau;

import static forageria.metier.actions.TypeAction.*;
/**
 * @author Benjamin Milhet
 * Permet de gerer différente demande au serveur
 */
public class ActionDemande extends Action {
    private String message;
    /**
     * @author Benjamin Milhet
     * Permet de gerer les mesages de demande au serveur
     */
    public ActionDemande(String message){
        this.message = message;
    }

    /**
     *
     * @return le messge a envoyer au serveur
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public TypeAction getType() {
        TypeAction TA = DEMANDE;
        return TA;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }

    @Override
    public TypeMateriau getMateriau() {
        return null;
    }


}
