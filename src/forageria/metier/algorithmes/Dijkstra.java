package forageria.metier.algorithmes;

import forageria.metier.TypeMouvement;
import forageria.metier.carte.Carte;
import forageria.metier.carte.cases.Case;
import forageria.metier.carte.cases.TypeCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Dijkstra extends AlgorithmeCalculDistance {
    private HashMap<Case,Boolean> estVisite;
    private HashMap<Case,Case> predecesseur;
    private int infini;

    /**
     * Recupere la carte donne en parametre et initialise la HashMap
     *
     * @param carte du jeux
     */
    public Dijkstra(Carte carte) {
        super(carte);
        this.infini = 1 + 16*this.getCarte().getTaille()*this.getCarte().getTaille();
        this.estVisite = new HashMap<>();
        this.predecesseur = new HashMap<>();
    }

    public int getInfini() {
        return infini;
    }

    /**
     * Permet de connaitre le nombre de mouvement a réaliser entre deux cases (en comptant les ressources a récolter si besoin)
     * @param destination on cherche le nombre de mouvement pour atteindre cette case
     * @return le nombre de mouvement
     */
    protected int coutMouvementVers(Case destination){
        int nbDeplacement = 0;
        if(destination.getType() != TypeCase.EAU){
            nbDeplacement = 1;
            if(destination.getBatiment() != null){
                nbDeplacement = this.infini;
            }
            if(destination.getRessource()!=null) {
                nbDeplacement += destination.getRessource().nombreCoupsPioche();
            }

        }
        else{
            nbDeplacement = this.infini;
        }
        return nbDeplacement;
    }

    /**
     *  Initialise tout les sommets(cases) afin de commencer l'algorithme de Dijkstra
     * @param depart c'est la case où l'on commence, on doit l'initialiser à 0
     */
    private void initialisation(Case depart){
        for (Case c:this.getCarte().getCases()
             ) {
            this.setDistance(c,this.infini);
            this.estVisite.put(c,false);
            this.predecesseur.put(c,null);
        }
        this.setDistance(depart,0);


    }

    /**
     * Permet de savoir qu'elle case a le cout le plus bas en partant de la fin
     * @param a case à tester pour savoir sil faut passer par cette ou non depuis la case b
     * @param b qui est la case a laquelle on est sur de passer
     */
    private void relachement(Case a, Case b){

        if (this.getDistance(b) > this.getDistance(a) + this.coutMouvementVers(b)){
            this.setDistance(b,this.getDistance(a) + this.coutMouvementVers(b));
            this.predecesseur.put(b,a);
        }
     }

    /**
     * Permet de savoir quelle case non visité est la plus proche
     * @return la case la plus proche
     */
    private Case getCaseLaPlusProche(){
        int distanceMin = this.infini;
        Case res = null;

        for (Case c: this.getCarte().getCases()
             ) {
            if (this.estVisite.get(c) == false && this.getDistance(c) < distanceMin) {
                distanceMin = this.getDistance(c);
                res = c;
            }
        }
        return res;
    }


    /**
     * Permet de calculer la distance la plus courte entre 2 cases
     * @param depart : case de depart a laquelle il faut calculer la distance
     */
    @Override
    public void calculerDistancesDepuis(Case depart) {
        initialisation(depart);
        Case caseLaPlusProche = this.getCaseLaPlusProche();
        while(caseLaPlusProche != null){
            this.estVisite.put(caseLaPlusProche,true);
            for(int i = 0 ; i < caseLaPlusProche.getVoisins().size() ; i++){
                relachement(caseLaPlusProche,caseLaPlusProche.getVoisins().get(i));
            }
            caseLaPlusProche = this.getCaseLaPlusProche();


        }

    }

    /**
     * Permet de connaitre le chemin le plus court entre deux cases a l'aide de prédecesseur
     * @param arrivee case a la laquelle on récupre le chemin pour y accéder
     * @return une liste contenant tous le smouvements pour atteindre cette case
     */
    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        ArrayList<TypeMouvement> resultat = new ArrayList<>();
        Case caseEnCours = arrivee;
        Case casePrecedente = null;

        if (this.getDistance(caseEnCours) != null) {
            while (this.getDistance(caseEnCours) > 0) {
                casePrecedente = this.predecesseur.get(caseEnCours);
                this.setDistance(casePrecedente, this.getDistance(caseEnCours) - 1);
                resultat.remove(null);
                if (casePrecedente!=null) {
                        resultat.add(casePrecedente.getMouvementPourAller(caseEnCours));
                }
                caseEnCours = casePrecedente;
            }
        }

                Collections.reverse(resultat);
                return resultat;
            }
        }