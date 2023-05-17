package forageria.metier.actions;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.ressources.TypeMateriau;

/**
 * @author Benjamin Milhet
 * Permet de fabriquer une des actions possibles
 */
public class FabriqueAction {

    /**
     * Permet de creer une actionMouvement
     * @param mouvement a réaliser
     * @return l'action a réaliser
     */
    public static Action creerMouvement(TypeMouvement mouvement){
        Action a = new ActionMouvement(mouvement);
        return a;
    }

    /**
     * Permet de creer une actionDemande
     * @param demande a envoyer au serveur
     * @return l'action a réaliser
     */
    public static Action creerDemande(TypeDemande demande){
        Action a = new ActionDemande("" + demande);
        return a;
    }

    /**
     * Permet de creer une actionCollecte
     * @param direction dans laquelle collecter
     * @return l'action a réaliser
     */
    public static Action creerCollecte(TypeMouvement direction){
        Action a = new ActionCollecte(direction);
        return a;
    }

    /**
     * Permet de creer une actionConstruire
     * @param mouvement dans lequelle construire
     * @return l'action a réaliser
     */
    public static Action creerConstruire(TypeMouvement mouvement, TypeBatiment batiment){
        Action a = new ActionConstruire(batiment,mouvement);
        return a;
    }

    /**
     * Permet de creer une actionCraft
     * @param direction dans laquelle est le fourneau
     * @param materiau qu'il faut craft
     * @return l'action a réaliser
     */
    public static Action creerCraft(TypeMouvement direction, TypeMateriau materiau){
        Action a = new ActionCraft(direction,materiau);
        return a;
    }



}
