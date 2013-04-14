package modele.motifs.fixes;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

/*
 * Motif fixe du corps : repr�sente un polygone quelconque dont on peut d�finir � souhait sa forme 
 */



public class MotifFixePolygone extends Motif {

	//Constructeur
	public MotifFixePolygone(ArrayList<PtSkeleton> listePoints, Color couleur) {
		super(listePoints, couleur);
		this.actualiserSurface();
	}

	//M�thode h�rit�e qui permet � ce motif d'actualiser sa forme
	public void actualiserSurface() {
		
		this.actualiserVect();
		 Polygon p = new Polygon();
	        Pt tmp;
	        for(int i=0; i<4 && i<this.listePoints.size();i++) {
     		tmp=listePoints.get(i);
     		p.addPoint((int) tmp.x,(int) tmp.y);
     		
     	}
     	forme = p;
	    setArea(p);
	    
	}


}
