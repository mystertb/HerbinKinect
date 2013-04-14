package modele;
import java.util.ArrayList;

import modele.motifs.fixes.Pt;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;


public class ExtraChecker {

	private ArrayList<PtSkeleton> listeNative;
	private int iterationAffichageEnvironnementLibre;
	private int iterationDebutDuJeu;
	private int iterationExit;
	private boolean afficherEnvironnementLibre;
	private boolean debutDuJeu;
	
	public ExtraChecker(ArrayList<PtSkeleton> listeNative) {
		
		iterationAffichageEnvironnementLibre = 0;
	    iterationDebutDuJeu = 0;
		iterationExit = 0;
		this.afficherEnvironnementLibre=false;
		this.debutDuJeu=false;
		this.listeNative=listeNative;
	}
	
	public boolean getDebutDuJeu() {
		
		return this.debutDuJeu;
		
	}
	
	public boolean getAfficherEnvironnementLibre() {
		
		return this.afficherEnvironnementLibre;
		
	}
	
	public void setDebutDuJeu(boolean statusJeu) {
		
		this.debutDuJeu=statusJeu;
		
	}
	
    public PtSkeleton getNativePt (Skeleton s) {
    	
    	for(int i=0;i<listeNative.size();i++) {
    		
    		if(listeNative.get(i).getPartieDuCorps().equals(s)) {
    			return listeNative.get(i);
    		}
    	}
    										
    	return new PtSkeleton(Skeleton.aucun);
    			
    	}
    	
	
	public void checkProfondeurMainDroiteEpauleDroite() {
		
		double profondeurEpauleMain = this.getNativePt(Skeleton.hanche_droite).z-this.getNativePt(Skeleton.main_droite).z;
		double profondeurHancheGaucheMainGauche = this.getNativePt(Skeleton.hanche_gauche).z-this.getNativePt(Skeleton.main_gauche).z;
		
		//System.out.println(profondeurEpauleMain);
		
		if(profondeurEpauleMain>450&&profondeurHancheGaucheMainGauche<=450) {
			
			iterationAffichageEnvironnementLibre++;
			
		}else {
			iterationAffichageEnvironnementLibre=0;
		}
		
		if(iterationAffichageEnvironnementLibre>30) {
			this.afficherEnvironnementLibre = !this.afficherEnvironnementLibre;
			this.iterationAffichageEnvironnementLibre = 0;
		}else {
			this.afficherEnvironnementLibre = false;
		}
		
	}
	
public void checkProfondeurDeuxMainsEpaules() {
		
	
	
		double profondeurHancheGaucheMainGauche = this.getNativePt(Skeleton.hanche_gauche).z-this.getNativePt(Skeleton.main_gauche).z;
		double profondeurEpauleMain = this.getNativePt(Skeleton.hanche_droite).z-this.getNativePt(Skeleton.main_droite).z;
		//System.out.println(profondeurEpauleMain);
		
		if(profondeurHancheGaucheMainGauche>450&&profondeurEpauleMain>450) {
			
			this.iterationDebutDuJeu++;
			
		} else {
			this.iterationDebutDuJeu=0;
		}
		
		if(iterationDebutDuJeu>30) {
			this.debutDuJeu=!this.debutDuJeu;
			this.iterationDebutDuJeu=0;
			
		}
		
	}

public void checkDeuxMainsEquiv() {
	
	Pt main_gauche = this.getNativePt(Skeleton.main_gauche);
	Pt main_droite = this.getNativePt(Skeleton.main_droite);
	
	double differenceMains = Math.abs(main_droite.x-main_gauche.x)+Math.abs(main_droite.y-main_gauche.y)+Math.abs(main_droite.y-main_gauche.y);
	
	if(differenceMains<=70) {
		this.iterationExit++;
	} else {
		this.iterationExit=0;
	}
	
	if(iterationExit>50) {
		System.exit(0);
	}
}

	public void actualiser() {
		
		this.checkDeuxMainsEquiv();
		this.checkProfondeurDeuxMainsEpaules();
		this.checkProfondeurMainDroiteEpauleDroite();
		
	}
	
	

}
