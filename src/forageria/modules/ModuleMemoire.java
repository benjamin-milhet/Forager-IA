package forageria.modules;

import forageria.IA;
import forageria.metier.Joueur;
import forageria.metier.actions.Action;
import forageria.metier.actions.TypeAction;
import forageria.metier.carte.Carte;
import forageria.metier.carte.Coordonnee;
import forageria.metier.carte.TypeBatiment;
import forageria.metier.carte.cases.Case;
import forageria.metier.carte.ressources.Ressource;
import forageria.metier.carte.ressources.TypeMateriau;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Module en charge de la mémorisation et de la restitution des informations obtenues
 * @author Matthieu, Benjamin Milhet
 */
public class ModuleMemoire extends Module  {
    private Carte carte;
    private Joueur joueur;
    private int dureeValiditeCarte;
    private HashMap<TypeMateriau, Integer> inventaire;

    private Coordonnee siteFourneau;
    private ArrayList<Coordonnee> fourneaux;
    private int timerCraftFourneau;
    private ArrayList<TypeMateriau> listeCraftEnCoursFourneau;

    private Coordonnee siteForge;
    private ArrayList<Coordonnee> forges;
    private int timerCraftForge;
    private ArrayList<TypeMateriau> listeCraftEnCoursForge;

    public ModuleMemoire(IA ia) {
        super(ia);
        this.dureeValiditeCarte = 0;
        this.inventaire = new HashMap<>();

        //On initialise tout l'inventaire a 0
        this.inventaire.put(TypeMateriau.TREE,0);
        this.inventaire.put(TypeMateriau.ROCK,0);
        this.inventaire.put(TypeMateriau.COAL,0);
        this.inventaire.put(TypeMateriau.IRON,0);
        this.inventaire.put(TypeMateriau.GOLD,0);
        this.inventaire.put(TypeMateriau.IRONINGOT,0);
        this.inventaire.put(TypeMateriau.GOLDINGOT,0);
        this.inventaire.put(TypeMateriau.COIN,0);

        this.fourneaux = new ArrayList<>();
        this.timerCraftFourneau = 0;
        this.listeCraftEnCoursFourneau = new ArrayList<>();

        this.forges = new ArrayList<>();
        this.timerCraftForge = 0;
        this.listeCraftEnCoursForge = new ArrayList<>();


    }

    public ArrayList<Coordonnee> getForges() {
        return forges;
    }

    public Coordonnee getSiteForge() {
        return siteForge;
    }

    public void setSiteForge(Coordonnee siteForge) {
        this.siteForge = siteForge;
    }

    public ArrayList<Coordonnee> getFourneaux() {
        return fourneaux;
    }

    public Coordonnee getSiteFourneau() {
        return siteFourneau;
    }

    public void setSiteFourneau(Coordonnee siteFourneau) {
        this.siteFourneau = siteFourneau;
    }

    public void genererCarte(String messageRecu){
        this.carte = new Carte(messageRecu);
        if(!getFourneaux().isEmpty()) {
            for (int i = 0; i < this.fourneaux.size(); i++) {
                this.getCarte().getCase(this.getFourneaux().get(i)).setBatiment(TypeBatiment.FURNACE);
            }
        }
        if(!getForges().isEmpty()) {
            for (int i = 0; i < this.forges.size(); i++) {
                this.getCarte().getCase(this.getForges().get(i)).setBatiment(TypeBatiment.FORGE);
            }
        }
        this.dureeValiditeCarte = 2;

    }

    /**
     * Permet de savoir si on possède la carte ou non
     * @return oui ou non
     */
    public boolean hasCarte(){
        boolean bool = false;
        if(this.dureeValiditeCarte > 0){
            bool = true;
        }
        return bool;
    }

    public boolean hasJoueur(){
        boolean bool = false;
        if (this.joueur != null){
            bool = true;
        }

        return bool;
    }

    public Joueur getJoueur(){
        return this.joueur;
    }

    /**
     * Permet de récuperer la position du joueur avec la réponse du serveur
     * @param messageRecu message du serveur
     */
    public void genererJoueur(String messageRecu){
        String[]  s = messageRecu.split("/");
        int col = Integer.parseInt(s[1]);
        int lig = Integer.parseInt(s[0]);
        Coordonnee coordonnee = new Coordonnee(lig,col);
        this.joueur = new Joueur(coordonnee);
    }

    public Carte getCarte() {
        return this.carte;
    }

    public Case getCase(){
        Case c = this.getCarte().getCase(this.getJoueur().getCoordonnee());
        return c;
    }

