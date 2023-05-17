package forageria.modules.etats;

import forageria.metier.TypeMouvement;
import forageria.metier.actions.FabriqueAction;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.ressources.TypeMateriau;
import forageria.metier.carte.ressources.TypeRessource;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

/**
 * @author Benjamin Milhet
 * Permet de chercher des ressources pour un fourneau ou du bois
 */
public class EtatInnocupe extends Etat {
    public EtatInnocupe(ModuleDecision module) {
        super(module);
    }

    /**
     * Permet de rechercher les ressources pour craft le fourneau puis cherche les ressources pour craft du charbon sinon cherche du bois
     * @return
     */
    @Override
    public Etat transition() {
        Etat e;
        //première étape : construire le fourneau
        if(this.getMemoire().getFourneaux().isEmpty()){
            e = new EtatRessourceFourneau(this.getModule());
        }
        //deuxième étape : construire la forge
        else if (this.getMemoire().getForges().isEmpty()){
            e = new EtatRessourceForge(this.getModule());
        }
        //troisième étape : fabriquer le plus de pièce possible
        else if (!this.getMemoire().getForges().isEmpty()){
            e = new EtatCrafterRessource(this.getModule(), TypeMateriau.COIN);

        }
        // au cas ou, va chercher du bois
        else{
            ArrayList<TypeRessource> typeRessources = new ArrayList<>();
            typeRessources.add(TypeRessource.ARBRE);
            //typeRessources.add(TypeRessource.ROCHER);
            e = new EtatRechercheRessource(this.getModule(),typeRessources);
        }

        return e;
    }

    @Override
    public void action() {


    }
}
