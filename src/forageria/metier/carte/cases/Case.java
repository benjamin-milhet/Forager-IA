/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte.cases;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.ressources.Ressource;
import forageria.metier.carte.ressources.TypeMateriau;

import java.util.ArrayList;

import static forageria.metier.TypeMouvement.*;
import static forageria.metier.TypeMouvement.TOP;

/**
 * class abstraite permettettant d'initialiser les cases de la carte de recuperer les cases voisins
 * @author Benjamin Milhet
 */
public abstract class Case {
    private Coordonnee coordonnee;
    private Ressource ressource;
    private ArrayList<Case> voisins;
    private TypeBatiment batiment;

    /**
     * recupere les coordonnees et initialise l'arraylist voisins
     * @param coordonnee d'une case
     *
     */
    public Case(Coordonnee coordonnee){
        this.coordonnee = coordonnee;
        voisins = new ArrayList<>();
        this.ressource = null;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }
    
    public abstract TypeCase getType();

    public ArrayList<Case> getVoisins() {
        return this.voisins;
    }

    public void ajouterVoisin(Case voisin){
        this.voisins.add(voisin);
    }

    public TypeBatiment getBatiment() {
        return batiment;
    }

    public void setBatiment(TypeBatiment batiment) {
        this.batiment = batiment;
    }

    /**
     *
     * @return si une case est bien vide ou non
     */
    public boolean estVide(){
        boolean bool = false;
        if(this.getRessource() == null && this.getBatiment() == null){
            bool = true;
        }
        return bool;
    }

    public abstract boolean estAccessible();

    /**
     * permet de recuperer le mouvement pour aller d'une case a une autre
     * @param arrivee : case a atteindre
     * @return le mouvement a effectuer
     */
    public TypeMouvement getMouvementPourAller(Case arrivee) {
        TypeMouvement TM = this.coordonnee.getMouvementPourAller(arrivee.getCoordonnee());
        return TM;

}}
