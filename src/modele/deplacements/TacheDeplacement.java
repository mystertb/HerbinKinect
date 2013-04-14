/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.deplacements;

//import com.sun.java.swing.plaf.motif.resources.motif;
import java.util.TimerTask;


import modele.motifs.fixes.Motif;
import modele.motifs.fixes.Vecteur;
import modele.motifs.mobiles.MotifMobile;

/**
 *
 * @author rayanox
 */
public class TacheDeplacement extends TimerTask{
    
    private Interaction deplacement;
    private MotifMobile motif;
    private Vecteur vecteur;
    
    //Constructeur pour une tache de déplacement quelconque
    public TacheDeplacement(Interaction d, MotifMobile m, Vecteur v) {
        this.deplacement = d;
        this.motif = m;
        this.vecteur = v;
    }
    
    //Constructeur pour une tache de rapprochement
    public TacheDeplacement(Interaction d, MotifMobile m) {
        this.deplacement = d;
        this.motif = m;
        this.vecteur = new Vecteur();
    }
    
    //méthode executée à chaque execution du timer
    @Override
    public void run() {
    	//System.out.println("Vecteur1 : x = "+(this.vecteur.getX2()-this.vecteur.getX1())+"\t y = "+(this.vecteur.getY2()-this.vecteur.getY1()));

    	if(this.deplacement.getJeuTermine()==true) {
    		this.collision(); 
    	}
    	this.collisionAvecMur();
    	//System.out.println("Vecteur2 : x = "+(this.vecteur.getX2()-this.vecteur.getX1())+"\t y = "+(this.vecteur.getY2()-this.vecteur.getY1()));
        this.deplacement.setNouvellePositionCollison(this.motif, this.vecteur);
        //System.out.println("Vecteur3 : x = "+(this.vecteur.getX2()-this.vecteur.getX1())+"\t y = "+(this.vecteur.getY2()-this.vecteur.getY1()));

    }
    
    //Méthode qui attribue un vecteur à la variable de classe "vecteur"
    public void setVecteur(Vecteur v) {
    	
    	this.vecteur=v;
    	
    }
    
    //test la collision avec un mur et redonne un nouveau vecteur dans le cas positif
    public void collisionAvecMur() {
    	boolean collision = false;
    	for(int i =0; i<this.deplacement.getListeMurs().size(); i++) {
    		if(this.motif.intersecte(this.deplacement.getListeMurs().get(i)) && this.motif.getEnCollisionAvecMur()==false) {
    			System.out.println("Ancien Vecteur : x = "+(this.vecteur.getX2()-this.vecteur.getX1())+"\t y = "+(this.vecteur.getY2()-this.vecteur.getY1()));
    			this.vecteur = this.deplacement.getListeMurs().get(i).transformationVecteurApresRebond(this.vecteur);
    			System.out.println("Nouveau Vecteur : x = "+(this.vecteur.getX2()-this.vecteur.getX1())+"\t y = "+(this.vecteur.getY2()-this.vecteur.getY1()));
    			this.motif.setEnCollisionAvecMur(true);
    			collision = true;
    		} else if(this.motif.intersecte(this.deplacement.getListeMurs().get(i))) {
    			collision = true;
    		}
    	}
    	if(collision==false && this.motif.getEnCollisionAvecMur()==true){
    		motif.setEnCollisionAvecMur(false);
    	}
    }
    //
    //retourne vrai si il y a intersection du motif en mouvement avec un autre motif
    public boolean intersection(Motif motif) {
        
        if(motif.intersecte(this.motif)) {
            
            
            return true;
        }
            
        else return false;
    }
    
    
    //Si le motif actuel rencontre un nouveau motif, le nouveau motif va alors commencer un dÃ©placement
    // dans la meme direction que le motif actuel
    public void collision() {
        
        int i =0;
        while(i<this.deplacement.getListeMotifs().size()) {
        	
            if(this.deplacement.getListeMotifs().get(i) != this.motif) {
                if(this.intersection(this.deplacement.getListeMotifs().get(i))) {
                	if(this.deplacement.getListeMotifs().get(i).getEnCollisionAvecMur()==false) {
                   

                        Vecteur vect = this.motif.getVecteurMotif();                        	
                        this.deplacement.getListeMotifs().get(i).setEnCollision(true);
                        //this.deplacement.setNouvellePositionCollison(this.deplacement.getListeMotifs().get(i), this.motif.getVecteurMotif());
                        this.deplacement.setNouvellePositionCollison(this.deplacement.getListeMotifs().get(i), new Vecteur((int) this.motif.getPoint(0).x, (int) this.motif.getPoint(0).y, (int) (this.motif.getPoint(0).x + this.motif.getCoefX()+3), (int) (this.motif.getPoint(0).y + this.motif.getCoefY()+3)));
                        vect = this.deplacement.getListeMotifs().get(i).getVecteurMotif();
                        this.deplacement.getListeMotifs().get(i).setEnCollision(true);
                        break;
                	}
                } else {
                	this.deplacement.getListeMotifs().get(i).setEnCollision(false);
                }
            }
            i++;
            
        }
        
    }
}
