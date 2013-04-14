package modele;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import modele.deplacements.Interaction;
import modele.interfaces.ObservateurJeu;
import modele.interfaces.ObservateurPersonnage;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.MotifFixeImage;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.mobiles.MotifMobile;
import outils.Sound;

public class InstanceKinect {

	private int width;
	private int height;
	private ArrayList<PtSkeleton> listePtSkel;
	private Interaction interaction;
	private ExtraChecker extraChecker;
	private Sound sound;
	private Personnage pers;
	private Decor decor;
	
	public InstanceKinect(int width, int height, ArrayList<PtSkeleton> listePtSkel) {
	
			//Tailles du champs de vision kinect, ce sera la taille de ce panel
			this.width = width;
			this.height = height;
			
			//Liste des adresses vers les parties du corps et leurs coordonnées
			this.listePtSkel=listePtSkel;
			
			this.pers = new Personnage(listePtSkel);
			//Instanciation de la classe servant à jouer de la musique
			this.sound = new Sound(); 
			//Jouer la musique
			this.sound.playStart();
			
			
			this.interaction = new Interaction(this.pers,this.width,this.height,this.listePtSkel,this.sound);
			
			//Instanciation de l'éxtra-checker, classe permettant de faire de la reconnaissance de gestes
			this.extraChecker = new ExtraChecker(listePtSkel);
			
			decor = new Decor(width,height);
			decor.creerListeImages();
			
	}
	
	public BufferedImage getVarianteSplash(Color couleur) {
		
		return this.interaction.getVarianteSplash(couleur);
		
	}
	
	public void removeEnv(MotifMobile motifMobile) {
		
		this.interaction.getListeMotifs().remove(motifMobile);
		
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public InteractionJeu getJeu() {
		
		return this.interaction.getJeu();
		
	}
	
	public Personnage getPers() {
		
		return this.pers;
		
	}
	
	public int getSizeCorps() {
		
		return pers.getSizeCorps();
		
	}
	
	public Motif getPartieCorps(int i) {
		
		return pers.get(i);
		
	}
	
	public void addObservateurJeu(ObservateurJeu obs) {
		
		this.getJeu().addObs(obs);
		
	}
	
	public void addObservateurPersonnage(ObservateurPersonnage obs) {
		
		this.getPers().addObs(obs);
		
	}
	
	public ArrayList<MotifFixeImage> getListeMotifImage() {
		
		return this.decor.getListeMotifImage();
		
	}
	
	public MotifFixeImage getMotifImage (int i) {
		
		return this.getListeMotifImage().get(i);
		
	}
	
	public void setText(String mot){
		this.pers.setText(mot);
	}
	
	public MotifFixeImage getMotifImage(String name) {
		
		return this.decor.getMotifImage(name);
		
	}
	
	public int getEnvironnementSize() {
		
		return this.interaction.getListeMotifs().size();
		
	}
	
	public MotifMobile getEnvironnement(int i) {
		
		return this.interaction.getListeMotifs().get(i);
		
	}
	
	public void detected() {
		
		sound.playHome();
		pers.setDetected(true);
		
	}
	
	public void actualiser() {
		
		
		
		this.extraChecker.actualiser();
		
		if(this.extraChecker.getDebutDuJeu()) {
			if(this.interaction.getJeuTermine()) {
				this.interaction.getJeu().genererEnvironnementAttaque();
				this.interaction.actualiserListeMotifs();
				this.interaction.lancerJeu();
			
			} else {
				
			this.interaction.finirJeu();
			this.sound.playHome();
			}
			
			this.extraChecker.setDebutDuJeu(false);
		} 
		
		this.interaction.actualiserDeplacement();
		this.decor.reinit();	
		if(this.interaction.getJeuTermine()) {
		if(this.extraChecker.getAfficherEnvironnementLibre()) {
			this.interaction.getJeu().genererEnvironnementLibre();
			this.interaction.actualiserListeMotifs();
		}
		//this.sound.playHome();
		this.decor.validate("art.jpg");
		this.decor.validate("tvlcd.gif");
		} else {
		this.decor.validate("matrix2.jpg");	
		}
		
	}
	
}
