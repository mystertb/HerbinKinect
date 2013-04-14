package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import modele.motifs.fixes.PtSkeleton;

/*
 * Motif mobile : Rectangle
 */

public class MotifMobileRectangle extends MotifMobile  {

	//Variables de classe
	protected int largeur;
	protected int hauteur;
	protected int epaisseurHauteur;
	protected int epaisseurLargeur;
	
	//Constructeur
	public MotifMobileRectangle(ArrayList<PtSkeleton> listePoints,
		Color couleur,int largeur,int hauteur, int niveau) {
		super(listePoints, couleur, niveau);
		this.hauteur=hauteur;
		this.largeur=largeur;
		this.actualiserSurface();
		
	}

	//retourne la largeur du motif
	public int getLargeur() {
		
		return this.largeur;
		
	}
	
	//retourne la hauteur du motif
	public int getHauteur() {
		
		return this.hauteur;
		
	}

	//Méthode héritée qui permet à ce motif d'actualiser sa forme
	public void actualiserSurface() {
		
		 epaisseurLargeur=(int) this.getPoint(2).getEpaisseurSelonProfondeur(largeur);
		 epaisseurHauteur=(int) this.getPoint(2).getEpaisseurSelonProfondeur(hauteur);
		forme = new Rectangle2D.Double(this.getPoint(0).x,this.getPoint(0).y, epaisseurLargeur,epaisseurHauteur);
		this.setArea(forme);
		
	}

	//retourne le nombre de dommages que fait le motif (en jeu), cette valeur est calculée en fonction de son niveau
	//Plus la figure aura un niveau élevé, plus elle fera de dégats au joueur.
	@Override
	public int getDmg() {
		// TODO Auto-generated method stub
		return niveau*2;
	}

	//retourne un rectangle utile lors de la destruction du motif car une image apparait brevement
	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		Rectangle rect = new Rectangle((int) getPoint(0).x,(int) getPoint(0).y,epaisseurLargeur,epaisseurHauteur);
		return rect;
		
	}
	
}