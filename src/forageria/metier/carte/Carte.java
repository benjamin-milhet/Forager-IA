/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forageria.metier.carte;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.cases.Case;
import forageria.metier.carte.cases.FabriqueCase;

import static forageria.metier.TypeMouvement.*;
import static forageria.metier.carte.cases.TypeCase.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * Permet de recuperer la carte du jeux avec les differents types de caes et de ressources
 * @author Benjamin Milhet
 */
public class Carte {
    private int taille;
    HashMap<Coordonnee,Case> cases;


    /**
     * Initialise la HashMap et recupere toute les infos du serveur afin de recuperer les differents type de cases et d ressource et de l'implementer dans le HashMap
     * @param messageRecu Code de la MAP
     */
    public Carte(String messageRecu) {
        this.cases = new HashMap<>();
        this.taille = (int)Math.sqrt(messageRecu.length());
        for(int i=0;i<this.taille;i++) {
            for(int j=0;j<this.taille;j++) {
                this.ajouterCase(new Coordonnee(i,j),messageRecu.charAt(j+this.taille*i));


            }

        }
        for(int i=0;i<this.taille;i++) {
            for(int j=0;j<this.taille;j++) {
                Coordonnee cooCase = new Coordonnee(i,j);
                for(TypeMouvement mouvement:TypeMouvement.values()) {
                    Coordonnee cooVoisin=cooCase.getVoisin(mouvement);
                    if(this.cases.get(cooVoisin)!=null) {
                        this.cases.get(cooCase).ajouterVoisin(this.cases.get(cooVoisin));
                    }
                }

            }

        }
        /*Case c1 = this.cases.get(new Coordonnee(0,0));
        for(int i = 0; i<c1.getVoisins().size();i++){
            System.out.println(c1.getVoisins().get(i).getCoordonnee());
        }

        Case c2 = this.cases.get(new Coordonnee(3,3));
        for(int i = 0; i<c2.getVoisins().size();i++){
            System.out.println(c2.getVoisins().get(i).getCoordonnee());
        }

        Case c3 = this.cases.get(new Coordonnee(6,4));
        for(int i = 0; i<c3.getVoisins().size();i++){
            System.out.println(c3.getVoisins().get(i).getCoordonnee());
        }
        */
    }
    
    private void ajouterCase(Coordonnee coordonnee, Character lettre){
        FabriqueCase f = new FabriqueCase();
        Case c = f.creer(coordonnee,lettre);
        this.cases.put(coordonnee,c);
    }

    /**
     * Affiche le detail de la map dans dans la console
     */
    public void afficheConsole(){
        for(int i = 0;i<this.taille;i++){
            for(int j = 0; j<this.taille;j++){
                String affichage = "W";
                Case caseEnCours = this.cases.get(new Coordonnee(i,j));
                if(caseEnCours.getType() == HERBE){
                    if(caseEnCours.getRessource() == null){
                        affichage = "H";
                    }
                    else{
                        switch(caseEnCours.getRessource().getType()){
                            case ARBRE:
                                affichage = "T";
                                break;
                            case ROCHER:
                                affichage = "R";
                                break;
                            case FER:
                                affichage = "I";
                                break;
                            case OR:
                                affichage = "G";
                                break;
                                
                        }
                    }
                }
                System.out.print(affichage);
            }
            System.out.println("");
        }
    }

    /**
     * Permet de récupere la case ou se trouve le joueur
     * @param coordonnee coordonné du joueur
     * @return la case du joueur
     */
    public Case getCase(Coordonnee coordonnee){
        Case c = this.cases.get(coordonnee);
        return c;
    }

    /**
     *
     * @returnla taille de la carte
     */
    public int getTaille() {
        return taille;
    }

    /**
     *
     * @return toutes les cases de la carte
     */
    public Collection<Case> getCases(){
        Collection<Case> c = this.cases.values();
        return c;
    }

    /**
     * Permet de savoir si une zone est valide afin d'y placer un fourneau
     * @param coinBasGauche case a partir de laquelle on commence de tester
     * @return si la zone est valide ou non
     */
    public boolean estZoneValide(Case coinBasGauche){
        boolean bool = true;
        Coordonnee coin = coinBasGauche.getCoordonnee();
        //grâce a la double boucle, on regarde toutes les cases autour de la case donné donné en paramètre
            for(int i = -2 ; i < 2 ; i++) {
                for(int j = - 1 ; j < 3 ; j++) {
                    Coordonnee coordonnee = new Coordonnee(coin.getLigne() - j, coin.getColonne() - i);
                    Case c = this.cases.get(coordonnee);
                    //Dès que l'on rencontre une case "interdite" on renvoi false
                    if(c != null) {
                        if (c.getType() != HERBE) {
                            bool  = false;
                        }
                        else if (c.getBatiment() != null){
                            bool = false;
                        }
                    }
                    else {
                        bool = false;
                    }
                }
            }
            return bool;
    }
}
