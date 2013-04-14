/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.motifs.fixes;

/**
 *
 * @author rayanox
 * Classe vecteur définie spécialement pour ce programme
 * Un vecteur est défini par deux points qui sont un point de départ et un point d'arrivée.
 * Point de départ : Coordonnées (x1,y1)
 * Point d'arrivée : Coordonnées (x2,y2)
 */
public class Vecteur {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private final double longueurVecteurParfait = 14.1421356237;// A modifier pour changer la vitesse des motifs(14.1421356237 est la
 	// longueur pour un vecteur(x=10,y=10))
    
    //********************************************
    
    //Constructeur avec parametres
    public Vecteur(int x1, int y1, int x2, int y2) {
        this.x1= x1;
        this.y1 = y1;
        this.x2= x2;
        this.y2 = y2;
    }
    
    //Constructeur sans les parametres, créé un vecteur par défaut nulle
    public Vecteur() {
        this.x1= 0;
        this.y1 = 0;
        this.x2= 0;
        this.y2 = 0;
    }
    
 // MÃ©thode qui permet d'obtenir un vecteur de la mÃªme longueur que le
 	// vecteur parfait, mais en gardant
 	// la mÃªme direction que le premier vecteur
    public void translationVecteur() {
        
        int AC = this.getX2()-this.getX1();
        int CB = this.getY2()-this.getY1();
        
        double k = this.coefficientMultiplicateur(AC, CB);
        
        this.x1=0;
        this.y1=0;
        this.x2=(int) (AC*k);
        this.y2=(int) (CB*k);
        		
        		
    }
    
    //retourne un coefficient qui permettra de multiplier les coordonées du vecteur pour en obtenir une longueur définie
    public double coefficientMultiplicateur(int AC, int BC) {
        double k = (this.longueurVecteurParfait/ (Math.sqrt((AC*AC) + (BC*BC))));
        return k;
    }
    
    //*********************************************
    
    //Attribue une nouvelle abscisse au point 1 
    public void setX1(int x1) {
        this.x1 = x1;
    }
    
    //Attribue une nouvelle ordonnée au point 1
    public void setY1(int y1) {
        this.y1 = y1;
    }
    
    //retourne l'abscisse du point 1
    public int getX1() {
        return this.x1;
    }
    
    //retourne l'ordonnée du point 1
    public int getY1() {
        return this.y1;
    }
    
    //Attribue une nouvelle abscisse au point 2
    public void setX2(int x2) {
        this.x2 = x2;
    }
   
    //Attribue une nouvelle ordonnée au point 2
    public void setY2(int y2) {
        this.y2 = y2;
    }
    
    //retourne l'abscisse du point 2
    public int getX2() {
        return this.x2;
    }
    
    //Retourne l'ordonnée du point 2
    public int getY2() {
        return this.y2;
    }
}
