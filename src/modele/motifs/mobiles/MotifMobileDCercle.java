package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.util.ArrayList;


import modele.Orientation;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;

/*
 * Motif mobile : Demi-cercle
 */

public class MotifMobileDCercle extends MotifMobile {

	//Variables de classe
	protected int largeur;
	protected double epaisseur;
	
	//Constructeur
	public MotifMobileDCercle(ArrayList<PtSkeleton> listePoints, Color couleur,
			int largeur,int niveau) {
		super(listePoints, couleur, niveau);
		// TODO Auto-generated constructor stub
		this.largeur=largeur;
		this.actualiserSurface();
		
	}
	
	//Méthode héritée qui permet à ce motif d'actualiser sa forme
	@Override
	public void actualiserSurface() {

		PtSkeleton premierPt = listePoints.get(0);
    	PtSkeleton partieDuCorps = getPoint(1);
    		 epaisseur = (partieDuCorps.getEpaisseurSelonProfondeur(largeur));
    		 
    		 int orientation;
    		 if(premierPt.y>partieDuCorps.y) {
    			 orientation=1;
    			 this.sens=Orientation.haut;
    		 } else {
    			 orientation=-1;
    			 this.sens=Orientation.bas;
    		 }
    		
        forme = new Arc2D.Double(premierPt.x,premierPt.y,epaisseur,epaisseur, 0,orientation*180,Arc2D.CHORD);
    	
    	this.setArea(forme);
	}

	//retourne le nombre de dommages que fait le motif (en jeu), cette valeur est calculée en fonction de son niveau
	//Plus la figure aura un niveau élevé, plus elle fera de dégats au joueur.
	@Override
	public int getDmg() {

		return niveau*10;
	}
	
	//retourne un rectangle utile lors de la destruction du motif car une image apparait brevement
	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		
		Rectangle rect = new Rectangle((int) getPoint(0).x,(int) getPoint(0).y,(int) epaisseur,(int) epaisseur/2);
		
		return rect;
		
	}

}