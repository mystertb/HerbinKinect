package modele.motifs.fixes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Timer;

/*
 * Classe Mère de tout type de motif
 * 
 */


public abstract class Motif extends Area {

	//Variables de classe
	protected ArrayList<PtSkeleton> listePoints;
	protected Shape forme;
	protected Color couleur;
	protected Vecteur vecteurMotif;
	
	//Constructeur
	public Motif(ArrayList<PtSkeleton> listePoints, Color couleur) {
		
		this.listePoints=listePoints;
		this.couleur=couleur;

        this.vecteurMotif = new Vecteur((int) this.getPoint(0).x,(int) this.getPoint(0).y,(int)this.getPoint(0).x,(int)this.getPoint(0).y);
	
	}
	
	public abstract void actualiserSurface();
	

	//retourne le motif actuel
	public Motif getMotif() {
		
		this.actualiserSurface();
		return this;
		
	}
	
	//Attirbue une nouvelle surface au motif actuel
	public void setArea(Shape forme) {
		
		Area surface = new Area(forme);
        this.reset();
        this.add(surface);
		
	}
	
	//retourne le point à l'indice i de la liste de points du motif
	public PtSkeleton getPoint(int i) {
		
		return this.listePoints.get(i);
		
	}
	
	//retourne la taille de la liste de points du motif
	public int listePtSize() {
		
		return this.listePoints.size();
		
	}
	
	//retourne la couleur du motif actuel
	public Color getCouleur() {
		
		return this.couleur;
		
	}


	//Attribue une couleur au motif actuel
	 public void setColor(int r, int g, int b) {
	    	
	    	
	    	this.couleur = new Color(estCouleur(r),estCouleur(g),estCouleur(b));
	    	
	    }
	    //test si le motif actuel est une couleur et si oui, retourne la valeur de la couleur, sinon retourne 0
	    public int estCouleur(int x) {
	    	
	    	if(x>255) {
	    		return 0;
	    	} else {
	    		return x;
	    	}
	    	
	    }
	    
	    //actualise les données du vecteur du motif
		public void actualiserVect() {
			
			 	this.vecteurMotif.setX1(this.vecteurMotif.getX2());
		        this.vecteurMotif.setY1(this.vecteurMotif.getY2());
		        //Puis on met les nouvelles coordonnÃ©es dans le deuxieme point du vecteur soit (x2, y2)
		        this.vecteurMotif.setX2((int) this.listePoints.get(0).x);
		        this.vecteurMotif.setY2((int) this.listePoints.get(0).y);
		        //if(this.listePoints.get(0).getPartieDuCorps().equals(Skeleton.main_gauche))
		        //System.out.println("Actualisation : Vecteur dde la main : x = "+(this.vecteurMotif.getX2()-this.vecteurMotif.getX1())+"\t y = "+(this.vecteurMotif.getY2()-this.vecteurMotif.getY1()));
		}
		
		//attribue une nouvelle abscisse au motif et actualise le vecteur du motif en conséquence
		public void setX(int x1) {
	        
	        //AJOUTÃ‰S A LINTEGRATION
	        //On met les anciennes coordonnÃ©es dans le premier point du vecteur soit (x1,y1)
	        this.vecteurMotif.setX1(this.vecteurMotif.getX2());
	        //Puis on met les nouvelles coordonnÃ©es dans le deuxieme point du vecteur soit (x2, y2)
	        this.vecteurMotif.setX2(x1);
	        
	        this.listePoints.get(0).x = x1;
	    }

		//attribue une nouvelle ordonnée au motif et actualise le vecteur du motif en conséquence
	    public void setY(int y1) {
	        
	        
	        //On met les anciennes coordonnÃ©es dans le premier point du vecteur soit (x1,y1)
	        this.vecteurMotif.setY1(this.vecteurMotif.getY2());
	        //Puis on met les nouvelles coordonnÃ©es dans le deuxieme point du vecteur soit (x2, y2)
	        this.vecteurMotif.setY2(y1);
	        
	        this.listePoints.get(0).y = y1;
	        
	    }
		
		//retourne vrai si le motif actuel est en intersection avec un motif donné en parametre
	    public boolean intersecte(Area a) {

	        Area tmp = new Area(a);
	        tmp.intersect(this);
	        return !tmp.isEmpty();
	    }
	    
	    //Retourne vrai si le motif actuel est en intersection avec un point donné (utile pour le système avec souris)
	    public boolean intesecteAvecPoint(Point p) {
	        if(this.contains(p)) return true;
	        else return false;
	    }
	    
	    //retourne le vecteur du motif actuel
	    public Vecteur getVecteurMotif() {
	        return this.vecteurMotif;
	    }
	    
	    //retourne vrai si la liste des points comporte un point du squelette
	   public boolean hasSkeleton(Skeleton skel) {
		   
		   boolean found = false;
		   
		   for(int i=0;i<this.listePtSize();i++) {
			   
			   if(this.listePoints.get(i).getPartieDuCorps().equals(skel)) {
				   found=true;
			   }
			   
		   }
		   
		   return found;
		   
	   }
	    
}
