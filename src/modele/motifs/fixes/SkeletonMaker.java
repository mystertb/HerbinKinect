/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.motifs.fixes;

import java.awt.Color;
import java.util.ArrayList;

import outils.Alphabet;



/**
 *
 * @author Nexus
 * Classe qui permet de générer les motifs des parties du corps (du squelette)
 */
public class SkeletonMaker {

	//Variables de classe	
    private String mot;
    private ArrayList<Motif> squeletteHerbin;
    private Alphabet alphabet;
    private int epaisseurPartieCorps;
    private ArrayList<PtSkeleton> listeSkel;
    
    //Constructeur avec parametre
    public SkeletonMaker(ArrayList<PtSkeleton> listeSkel) {

        squeletteHerbin = new ArrayList<Motif>();
        this.listeSkel = listeSkel;
        alphabet = new Alphabet();
    }

    //Constructeur par défaut sans parametres
    public SkeletonMaker() {
    	
    	alphabet = new Alphabet();
    }
    
    //Met à jour les motifs de chaque partie du corps
    public void skeletonMaker() {
        
        for(int i=0;i<Skeleton.values().length;i++) {
        	skeletonUpdate(Skeleton.values()[i]);
        }
        
    }
   
    
    /*
     * Attribue les formes, coloris correspondant a chaque partie du corps et articulation, 
    et fait l'équivalance entre articulation et partie du corps concerné
    */
    
    public PtSkeleton getUpdatedPt (Skeleton s) {
    	
    	for(int i=0;i<listeSkel.size();i++) {
    		
    		if(listeSkel.get(i).getPartieDuCorps().equals(s)) {
    			return listeSkel.get(i);
    		}
    		
    	}
    	
    	System.out.println(s);
    	
    	return new PtSkeleton(Skeleton.aucun);
    }
    
    //retourne la liste des motifs du squelette
    public ArrayList<Motif> getSqueletteHerbin() {
    	
    	return squeletteHerbin;
    	
    }
    
    //Lance la génération des formes en fonction du texte entré
    public void startText(String texte) {
    	
    	this.mot = texte;
    	skeletonMaker();
    	
    }
    
