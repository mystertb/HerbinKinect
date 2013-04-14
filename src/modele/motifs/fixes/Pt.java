package modele.motifs.fixes;

import outils.Geometrie;

/*
 * Classe Point redéfinie en fonction des besoins du programme, donc renommée en pt
 * Ces points sont des points 3D
 */

public class Pt {
	
	//Variables
	public double x,y,z;
	private Geometrie geo;
	
	//Constructeur avec parametres
	public Pt(double x, double y,double z) {
		
		this.x=x;
		this.y=y;
		this.z=z;
		geo = new Geometrie();
	}
	
	//Constructeur sans parametres (nulle par défaut)
	public Pt() {
		
		this.x=0;
		this.y=0;
		this.z=0;
		geo = new Geometrie();
		
	}
	
	//Constructeur d'un point 2D
	public Pt(double x, double y) {
		
		this.x=x;
		this.y=y;
		this.z=0;
		geo = new Geometrie();
		
	}
	
	//attribut les coordonnées d'un point donné en parametres a ce point actuel
	public void setPoint(Pt point) {
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
	}
	
	//Attribue un point en effectuant une rotation par rapport à un autre point
	public void setPoint(Pt point, double angleVecteur, Pt pCentre) {
		
		setPoint(point);
		rotate(angleVecteur,pCentre);
		
	}
	
	//attribue de nouvelles coordonées au point actuel par rotation par rapport à un point donné
	public void rotate(double angleVecteur, Pt pCentre) {
		
		this.setPoint(geo.getRotatedPoint(new Pt(x,y,z),angleVecteur,pCentre));
		
	}
	
	//retourne l'épaisseur selon la profondeur, les valeurs de z sont comprises entre 500 et 2200
	public double getEpaisseurSelonProfondeur(int epaisseur) {
		
		double tmpZ ;
		if(z>500) {
			tmpZ = z-500;
		} else {
			return epaisseur;
		}
		
		return  (epaisseur*((1700/tmpZ)+1))/2; 
		
	}

}
