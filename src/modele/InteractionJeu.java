package modele;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import outils.OperationsImage;

import modele.deplacements.Mur;
import modele.interfaces.ObservateurJeu;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.mobiles.MotifMobile;

public class InteractionJeu {
	
	private int scoreMax;
	private int niveau;
	private int niveauActuel;
	private boolean gagne;
	private boolean jeuTermine;
	private boolean reinitialiser;
	private Environnement env;
	private ArrayList<MotifMobile> listeMenaces;
	private ArrayList<Mur> listeMurs;
	private final int width;
	private final int height;
	private ArrayList<PtSkeleton> pointsTarget;
	private OperationsImage op;
	private ArrayList<ObservateurJeu> listeObservateur;
	
	public InteractionJeu(int scoreMax, int niveau, int width, int height, ArrayList<PtSkeleton> pointsTarget) {

		this.listeObservateur = new ArrayList<ObservateurJeu>();
		
		this.setScoreMax(scoreMax);
		this.niveau=niveau;
		this.niveauActuel=1;
		this.gagne=false;
		this.jeuTermine=true;
		this.reinitialiser=false;
		
		this.height = height;
		this.width = width;		
		this.pointsTarget = pointsTarget;
		
		this.listeMenaces = new ArrayList<MotifMobile>();
		
		this.op = new OperationsImage();
		
	}

	public void addObs(ObservateurJeu o) {
		
		this.listeObservateur.add(o);
		
	}
	
	public void notifierObs() {
		
		for(int i=0;i<this.listeObservateur.size();i++) {
			
			this.listeObservateur.get(i).atualiserJeu(this.scoreMax, this.niveauActuel, this.jeuTermine);
			
		}
		
	}
	
	public void genererListeMenaces() {
		
		env.creerListeMotifs(this.niveauActuel,this.niveau);
		
		
	}
	
	public BufferedImage getVarianteSplash(Color couleur) {
		
		return this.op.getVariante(couleur);
		
	}
	
	public void viderListeMenaces() {
		this.listeMenaces = new ArrayList<MotifMobile>();
	}
	
	public void genererEnvironnementAttaque() {
		this.env = null;
		this.env = new EnvironnementJeu(width,height,this.pointsTarget);
		this.env.creerMurs();
		this.listeMurs = env.getListeMurs();
		this.listeMenaces=env.getListeMotifs();
	}
	
	public void genererEnvironnementLibre() {
		env = new EnvironnementLibre(width, height, pointsTarget);
		env.creerMurs();
		this.listeMurs = env.getListeMurs();
		this.listeMenaces = env.getListeMotifs();
		this.genererListeMenaces();
	}
	
	public ArrayList<MotifMobile> getListeMenaces() {
		
		return this.listeMenaces;
		
	}
	
	
	public ArrayList<Mur> getListeMurs() {
		
		return this.listeMurs;
		
	}
	
	public ArrayList<MotifMobile> getEnvironnement() {
		
		return this.listeMenaces;
		
	}
	
	public int getNiveauActuel() {
		
		return this.niveauActuel;
		
	}
	
	public int getScoreMax() {
		return scoreMax;
	}

	public void setJeuTermine(boolean statusJeu) {
		
		this.jeuTermine=statusJeu;
		this.notifierObs();
		
	}
	
	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}
	
	public void setReinitialiser(boolean reinitialiser) {
		
		this.reinitialiser=reinitialiser;
		this.notifierObs();
		
	}

	public void setGagne(boolean gagne) {

		this.gagne=gagne;
		if(!gagne) {
			this.reinitialiser=true;
		} else if(gagne&&(this.niveauActuel+1)<=this.niveau) {
			this.niveauActuel++;
			this.scoreMax+=500;
			this.reinitialiser=true;
		} else {
			this.reinitialiser=true;
			this.jeuTermine=true;
		}
		this.notifierObs();
	}
	
	public void reinitialiser() {
		
		this.scoreMax=500;
		this.niveauActuel=0;
		
	}
	
	public boolean getGagne() {
		
		return this.gagne;
		
	}
	
	public boolean getReinitialiser() {
		
		return this.reinitialiser;
		
	}
	
	public boolean getJeuTermine() {
		
		return this.jeuTermine;
		
	}
	
}
