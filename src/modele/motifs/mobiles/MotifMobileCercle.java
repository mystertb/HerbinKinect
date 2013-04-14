package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import modele.motifs.fixes.PtSkeleton;

/*
 * Motif mobile : Cercle
 * 
 */


public class MotifMobileCercle extends MotifMobile {

	//Variables
	protected int largeur;
	protected double epaisseur;
	
	//Constructeur
	public MotifMobileCercle(ArrayList<PtSkeleton> listePoints, Color couleur,int epaisseur, int niveau) {
		super(listePoints, couleur, niveau);
		this.largeur=epaisseur;
		this.actualiserSurface();
	}
	
	//Méthode héritée qui permet à ce motif d'actualiser sa forme
	public void actualiserSurface() {
			
		PtSkeleton tmp = this.getPoint(0);
		PtSkeleton tmp2 = this.getPoint(2);
    	epaisseur=tmp2.getEpaisseurSelonProfondeur(largeur);
        	
            forme = new Ellipse2D.Double(Math.abs(tmp.x-(epaisseur/2)),Math.abs(tmp.y-(epaisseur/2)), epaisseur, epaisseur);

		this.setArea(forme);
		//this.actualiserVect();
	}

	//retourne le nombre de dommages que fait le motif (en jeu), cette valeur est calculée en fonction de son niveau
	//Plus la figure aura un niveau élevé, plus elle fera de dégats au joueur.
	@Override
	public int getDmg() {
		
		return niveau*5;
	}

	//retourne un rectangle utile lors de la destruction du motif car une image apparait brevement
	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle((int) getPoint(0).x,(int) getPoint(0).y,(int) epaisseur,(int) epaisseur);
		
		return rect;
		
	}

}