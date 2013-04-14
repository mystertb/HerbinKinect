package modele.motifs.fixes;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/*
 * Motif cercle fixe du corps 
 * 
 */




public class MotifFixeCercle extends Motif {

	//Variables
	protected int largeur;
	
	//Constructeur
	public MotifFixeCercle(ArrayList<PtSkeleton> listePoints, Color couleur,int epaisseur) {
		super(listePoints, couleur);
		this.largeur=epaisseur;
		this.actualiserSurface();
	}

	//Méthode héritée qui permet à ce motif d'actualiser sa forme
	public void actualiserSurface() {
		
		
		PtSkeleton tmp = listePoints.get(0);
    	double epaisseur;
        	if(listePoints.size()==1) {
        	epaisseur = listePoints.get(0).getPartieDuCorps().equals(Skeleton.aucun) ? largeur:(tmp.getEpaisseurSelonProfondeur(largeur));
            forme = new Ellipse2D.Double(Math.abs(tmp.x-(epaisseur/2)),Math.abs(tmp.y-(epaisseur/2)), epaisseur, epaisseur);
        	} else {
        		PtSkeleton tmp2 = listePoints.get(1);
        		epaisseur = (tmp.getEpaisseurSelonProfondeur(largeur));
                forme = new Ellipse2D.Double(Math.abs(tmp.x-(epaisseur))+tmp2.x, Math.abs(tmp.y-(epaisseur))+tmp2.y, epaisseur, epaisseur);
        	}
        	
		this.setArea(forme);
		this.actualiserVect();
	}

}