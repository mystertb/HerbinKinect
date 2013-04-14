package modele.motifs.fixes;

import java.awt.Color;

import java.awt.Polygon;
import java.util.ArrayList;




import outils.Geometrie;

/*
 * Motif spécifique au squelette de l'utilisateur(Les formes des figures sont adaptés au positionnement de l'utilisateur) .
 * Ceci permet la rotation des figures lorsque l'utilisateur bouge (les bras par exemple)
 */

public class MotifFixeSkeletonPolyrect extends Motif {

	//Variables de classe
	private Geometrie geo;
	private int largeur;
	
	//Constructeur
	public MotifFixeSkeletonPolyrect(ArrayList<PtSkeleton> listePoints, Color couleur, int epaisseur) {
		super(listePoints, couleur);
		this.largeur=epaisseur;
		completerForme();
		geo = new Geometrie();
		
	}
	
	//Complete la liste de points si elle est incomplete pour atteindre la taille définie (4)
	 public void completerForme() {
	    	
		   
	    	PtSkeleton tmp1 = listePoints.get(0);
	    	PtSkeleton tmp2 = listePoints.get(1);
	    	listePoints.remove(0);
	    	listePoints.remove(0);
	    		for(int i=0;i<4;i++) {
	    			this.listePoints.add(new PtSkeleton(Skeleton.aucun));
	    		}
	    	listePoints.add(tmp1);
	    	listePoints.add(tmp2);
	 }
	 
	 //Actualise la positions des points du motif en fonction du positionnement de l'utilisateur
	 public void actualiserPos() {
	    	
			
	    	Pt ptSkel1,ptSkel2,pTr1,pTr2,pTr3,pTr4, pCentre;
	    	ptSkel1 = this.listePoints.get(4);
	    	ptSkel2 = this.listePoints.get(5);
			double mod, angleVecteur, profondeurMoyenne;
			angleVecteur = geo.getAngleVecteur(ptSkel1, ptSkel2);
			mod = geo.getModule(ptSkel1,ptSkel2);
			profondeurMoyenne = (ptSkel1.getEpaisseurSelonProfondeur(largeur)+ptSkel2.getEpaisseurSelonProfondeur(largeur))/2;
			ptSkel1 = new Pt((int) (ptSkel1.x-profondeurMoyenne/2),ptSkel1.y);
			pTr1 = new Pt((int) (ptSkel1.x),ptSkel1.y);
	    	pTr2 = new Pt((int) (ptSkel1.x+profondeurMoyenne),ptSkel1.y);
	    	pTr3 = new Pt((int) (ptSkel1.x+profondeurMoyenne),(int) (ptSkel1.y+mod));
	    	pTr4 = new Pt(ptSkel1.x,(int) (ptSkel1.y+mod));
	    	
	    	pCentre = new Pt((int) (ptSkel1.x+(profondeurMoyenne)/2),ptSkel1.y);
	    	
	    	this.listePoints.get(0).setPoint(pTr1,angleVecteur,pCentre);
	    	this.listePoints.get(1).setPoint(pTr2,angleVecteur,pCentre);
	    	this.listePoints.get(2).setPoint(pTr3,angleVecteur,pCentre);
	    	this.listePoints.get(3).setPoint(pTr4,angleVecteur,pCentre);
	    	
	    }

	//Méthode héritée qui permet à ce motif d'actualiser sa forme
	 public void actualiserSurface() {

		 
		 this.actualiserPos();
		 Polygon p = new Polygon();
	        Pt tmp;
	        for(int i=0; i<4 && i<this.listePoints.size();i++) {
  		tmp=listePoints.get(i);
  		p.addPoint((int) tmp.x,(int) tmp.y);
  	}
  	forme = p;
	    setArea(p);
	    this.actualiserVect();
		 
	 }
}
