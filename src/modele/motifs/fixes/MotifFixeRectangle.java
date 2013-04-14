package modele.motifs.fixes;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/*
 * Motif fixe du panel : forme Rectangle
 */



public class MotifFixeRectangle extends Motif {

	//Variables de classe
	protected int largeur;
	protected int hauteur;
	
	//Constructeur
	public MotifFixeRectangle(ArrayList<PtSkeleton> listePoints,
		Color couleur,int largeur,int hauteur) {
		
		super(listePoints, couleur);
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
		forme = new Rectangle2D.Double(this.getPoint(0).x,this.getPoint(0).y,largeur,hauteur);
		this.setArea(forme);
		
	}

}
