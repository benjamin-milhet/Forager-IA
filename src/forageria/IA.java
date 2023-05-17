package forageria;

import forageria.modules.ModuleCommunication;
import forageria.modules.ModuleDecision;
import forageria.modules.ModuleMemoire;
import forageria.modules.ModuleReaction;
import java.io.IOException;

/**
 * Classe principale de l'IA
 * @authors Matthieu, Benjamin Milhet
 */
public class IA {


    private ModuleCommunication moduleCommunication;                           //Module de communication avec le serveur
    private ModuleDecision moduleDecision;                                     //Module en charge de la prise de décisions
    private ModuleMemoire moduleMemoire;                                       //Module en charge de la mémorisation des informations
    private ModuleReaction moduleReaction;                                     //Module en charge de la réaction de l'IA à un message du serveur
    private boolean isDiscussionTerminee;                                       //La discussion avec le serveur est-elle terminée
    private String messageEnvoye;                                               //Dernier message envoyé au serveur (ou devant être envoyé)
    private String messageRecu;                                                 //Dernier message reçu du serveur
    
    /**
     * Constructeur
     */
    public IA() {
        this.messageEnvoye = "";
        this.messageRecu = "";
        this.initialisation_Modules();
    }
    
    /**
     * Initialisation des différents modules
     */
    private void initialisation_Modules() {
        //Module de communication avec le serveur
        this.moduleCommunication = new ModuleCommunication(this);
        //Module de prise de décision
        this.moduleDecision = new ModuleDecision(this);
        //Module de gestion de la mémoire
        this.moduleMemoire = new ModuleMemoire(this);
        //Module de réaction aux messages du serveur
        this.moduleReaction = new ModuleReaction(this);
    }
    
    /**
     * Getter du module de communication
     * @return Le module de communication
     */
    public ModuleCommunication getModuleCommunication() {
        return this.moduleCommunication; 
    }
    
    /**
     * Getter du module de décision
     * @return Le module de décision
     */
    public ModuleDecision getModuleDecision() {
        return this.moduleDecision;
    }
    
    /**
     * Getter du module de mémoire
     * @return 
     */
    public ModuleMemoire getModuleMemoire() {
        return this.moduleMemoire;
    }
    
    /**
     * Getter du module de réaction
     * @return 
     */
    public ModuleReaction getModuleReaction() {
        return this.moduleReaction;
    }
    
    /**
     * Méthode servant à démarer l'IA
     * @throws IOException Erreur renvoyé en cas de problème de connection
     */
    public void start() throws IOException {
        //LANCEMENT DU MODULE DE COMMUNICATION
            //Connexion au serveur
        this.moduleCommunication.connexion();   
            //Création des gestoionnaires des flux de communication
        this.moduleCommunication.creationFlux();                                
        
        //LANCEMENT DE LA BOUCLE DE DISCUSSION
        this.isDiscussionTerminee = false;
        while(!isDiscussionTerminee) {
            //Recepetion d'un message du serveur
            messageRecu = this.moduleCommunication.recevoirMessage();
            //On traite ce message
            this.reagirAuMessageRecu();
            //On détermine quoi faire
            messageEnvoye = this.determinerNouvelleAction();
            //On encoit le message au serveur
            this.moduleCommunication.envoyerMessage(messageEnvoye);
            
        }
    }
    
    
    /**
     * Interpréter la réponse du serveur
     */
    private void reagirAuMessageRecu() {
        this.moduleReaction.reagirAuMessageRecu(messageEnvoye,messageRecu);
    }
    
    /**
     * Détermine la nouvelle action à réaliser
     * @return le prochain message à envoyer au serveur
     */
    private String determinerNouvelleAction() {
            
        return this.moduleDecision.determinerNouvelleAction(messageEnvoye);
    }
    
    /**
     * Arrête la discussion avec le serveur
     */
    public void arretDiscussion() {
        this.isDiscussionTerminee = true;
    }
}
