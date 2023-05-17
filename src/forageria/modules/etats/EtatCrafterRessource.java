package forageria.modules.etats;

import forageria.metier.carte.ressources.TypeMateriau;
import forageria.metier.carte.ressources.TypeRessource;
import forageria.modules.ModuleDecision;

import java.util.ArrayList;

public class EtatCrafterRessource extends Etat {
    private TypeMateriau materiau;

    public EtatCrafterRessource(ModuleDecision module, TypeMateriau materiau) {
        super(module);
        this.materiau = materiau;
    }

    /**
     * Permet d'aller chercher du bois pour ensuite crafter du charbon
     * @return un nouvel etat
     */
    @Override
    public Etat transition() {
        Etat nouvelEtat = new EtatAttendre(this.getModule());
        //Cas du charbon : on commence par chercher 2 bois si besoin, puis on change d'état pour aller le craft
        if (this.materiau == TypeMateriau.COAL){
            if (this.getMemoire().getQuantiteMateriel(TypeMateriau.TREE) < 2){
                ArrayList<TypeRessource> a = new ArrayList<>();
                a.add(TypeRessource.ARBRE);
                nouvelEtat = new EtatRechercheRessource(this.getModule(),a);
            }
            else{
                nouvelEtat = new EtatCrafterAuFourneau(this.getModule(),this.materiau);
            }
        }
        //Cas du lingot de fer : on commence par chercher/crafter 1 charbon et 2 de fer si besoin, puis on change d'état pour aller le craft
        else if (this.materiau == TypeMateriau.IRONINGOT){
            if  (this.getMemoire().getQuantiteMateriel(TypeMateriau.COAL) < 1){
                nouvelEtat = new EtatCrafterRessource(this.getModule(),TypeMateriau.COAL);
            }
            else if (this.getMemoire().getQuantiteMateriel(TypeMateriau.IRON) < 2){
                ArrayList<TypeRessource> a = new ArrayList<>();
                a.add(TypeRessource.FER);
                nouvelEtat = new EtatRechercheRessource(this.getModule(),a);
            }
            else{
                nouvelEtat = new EtatCrafterAuFourneau(this.getModule(),this.materiau);
            }
        }
        //Cas du lingot d'or : on commence par chercher/crafter 1 charbon et 2 d'or si besoin, puis on change d'état pour aller le craft

        else if (this.materiau == TypeMateriau.GOLDINGOT){
            if  (this.getMemoire().getQuantiteMateriel(TypeMateriau.COAL) < 1){
            nouvelEtat = new EtatCrafterRessource(this.getModule(),TypeMateriau.COAL);
            }
            else if (this.getMemoire().getQuantiteMateriel(TypeMateriau.GOLD) < 2){
                ArrayList<TypeRessource> a = new ArrayList<>();
                a.add(TypeRessource.OR);
                nouvelEtat = new EtatRechercheRessource(this.getModule(),a);
            }
            else{
                nouvelEtat = new EtatCrafterAuFourneau(this.getModule(),this.materiau);
            }
        }
        //Cas de la pièce : on commence par aller crafter 2 lingots d'or si besoin, puis on change d'état pour aller le craft
        else if (this.materiau == TypeMateriau.COIN){
            if (this.getMemoire().getQuantiteMateriel(TypeMateriau.GOLDINGOT) < 2){
                nouvelEtat = new EtatCrafterRessource(this.getModule(),TypeMateriau.GOLDINGOT);
            }
            else{
                nouvelEtat = new EtatCrafterALaForge(this.getModule(),this.materiau);
            }
        }
        return nouvelEtat;
    }

    @Override
    public void action() {

    }
}
