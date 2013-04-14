package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

import modele.Orientation;
import modele.motifs.fixes.Pt;
import modele.motifs.fixes.PtSkeleton;

/*
 * Motif mobile : triangle
 */


public class MotifMobileTriangle extends MotifMobile {

	//Variables de classe
	private int epaisseur;
	
	//Constructeur
	public MotifMobileTriangle(ArrayList<PtSkeleton> listePoints,
			Color couleur, int epaisseur, int niveau) {
		super(listePoints, couleur, niveau);
		this.epaisseur=epaisseur;
		this.listePoints.add(new PtSkeleton());
		this.listePoints.add(new PtSkeleton());
		this.actualiserSurface();
		// TODO Auto-generated constructor stub
	}

	//retourne le 3ème point du triangle pour que le triangle soit bien équilatéral
	public Pt getEquilateral() {
       
	double x1,y1,x2,y2;
	
	x1=this.listePoints.get(0).x;
	y1=this.listePoints.get(0).y;
	x2=this.listePoints.get(3).x;
	y2=this.listePoints.get(3).y;
	
  	  Pt p3=new Pt();      
  	  double s60 = Math.sin(Math.PI / 3);    
  	  double c60 = Math.cos(Math.PI / 3);
  	    double X= c60 * (x1 - x2) - s60 * (y1 - y2) + x2;
  	    double Y= s60 * (x1 - x2) + c60 * (y1 - y2) + y2;

	      p3.x=(int) X;
  	    if(y1>=this.getPoint(1).y) {
  	      p3.y=(int) Y;		
  	      this.sens=Orientation.haut;
  	    } else {
  	  	      p3.y=(int) Y+2*(y1-Y);	
  	  	  this.sens=Orientation.bas;
  	    }
  	      
  	    return p3;

  	    }

	//Méthode héritée qui permet à ce motif d'actualiser sa forme
	@Override
	public void actualiserSurface() {
		//this.actualiserVect();
		 Polygon p = new Polygon();
	        Pt pt1 = getPoint(0);
	        Pt pt2 = getPoint(3);
	        Pt pt3 = getPoint(4);
	        pt2.x=(int) pt1.x+getPoint(2).getEpaisseurSelonProfondeur(epaisseur);
	        pt2.y=(int) pt1.y;
	        pt3.x=getEquilateral().x;
	        pt3.y=getEquilateral().y;
    		p.addPoint((int) pt1.x,(int) pt1.y);
    		p.addPoint((int) pt2.x,(int) pt2.y);
    		p.addPoint((int) pt3.x,(int) pt3.y);
    	forme = p;
	    setArea(p);
		
	}

	//retourne un rectangle utile lors de la destruction du motif car une image apparait brevement
	@Override
	public Rectangle getRectangle() {
		// TODO Auto-generated method stub
		Rectangle rect;
		int hauteur, largeur;
		hauteur=(int) Math.abs((getPoint(4).y-getPoint(0).y));
		largeur=(int) Math.abs((getPoint(3).x-getPoint(0).x));
		int orientation = this.sens.equals(Orientation.haut) ? largeur:0;
		rect = new Rectangle((int) getPoint(0).x,(int) getPoint(0).y-orientation,largeur ,hauteur);
		return rect;
		
	}
	
	//retourne le nombre de dommages que fait le motif (en jeu), cette valeur est calculée en fonction de son niveau
	//Plus la figure aura un niveau élevé, plus elle fera de dégats au joueur.
	@Override
	public int getDmg() {
		return niveau*7;
	}

}
