package modele.motifs.fixes;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/*
 * Motif demi-cercle fixe du corps 
 * 
 * 
 */


public class MotifDCercle extends Motif {

	//Variable de classe
	protected int largeur;
	
	//Constructeur
	public MotifDCercle(ArrayList<PtSkeleton> listePoints, Color couleur,int epaisseur) {
		super(listePoints, couleur);
		this.largeur=epaisseur;
		this.actualiserSurface();
	}

	//Méthode héritée qui permet à ce motif d'actualiser sa forme
	@Override
	public void actualiserSurface() {

		PtSkeleton tmp = listePoints.get(0);
		PtSkeleton tmp2 = listePoints.get(1);
    	
    		double epaisseur = listePoints.get(0).getPartieDuCorps().equals(Skeleton.aucun) ? largeur:(tmp.getEpaisseurSelonProfondeur(largeur));
            
    		double epaisseurTete = (tmp.getEpaisseurSelonProfondeur(75));
    	if(listePoints.size()>1) {	
    		
    		forme = new Arc2D.Double(Math.abs(tmp.x-(epaisseurTete/6)),(Math.abs(tmp.y)),epaisseur,epaisseur, 0,-180,Arc2D.CHORD);
    	  
    	} else {
        forme = new Arc2D.Double(tmp.x,tmp.x,largeur,largeur, 0,180,Arc2D.CHORD);
    	}
    	this.setArea(forme);
    	this.actualiserVect();
	}

}