    /**
     * Permet d'effectuer une action
     * @param action a réaliser
     */
    public void effectuerAction(Action action){
        //Si l'action est un mouvement
        if(action.getType() == TypeAction.MOUVEMENT){
            this.getJoueur().deplacer(action.getDirection());
            this.dureeValiditeCarte--;
        }
        //Si l'action est une collecte
        else if (action.getType() == TypeAction.COLLECTE){
            Case caseDestination = this.carte.getCase((this.getCase()).getCoordonnee().getVoisin(action.getDirection()));
            this.recolter(caseDestination.getRessource());
            if (!caseDestination.estVide()){
                caseDestination.setRessource(null);
            }
        }
        //Si l'action est une construction
        else if (action.getType() == TypeAction.CONSTRUCTION){
            //si c'est un fourneau, on rentre ces 4 coordonnées puis on enlève les ressources utilisées
            if (this.fourneaux.isEmpty()) {
                this.fourneaux.add(this.siteFourneau);
                this.fourneaux.add(new Coordonnee(this.siteFourneau.getLigne() - 1, this.siteFourneau.getColonne()));
                this.fourneaux.add(new Coordonnee(this.siteFourneau.getLigne(), this.siteFourneau.getColonne() + 1));
                this.fourneaux.add(new Coordonnee(this.siteFourneau.getLigne() - 1, this.siteFourneau.getColonne() + 1));

                this.inventaire.put(TypeMateriau.ROCK, this.inventaire.get(TypeMateriau.ROCK).intValue() - 10);

            }
            //si c'est la forge, on rentre ces 4 coordonnées puis on enlève les ressources utilisées
            else if(this.forges.isEmpty()){
                this.forges.add(this.siteForge);
                this.forges.add(new Coordonnee(this.siteForge.getLigne() - 1, this.siteForge.getColonne()));
                this.forges.add(new Coordonnee(this.siteForge.getLigne(), this.siteForge.getColonne() + 1));
                this.forges.add(new Coordonnee(this.siteForge.getLigne() - 1, this.siteForge.getColonne() + 1));

                this.inventaire.put(TypeMateriau.ROCK, this.inventaire.get(TypeMateriau.ROCK).intValue() - 4);
                this.inventaire.put(TypeMateriau.IRONINGOT, this.inventaire.get(TypeMateriau.IRONINGOT).intValue() - 4);
            }
            //On récupère la carte afin que l'IA reconnaise bien la position des batiments
            this.dureeValiditeCarte = 0;
        }
        //Si l'action est un craft
        else if (action.getType() == TypeAction.CRAFT){
            //suivant le craft, il enleve les ressources utilisés et l'ajoute a la liste des crafts du fourneau ou de la forge en fonction du matériau choisi
            if (action.getMateriau() == TypeMateriau.COAL){
                this.inventaire.put(TypeMateriau.TREE, this.inventaire.get(TypeMateriau.TREE).intValue() - 2);
                this.ajouterCraft(TypeMateriau.COAL);

            }
            else if (action.getMateriau() == TypeMateriau.IRONINGOT){
                this.inventaire.put(TypeMateriau.IRON, this.inventaire.get(TypeMateriau.IRON).intValue() - 2);
                this.inventaire.put(TypeMateriau.COAL, this.inventaire.get(TypeMateriau.COAL).intValue() - 1);
                this.ajouterCraft(TypeMateriau.IRONINGOT);

            }
            else if (action.getMateriau() == TypeMateriau.GOLDINGOT){
                this.inventaire.put(TypeMateriau.GOLD, this.inventaire.get(TypeMateriau.GOLD).intValue() - 2);
                this.inventaire.put(TypeMateriau.COAL, this.inventaire.get(TypeMateriau.COAL).intValue() - 1);
                this.ajouterCraft(TypeMateriau.GOLDINGOT);

            }
            else if (action.getMateriau() == TypeMateriau.COIN){
                this.inventaire.put(TypeMateriau.GOLDINGOT, this.inventaire.get(TypeMateriau.GOLDINGOT).intValue() - 2);
                this.ajouterCraftForge(TypeMateriau.COIN);

            }
        }
        //on fait avancer le temps afin de craft les ressources
        this.avancerTimeCraft();
        this.avancerTimeCraftForge();
    }

    /**
     * Permet d'ajouter a l'inventaire les differents matériaux récolter
     * @param ressource detruit afin d'obtenir les matériaux
     */
    private void recolter(Ressource ressource){
        if(ressource != null) {
            for (int i = 0; i < ressource.getLoot().size(); i++) {
                this.inventaire.put(ressource.getLoot().get(i), this.getQuantiteMateriel(ressource.getLoot().get(i))+1);
            }
        }

    }

    /**
     * Permet de recuperer la quantite d'un matériau dans l'inventaire
     * @param type du matériau dont on cherche la quantité
     * @return la quantité du matériau
     */
    public int getQuantiteMateriel(TypeMateriau type){
        int nb = 0;
        if(this.inventaire.get(type) != null) {
            nb = this.inventaire.get(type);
        }
        return nb;
    }

    /**
     * Permet d'ajouter a la liste des crafts un nouveau craft en ajoutant le temps de 14(temps pour craft quelque chose)
     * @param materiau
     */
    private void ajouterCraft(TypeMateriau materiau){
        this.timerCraftFourneau += 14;
        this.listeCraftEnCoursFourneau.add(materiau);

    }

    /**
     *Permet d'attendre avant de craft une autre ressource puis ajoute le matériaux crafter a l'inventaire
     */
    private void avancerTimeCraft(){
        if (this.timerCraftFourneau > 0 ){
            this.timerCraftFourneau -= 1;
            if (this.timerCraftFourneau % 14 == 0){
                TypeMateriau materiau = this.listeCraftEnCoursFourneau.get(0);
                this.inventaire.put(materiau,this.inventaire.get(materiau).intValue() + 1);
                this.listeCraftEnCoursFourneau.remove(0);
            }
        }
    }

    /**
     * Permet d'ajouter a la liste des crafts un nouveau craft en ajoutant le temps de 14(temps pour craft quelque chose)
     * @param materiau
     */
    private void ajouterCraftForge(TypeMateriau materiau){
        this.timerCraftForge += 14;
        this.listeCraftEnCoursForge.add(materiau);

    }

    /**
     *Permet d'attendre avant de craft une autre ressource puis ajoute le matériaux crafter a l'inventaire
     */
    private void avancerTimeCraftForge(){
        if (this.timerCraftForge > 0 ){
            this.timerCraftForge -= 1;
            if (this.timerCraftForge % 14 == 0){
                TypeMateriau materiau = this.listeCraftEnCoursForge.get(0);
                this.inventaire.put(materiau,this.inventaire.get(materiau).intValue() + 1);
                this.listeCraftEnCoursForge.remove(0);
            }
        }
    }
}
