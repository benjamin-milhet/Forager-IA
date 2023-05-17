package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.actions.Action;
import forageria.metier.actions.FabriqueAction;
import forageria.metier.carte.ressources.Ressource;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

/**
 * @author Benjamin Milhet
 */
public class EtatCollecter extends Etat {
    private ArrayList<TypeMouvement> chemin;
    private Ressource ressource;


    public EtatCollecter(ModuleDecision module, ArrayList<TypeMouvement> chemin, Ressource ressource) {
        super(module);
        this.chemin = chemin;
        this.ressource = ressource;
    }


    @Override
    public Etat transition() {
        Etat e = new EtatReflexion(this.getModule());
        return e;
    }

    /**
     * A l'aide de la classe FabriqueAction demande au serveur de déplacer le personner jusqu'a une ressource puis de la collecter
     */
    @Override
    public void action() {
        TypeMouvement dernierMouvement = this.chemin.get(this.chemin.size()-1);
        this.chemin.remove(this.chemin.size()-1);
        for (TypeMouvement mouvement:this.chemin
             ) {
            Action action = FabriqueAction.creerMouvement(mouvement);
            this.getModule().ajouterAction(action);
        }
        int n = this.ressource.nombreCoupsPioche();
        for (int i = 0 ; i < n ; i++){
            Action a = FabriqueAction.creerCollecte(dernierMouvement);
            this.getModule().ajouterAction(a);

        }

    }
}
