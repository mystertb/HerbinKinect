package modele;

import java.awt.Color;
import java.util.ArrayList;

import modele.interfaces.ObservateurJeu;
import modele.interfaces.ObservateurPersonnage;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.SkeletonMaker;

public class Personnage {
	
	private int vie;
	private int score;
	private ArrayList<Motif> corps;
	private boolean detected;
	private ArrayList<ObservateurPersonnage> listeObservateurs;
	SkeletonMaker skelMaker;
	public Personnage(ArrayList<PtSkeleton> listePtNative) {
		
		this.vie=100;
		this.score=0;
		this.detected=false;
		 skelMaker = new SkeletonMaker(listePtNative);
		 skelMaker.startText("nouveau");
		this.corps = skelMaker.getSqueletteHerbin();
		this.listeObservateurs = new ArrayList<ObservateurPersonnage>();
		
	}

	public void setText(String mot){
		this.skelMaker.startText(mot);
		this.corps=skelMaker.getSqueletteHerbin();
	}
	
	public void addObs(ObservateurPersonnage o) {
		
		this.listeObservateurs.add(o);
		
	}
	
	public void notifierObs() {
		
		for(int i=0;i<this.listeObservateurs.size();i++) {
			
			this.listeObservateurs.get(i).actualiserPersonnage(vie, score, getCouleurVie());
			
		}
		
	}
	
	//Retourne le nombre de motifs composant le corps
	public int getSizeCorps() {
		if(this.isDetected()) {
		return this.corps.size();
		} else {
			return 0;
		}
	}
	
	//Retourne la partie du corps
	public Motif get(int i) {
		return this.corps.get(i);
	}
	
	public void setDetected(boolean statusDetection) {
		
		this.detected=statusDetection;
		
	}
	
	public boolean isDetected() {
		
		return this.detected;
		
	}
	
	public void reinitialiserVie() {
		
		this.vie=100;
		this.notifierObs();
	}
	
	public Color getCouleurVie() {
		
		if(this.vie>66) {
			return Color.green;
		}else if (this.vie>33) {
			return Color.orange;
		}else {
			return Color.red;
		}
		
		
	}
	
	public void reinitialiserScore() {
		
		this.score=0;
		this.notifierObs();
		
	}
	
	public ArrayList<Motif> getListeCorps() {
		
		return this.corps;
		
	}
	
	public void setDommages(int dmg) {
		
		if((vie-dmg)>0) {
		this.vie-=dmg;
		} else {
			vie=0;
		}
		this.notifierObs();
		
	}
	
	public void setScore(int score) {
		this.score+=score;
		this.notifierObs();
		
	}
	
	public int getScore() {
		
		return this.score;
		
	}
	
	public int getVie() {
		
		return this.vie;
		
	}
	
	
	
}
