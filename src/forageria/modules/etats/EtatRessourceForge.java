package forageria.modules.etats;

import forageria.metier.carte.ressources.TypeMateriau;
import forageria.metier.carte.ressources.TypeRessource;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

public class EtatRessourceForge extends Etat {
    public EtatRessourceForge(ModuleDecision module) {
        super(module);
    }

    /**
     * Permet d'aller chercher les 4 rochers  et crafter les 4 lingots de fer pour construire la forge puis il le construit
     * @return
     */
    @Override
    public Etat transition() {
        ArrayList<TypeRessource> typeRessources = new ArrayList<>();
        Etat e;
        //Compare s'il il a ressource nécessaire afin de construire la forge
        if(this.getMemoire().getQuantiteMateriel(TypeMateriau.ROCK) >= 4 && this.getMemoire().getQuantiteMateriel(TypeMateriau.IRONINGOT) >= 4){
            e = new EtatSiteForge(this.getModule());
        }
        //Sinon va chercher dees pierres
        else if (this.getMemoire().getQuantiteMateriel(TypeMateriau.ROCK) < 4){
            typeRessources.add(TypeRessource.ROCHER);
            e = new EtatRechercheRessource(this.getModule(),typeRessources);
        }
        //sinon va crafter des lingots de fer
        else if (this.getMemoire().getQuantiteMateriel(TypeMateriau.IRONINGOT) < 4){
            e = new EtatCrafterRessource(this.getModule(), TypeMateriau.IRONINGOT);
        }
        else{
            e = new EtatAttendre(this.getModule());
        }
        return e;
    }

    @Override
    public void action() {

    }
}
