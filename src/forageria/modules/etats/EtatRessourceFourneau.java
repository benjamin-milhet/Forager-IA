package forageria.modules.etats;

import forageria.metier.carte.ressources.TypeMateriau;
import forageria.metier.carte.ressources.TypeRessource;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

public class EtatRessourceFourneau extends Etat {
    public EtatRessourceFourneau(ModuleDecision module) {
        super(module);
    }

    /**
     * Permet d'aller chercher les 10 rochers pour construire le fourneau puis il le construit
     * @return
     */
    @Override
    public Etat transition() {
        ArrayList<TypeRessource> typeRessources = new ArrayList<>();
        Etat e;
        //Compare s'il il a ressource nécessaire afin de construire le fourneau
        if(this.getMemoire().getQuantiteMateriel(TypeMateriau.ROCK) >= 10){
            e = new EtatSiteFourneau(this.getModule());
        }
        //sinon il va chercher de la pierre
        else if (this.getMemoire().getQuantiteMateriel(TypeMateriau.ROCK) < 10){
            typeRessources.add(TypeRessource.ROCHER);
            e = new EtatRechercheRessource(this.getModule(),typeRessources);
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
