package outils;

import java.awt.Point;

import modele.motifs.fixes.Pt;

/*
 * Classe qui possède des méthodes pratiques pour effectuer des calculs géométriques
 */

public class Geometrie {

	//Constructeur par défaut
	public Geometrie() {
		
	}
	
	//renvoie la distance entre deux points (en utilisant pythagore)
	public double getModule(Pt p1, Pt p2) {
		
		double re,im;
		
		re = p1.x-p2.x;
       	im = p1.y-p2.y;
       	 return Math.sqrt(re*re+im*im);
	}
	
	//
	public Pt getRotatedPoint(Pt pt,double angleVecteur, Pt ptCentre ) {
		
		Pt rotatedPoint = new Pt(pt.x,pt.y,pt.z);
		
		rotatedPoint.x =  (Math.cos(angleVecteur)*(pt.x - ptCentre.x) -Math.sin(angleVecteur)*(pt.y - ptCentre.y) + ptCentre.x);
		rotatedPoint.y =  (Math.sin(angleVecteur)*(pt.x - ptCentre.x) + Math.cos(angleVecteur)*(pt.y - ptCentre.y) + ptCentre.y);
		
		return rotatedPoint;
		
	}
	
	public double getAngleVecteur(Pt p1, Pt p2) {
		
		double re, im;
		double mod, angleVecteur;
		double PI = Math.PI;
		
		re = p1.x-p2.x;
       	im = p1.y-p2.y;
       	mod = getModule(p2,p2);
       	if(im==0&&re<0) {
       	angleVecteur=PI;
       	} else { 
    angleVecteur=2*Math.atan(im/(re+mod));
    }
    	
   	angleVecteur = angleVecteur!=-Math.PI ? Math.signum(re)*(PI/2)+(angleVecteur/2):0;
   	
   	return angleVecteur;
		
	}
	
	
	
}
