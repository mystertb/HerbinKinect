package modele.motifs.fixes;

import java.awt.Color;
import java.util.ArrayList;

/*
 * 
 * Motif fixe  : forme triangle équilatéral
 * 
 */


public class MotifFixeTriEqu extends MotifFixePolygone {

	public MotifFixeTriEqu(ArrayList<PtSkeleton> listePoints, Color couleur) {
		super(listePoints, couleur);
		// TODO Auto-generated constructor stub
		this.listePoints.add(new PtSkeleton());
		this.listePoints.get(2).setPoint(getEquilateral());
		this.actualiserSurface();
	}

	//retourne le 3ème point du triangle pour que le triangle soit bien équilatéral
	public Pt getEquilateral() {
       
	double x1,y1,x2,y2;
	
	x1=this.listePoints.get(0).x;
	y1=this.listePoints.get(0).y;
	x2=this.listePoints.get(1).x;
	y2=this.listePoints.get(1).y;
	
  	  Pt p3=new Pt();      
  	  double s60 = Math.sin(Math.PI / 3);    
  	  double c60 = Math.cos(Math.PI / 3);
  	    double X= c60 * (x1 - x2) - s60 * (y1 - y2) + x2;
  	    double Y= s60 * (x1 - x2) + c60 * (y1 - y2) + y2;
  	    
  	      p3.x=(int) X;
  	      p3.y=(int) Y;		  
  	      
  	    return p3;

  	    }

}
