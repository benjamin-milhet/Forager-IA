package forageria.metier.actions;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.ressources.TypeMateriau;

/**
 * @author Benjamin Milhet
 */
public abstract  class Action {

    public abstract String getMessage();

    public abstract TypeAction getType();

    public abstract TypeMouvement getDirection();

    public abstract TypeMateriau getMateriau() ;
}
