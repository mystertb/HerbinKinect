package modele.motifs.mobiles;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Timer;

import modele.Orientation;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Vecteur;

/*
 * Classe mère des motifs qui ne sont pas des motifs du corps et qui peuvent se déplacer
 */

public abstract class MotifMobile extends Motif {

	//Variables de classe
	protected boolean enDeplacement;
	protected int coefX; //coefficient de dÃ©placement sur l'abscisse 
	protected int coefY; //coefficient de dÃ©placement sur l'ordonnÃ©e
	protected Timer timer;
	protected int coefXdepart;
	protected int coefYdepart;
	protected int pourcentage;
	protected boolean enCollisionAvecMur;
	protected boolean enCollisionAvecFigures;
	protected boolean enRapprochement;
	protected int niveau;
	protected int nbResistance;
	protected Orientation sens;
	protected boolean remove;
	protected int increment;
	
	//Constructeur
	public MotifMobile(ArrayList<PtSkeleton> listePoints, Color couleur,int niveau) {
		
		super(listePoints, couleur);
		this.listePoints=listePoints;
		this.couleur=couleur;
		this.remove=false;
		this.niveau=niveau;
        this.coefX = 0;
        this.coefY = 0;
        this.coefXdepart = 0;
        this.coefYdepart = 0;
        this.enDeplacement = false;
        this.timer = new Timer();
        this.pourcentage = 0;
        this.vecteurMotif = new Vecteur((int) this.getPoint(0).x,(int) this.getPoint(0).y,(int)this.getPoint(0).x,(int)this.getPoint(0).y);
		this.enCollisionAvecFigures=false;
		this.enRapprochement=false;
		this.increment = 0;
	}

	
	@Override
	public abstract void actualiserSurface();

	public abstract Rectangle getRectangle();
	
	//actualise le vecteur du motif pour qu'il soit opérationnel pour pointer sur la cible (lors d'une partie)
	public void refreshTarget() {
		
		Vecteur vecteurTarget = new Vecteur((int)this.getPoint(0).x,(int)this.getPoint(0).y,(int)this.getPoint(1).x,(int)this.getPoint(1).y);
		vecteurTarget.translationVecteur();
		
		this.setCoefXdepart(vecteurTarget.getX2()-vecteurTarget.getX1());
		this.setCoefYdepart(vecteurTarget.getY2()-vecteurTarget.getY1());
        
        this.setCoefX(this.getCoefXdepart());
        this.setCoefY(this.getCoefYdepart());
		
	}
	
	//retourne la variable d'incrémentation avec +1
	public int increment() {
		
		return this.increment++;
		
	}
	
	//indique au motif qu'il doit être détruit (utilisé lors d'une partie)
	public void setRemove() {
		this.remove=true;
	}
	
	//retourne vrai si le motif est un motif à détruire
	public boolean getRemove() {
		return this.remove;
	}
	
	//retourne le niveau du motif
	public int getNiveau() {
		
		return this.niveau;
		
	}
	
	public abstract int getDmg();
	
	//attribue vrai si la figure devient en rapprochement
	public void setEnRapprochement(boolean b) {
		
		this.enRapprochement=b;
		
	}
	
	//retourne vrai si le motif est en rapprochement
	public boolean isEnRapprochement() {
		
		return this.enRapprochement;
		
	}
	
	//attribue vrai à la variable "enCollisionAvecFigures" si le motif actuel est en collision avec n'importe quelle figure
	public void setEnCollision(boolean b) {
		this.enCollisionAvecFigures=b;
	}
	
	//décrémente le niveau du motif actuel
	public void diminuerNiveau() {
		
		niveau--;
		
	}
	
	//retourne vrai si le motif actuel est en collision avec un autre motif
	public boolean getEncollision() {
		
		return this.enCollisionAvecFigures;
		
	}
	
	//Attribue un pourcentage au motif (pour son déplacement)
	 public void setPourcentage(int p) {
	        this.pourcentage = p;
	    }
	    
	 //retourne la valeur du pourcentage de déplacement du motif
	 public int getPourcentage() {
	     return this.pourcentage;
	 }
	    
	 	//attribue un nombre de pixels en ordonnée pour le début du déplacement du motif
	    public void setCoefYdepart(int y) {
	        this.coefYdepart = y;
	    }
	    
	    //retourne le nombre de pixels en ordonnées pour le début du déplacement du motif
	    public int getCoefYdepart() {
	        return this.coefYdepart;
	    }
	    
	    //attribue un nombre de pixels en abscisses pour le début du déplacement du motif
	    public void setCoefXdepart(int x) {
	        this.coefXdepart = x;
	    }
	    
	    //retourne le nombre de pixels en abscisses pour le début du déplacement du motif
	    public int getCoefXdepart() {
	        return this.coefXdepart;
	    }
	    
	    //Créé un nouveau timer, le précédent est par la suite éliminé par le ramasse-miette car il n'est plus utilisé dans le programme
	    public void reInitialiserTimer() {
	        this.timer = new Timer();
	    }
	    
	    //retourne le timer du motif
	    public Timer getTimer() {
	        return this.timer;
	    }
	    
	    //retourne vrai si le motif est en déplacement (permet de ne pas pouvoir retoucher un motif lorsqu'il est en déplacement)
	    public boolean getEnDeplacement() {
	        return this.enDeplacement;
	    }
	    
	    //déclare si le motif est en déplacement ou non
	    public void setEnDeplacement(boolean b) {
	        this.enDeplacement = b;
	    }
	    
	    //Retourne le nombre de pixels actuellement utilisé en abscisse pour le déplacement du motif
	    public int getCoefX() {
	        return this.coefX;
	    }
	  //Retourne le nombre de pixels actuellement utilisé en ordonnée pour le déplacement du motif
	    public int getCoefY() {
	        return this.coefY;
	    }
	    
	    //Attribue un nombre de pixels actuellement utilisé en abscisse pour le déplacement du motif
	    public void setCoefX(int i) {
	        this.coefX = i;
	        //this.actualiserVect();
	    }
	    
	    // attribue un nombre de pixels actuellement utilisé en ordonnée pour le déplacement du motif
	    public void setCoefY(int i) {
	        this.coefY = i;
	        //this.actualiserVect();
	    }   
	    
	    //retourne vrai si le motif rencontre un mur
	    public boolean getEnCollisionAvecMur() {
	    	return this.enCollisionAvecMur;
	    }
	    
	    //déclare si le motif rencontre un mur ou non
	    public void setEnCollisionAvecMur(boolean b) {
	    	this.enCollisionAvecMur = b;
	    }
	
}