    //Génère les motifs pour chaque partie du corpsen choisissant les couleurs en fonction de l'algorithme herbin
    public void skeletonUpdate(Skeleton skel) {

    	Motif motif;
    	//ArrayList<SkeletonPoint> listePoints, int epaisseur, FormeGeo formeGeo, Color couleur
    	alphabet.chargerCouleur(mot.charAt((squeletteHerbin.size())%mot.length()));
    	ArrayList<PtSkeleton> listePoints = new ArrayList<PtSkeleton>();
    	epaisseurPartieCorps=22;
        switch (skel) {

            case tete:
                
                //squelette tete cou    
            	PtSkeleton tete = getUpdatedPt(Skeleton.tete);
                listePoints.add(tete);
                listePoints.add(getUpdatedPt(Skeleton.cou)); 
            	alphabet.chargerCouleur(mot.charAt(4%mot.length()));
            	motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);

                listePoints = new ArrayList<PtSkeleton>();
                
            	//tete
            	listePoints.add(tete);
            	alphabet.chargerCouleur(mot.charAt(4%mot.length()));
            	motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),75);
            	this.squeletteHerbin.add(motif);
            	
            	listePoints = new ArrayList<PtSkeleton>();
            	
            	//oeuil gauche
            	listePoints.add(tete);
            	listePoints.add(new PtSkeleton(new Pt(5,10), Skeleton.aucun));
            	alphabet.chargerCouleur(mot.charAt((squeletteHerbin.size())%mot.length()));
            	alphabet.negatif();
            	alphabet.couleurLaPlusProche("");
            	motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),20);
            	this.squeletteHerbin.add(motif);
            	
            	listePoints = new ArrayList<PtSkeleton>();
            	
            	
            	//oeuil droit
            	listePoints.add(tete);
            	listePoints.add(new PtSkeleton(new Pt(30,10), Skeleton.aucun));
            	motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),20);
            	this.squeletteHerbin.add(motif);
            	
            	listePoints = new ArrayList<PtSkeleton>();
            	

            	//pupille gauche
            	listePoints.add(tete);
            	listePoints.add(new PtSkeleton(new Pt(0,5), Skeleton.aucun));
            	alphabet.chargerCouleur(mot.charAt((squeletteHerbin.size())%mot.length()));
            	motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),10);
            	this.squeletteHerbin.add(motif);
            	
            	listePoints = new ArrayList<PtSkeleton>();
            	
            	//pupille gauche
            	listePoints.add(tete);
            	listePoints.add(new PtSkeleton(new Pt(25,5), Skeleton.aucun));
            	alphabet.chargerCouleur(mot.charAt((squeletteHerbin.size()-1)%mot.length()));
            	motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),10);
            	this.squeletteHerbin.add(motif);
            	
            	listePoints = new ArrayList<PtSkeleton>();
            	
            	//nez
            	listePoints.add(tete);
            	listePoints.add(new PtSkeleton(new Pt(10,20), Skeleton.aucun));
            	alphabet.chargerCouleur(mot.charAt((squeletteHerbin.size())%mot.length()));
            	motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),10);
            	this.squeletteHerbin.add(motif);
            	
            	listePoints = new ArrayList<PtSkeleton>();
            	
            	//levres
            	listePoints.add(tete);
            	listePoints.add(new PtSkeleton(new Pt(20,0), Skeleton.aucun));
            	alphabet.chargerCouleur(mot.charAt((squeletteHerbin.size()-3)%mot.length()));
            	alphabet.negatif();
            	motif = new MotifDCercle(listePoints,alphabet.getCouleur(),30);
            	this.squeletteHerbin.add(motif);
            	
            	
                break;
            case epaule_gauche:
            	//epaule gauche               
                //squelette epaule gauche epaule droite hanche droite hanche gauche
                listePoints.add(getUpdatedPt(Skeleton.epaule_gauche));
                listePoints.add(getUpdatedPt(Skeleton.epaule_droite)); 
                listePoints.add(getUpdatedPt(Skeleton.hanche_droite));
                listePoints.add(getUpdatedPt(Skeleton.hanche_gauche)); 
                alphabet.chargerCouleur(mot.charAt(3%mot.length()));
                motif = new MotifFixePolygone(listePoints,alphabet.getCouleur());
            	this.squeletteHerbin.add(motif);
            	
            	
                listePoints = new ArrayList<PtSkeleton>();
                
            	 //squelette epaule gauche coude gauche
                listePoints.add(getUpdatedPt(Skeleton.epaule_gauche));
                listePoints.add(getUpdatedPt(Skeleton.coude_gauche)); 
                alphabet.chargerCouleur(mot.charAt(3%mot.length()));
                motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case epaule_droite:
            	//epaule droite
                //suqelette epaule droite coude droit
                listePoints.add(getUpdatedPt(Skeleton.epaule_droite));
                listePoints.add(getUpdatedPt(Skeleton.coude_droit)); 
                alphabet.chargerCouleur(mot.charAt(3%mot.length()));
                motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case coude_gauche:
            	//coude gauche
                //suqelette coude gauche main gauche
                listePoints.add(getUpdatedPt(Skeleton.coude_gauche));
                listePoints.add(getUpdatedPt(Skeleton.main_gauche)); 
                alphabet.chargerCouleur(mot.charAt(4%mot.length()));
                motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case coude_droit:
            	//coude droit            	
                //suqelette coude droit main droite
                listePoints.add(getUpdatedPt(Skeleton.coude_droit));
                listePoints.add(getUpdatedPt(Skeleton.main_droite)); 
                alphabet.chargerCouleur(mot.charAt(4%mot.length()));
                motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case main_gauche:
            	//main gauche
                listePoints.add(getUpdatedPt(Skeleton.main_gauche));
                alphabet.chargerCouleur(mot.charAt(5%mot.length()));
                motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case main_droite:
            	//main droite
                listePoints.add(getUpdatedPt(Skeleton.main_droite));
                alphabet.chargerCouleur(mot.charAt(5%mot.length()));
                motif = new MotifFixeCercle(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case hanche_gauche:
                //squelette hanche gauche genou gauche
                listePoints.add(getUpdatedPt(Skeleton.hanche_gauche));
                listePoints.add(getUpdatedPt(Skeleton.genou_gauche)); 
                alphabet.chargerCouleur(mot.charAt(6%mot.length()));
                motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case hanche_droite:
            	//suqelette hanche droite genou droit
                listePoints.add(getUpdatedPt(Skeleton.hanche_droite));
                listePoints.add(getUpdatedPt(Skeleton.genou_droit)); 
                alphabet.chargerCouleur(mot.charAt(6%mot.length()));
                motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            case genou_gauche:
                 listePoints.add(getUpdatedPt(Skeleton.genou_gauche));
                 listePoints.add(getUpdatedPt(Skeleton.pied_gauche)); 
                 alphabet.chargerCouleur(mot.charAt(4%mot.length()));
                 motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
             	this.squeletteHerbin.add(motif);
                break;
            case genou_droit:
                listePoints.add(getUpdatedPt(Skeleton.genou_droit));
                listePoints.add(getUpdatedPt(Skeleton.pied_droit)); 
                alphabet.chargerCouleur(mot.charAt(4%mot.length()));
                motif = new MotifFixeSkeletonPolyrect(listePoints,alphabet.getCouleur(),epaisseurPartieCorps);
            	this.squeletteHerbin.add(motif);
                break;
            default:
            	break;
            
            	 }

    }
    
    
}