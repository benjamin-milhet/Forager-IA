package forageria.modules;

import forageria.IA;
import forageria.metier.actions.*;
import forageria.metier.actions.FabriqueAction;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.cases.Case;
import forageria.modules.etats.Etat;
import forageria.modules.etats.EtatInitial;

import java.util.ArrayList;

/**
 * Module en charge de la prise de décision
 * @author Matthieu, Benjamin Milhet
 */

    public class ModuleDecision extends Module {

        public ArrayList<Action> listeDesActionsARealiser;
        private Etat etatCourant;
        private String messageReponse;

        public ModuleDecision(IA ia) {
            super(ia);
            this.listeDesActionsARealiser = new ArrayList<>();
            this.etatCourant = new EtatInitial(this);
        }

        /**
         * Méthode principale de prise de décision
         * @param messageRecu dernier message reçu du serveur
         * @return Le prochain message à envoyer
         */
        public String determinerNouvelleAction(String messageRecu) {
            this.messageReponse = "";
            while(this.messageReponse.equals("")){
                etatCourant = etatCourant.transition();
                etatCourant.action(); }
            return this.messageReponse;

        }

    /**
     * Permert d'ajouter une action dans la liste listeDesActionsARealiser
     * @param action a ajouter dans la liste
     */
    public void ajouterAction(Action action){
            this.listeDesActionsARealiser.add(action);
        }
    /**
     * Permert d'ajouter une action dans la liste listeDesActionsARealiser en position 0
     * @param action a ajouter dans la liste
     */
        public void ajouterActionPrioritaire(Action action){
            this.listeDesActionsARealiser.add(0,action);
        }
    /**
     * Vérifie s'il y des actions a réaliser dans la liste
     */
        public boolean hasAction(){
            boolean bool = false;
            if(!this.listeDesActionsARealiser.isEmpty()){
                bool  = true;
            }

            return bool;
        }

    /**
     * Permet de réaliser les actions dans l'odre de la listeDesActionsARéaliser
     */
        public void realiserAction(){
            Action action = this.listeDesActionsARealiser.get(0);
            if (action.getType() == TypeAction.MOUVEMENT){
                Coordonnee coordonneeDestination = this.getIA().getModuleMemoire().getCase().getCoordonnee().getVoisin(action.getDirection());
                Case caseDestination = this.getIA().getModuleMemoire().getCarte().getCase(coordonneeDestination);
                //if(!caseDestination.estVide()){
                if(caseDestination.getRessource() != null){
                    for(int i = 0 ; i < caseDestination.getRessource().nombreCoupsPioche() ; i++){
                        this.listeDesActionsARealiser.add(0, FabriqueAction.creerCollecte(action.getDirection()));


                    }
                }
            }
            this.messageReponse = this.listeDesActionsARealiser.get(0).getMessage();
            this.getIA().getModuleMemoire().effectuerAction(this.listeDesActionsARealiser.get(0));
            this.listeDesActionsARealiser.remove(0);
        }
    }


