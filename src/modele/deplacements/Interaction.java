/*
 * Classe qui s'occupe de tout ce qui est d√©placements des motifs
 */
package modele.deplacements;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import outils.Sound;

import modele.InteractionJeu;
import modele.Personnage;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;
import modele.motifs.fixes.Vecteur;
import modele.motifs.mobiles.MotifMobile;

/**
 * 
 * @author rayanox
 * 
 * Classe qui gËre tous les dÈplacements de motifs et interaction avec le jeu
 */
public class Interaction {

	//Variables de classe
	private MotifMobile figureEnCollision;
	private Motif figureDuCorpsEnCollision;
	private ArrayList<MotifMobile> listeMotifsAlphabet;
	private ArrayList<Mur> listeDeMurs;
	private ArrayList<Motif> listeSquelette;
	private Personnage perso;
	private InteractionJeu jeu;
	private Sound son;

	

	// Constructeur
	public Interaction(Personnage perso, int width, int height,
			ArrayList<PtSkeleton> listeNative, Sound s) {

		this.jeu = new InteractionJeu(500, 5, width, height, listeNative);
		this.listeMotifsAlphabet = this.getListeMotifs();
		this.listeSquelette = perso.getListeCorps();
		this.listeDeMurs = this.getListeMurs();
		this.figureEnCollision = null;
		this.figureDuCorpsEnCollision = null;
		this.perso = perso;
		this.son=s;
	}
	
	//Actualise la liste des motifs en pointant vers la nouvelle liste dans "jeu" (car il y a parfois eu un new())
	public void actualiserListeMotifs() {
		this.listeMotifsAlphabet = this.getListeMotifs();
	}

	//retourne la liste des figures en jeu
	public ArrayList<MotifMobile> getListeMotifs() {
		return this.jeu.getListeMenaces();
	}

	//retourne la liste des motifs du corps
	public ArrayList<Motif> getListeSquelette() {
		return this.listeSquelette;
	}

	//retourne la liste des murs(il y en a 4)
	public ArrayList<Mur> getListeMurs() {
		return this.jeu.getListeMurs();
	}

	//retourne le jeu
	public InteractionJeu getJeu() {
		
		return this.jeu;
		
	}

	//retourne vrai  si le joueur a gagnÈ la partie
	public boolean isJeuGagne() {

		return this.jeu.getGagne();

	}

	//retourne vrai si une des figures du corps est en collision avec un motif mobile
	public boolean collision() {
		
		
		
    	if(!jeu.getJeuTermine()) {
    		if(jeu.getReinitialiser()) {
    			perso.reinitialiserVie();
        		if(!jeu.getGagne()) {
        		perso.reinitialiserScore();
        		} 
        		jeu.setReinitialiser(false);
        	}
    		
    		this.jeu.genererListeMenaces();
    	
    		//Boucle de tests pour chaque motif
	        for(int i =0; i<this.listeSquelette.size(); i++) {
	        	
	        	for(int u=0; u<this.listeMotifsAlphabet.size(); u++) {
	        		
	        		if(!this.listeMotifsAlphabet.get(u).getRemove()) {
	        			
		        		if(this.listeMotifsAlphabet.get(u).getNiveau()>0) {
		        		
			        		if(!this.listeMotifsAlphabet.get(u).getEnDeplacement()&&this.listeSquelette.get(i).intersecte(this.listeMotifsAlphabet.get(u))) {
			        			
			        			this.figureEnCollision = this.listeMotifsAlphabet.get(u);
			        			this.figureDuCorpsEnCollision = this.listeSquelette.get(i);
			        			
			        			
			        			
			        			this.jouer();
			        			
			        			this.figureEnCollision.setEnCollision(true);
			        			return true;
			        			
			        		} else {
			        			this.listeMotifsAlphabet.get(u).setEnCollision(false);
			        		}
		        		} else {
		        			this.son.playSplash();
		        			this.listeMotifsAlphabet.get(u).setRemove();
		        		}
		        	}
	        	
	        	}
	    	}
		} else {
			for(int i =0; i<this.listeSquelette.size(); i++) {
	        	
	        	for(int u=0; u<this.listeMotifsAlphabet.size(); u++) {
	        		
	        		
	        			//System.out.println("Niveau figure n∞"+u+" = "+this.listeMotifsAlphabet.get(u).getNiveau());
		        		
		        		
			        		if((!this.listeMotifsAlphabet.get(u).getEnDeplacement())&&this.listeSquelette.get(i).intersecte(this.listeMotifsAlphabet.get(u))) {
			        			
			        			this.figureEnCollision = this.listeMotifsAlphabet.get(u);
			        			this.figureDuCorpsEnCollision = this.listeSquelette.get(i);
			        			
			        			if(this.figureDuCorpsEnCollision.getPoint(0).getPartieDuCorps().equals(Skeleton.main_gauche))
			        				System.out.println("OUI");
			        			
			        			this.figureEnCollision.setEnCollision(true);
			        			return true;
			        			
			        		} else {
			        			this.listeMotifsAlphabet.get(u).setEnCollision(false);
			        		}
		        		
		        	
	        	
	        	}
	    	}
		}
    	//Si aucune collision n'a ÈtÈ dÈtectÈe, on rÈinitialise les figures temporaires ‡ null et on retourne faux
        this.figureEnCollision = null;
        this.figureDuCorpsEnCollision = null;
        return false;
    }
	
	//Retourne la meme image mais colorÈe avec la couleur donnÈe en parametre
	public BufferedImage getVarianteSplash(Color couleur) {
		
		return this.jeu.getVarianteSplash(couleur);
		
	}
	
	//retourne le score Maximum ‡ atteindre
	public int getScoreMax() {
		
		return this.jeu.getScoreMax();
		
	}
	
	//retourne le score actuel du joueur
	public int getScore() {
		
		return this.perso.getScore();
		
	}
	
	//Retourne les points de vie du joueur (points sur 100)
	public int getVie() {
		
		return this.perso.getVie();
		
	}
	
	//Retourne le niveau de la partie
	public int getNiveau() {
		
		return this.jeu.getNiveauActuel();
		
	}

	//retourne vrai si le jeu est terminÈ
	public boolean getJeuTermine() {
		
		return this.jeu.getJeuTermine();
		
	}
	
	//inverse le booleen de partie et permet donc un dÈbut de jeu
	public void lancerJeu() {

		this.son.playGame();
		this.jeu.setJeuTermine(false);

	}

	//RÈinitialise les parametre nÈcessaires pour arreter la partie sans problemes logiques
	public void finirJeu() {
		
		this.perso.reinitialiserScore();
		this.perso.reinitialiserVie();
		this.jeu.reinitialiser();
		this.jeu.setJeuTermine(true);
		this.jeu.viderListeMenaces();
		this.actualiserListeMotifs();
		
	}
	
	//joue un tour
	public void jouer() {
		if (figureDuCorpsEnCollision.hasSkeleton(Skeleton.main_droite)
				|| figureDuCorpsEnCollision.hasSkeleton(Skeleton.main_gauche)
				|| figureDuCorpsEnCollision.hasSkeleton(Skeleton.coude_droit)
				|| figureDuCorpsEnCollision.hasSkeleton(Skeleton.coude_gauche)) {
			
			
			if (!this.jeu.getJeuTermine()) {
			
			 if( perso.getScore() < jeu.getScoreMax()) {
				this.perso.setScore(figureEnCollision.getDmg());
			} else {
				this.jeu.setGagne(true);
			}
			 this.figureEnCollision.diminuerNiveau();
			}

		} else if (figureDuCorpsEnCollision.hasSkeleton(Skeleton.tete)) {
			if (!this.jeu.getJeuTermine() && perso.getVie() > 0) {
				this.perso.setDommages(figureEnCollision.getDmg());
			} else {
				this.jeu.setGagne(false);
			}
			this.figureEnCollision.diminuerNiveau();
		}
		
	}

	//MÈthode qui attribue une nouvelle position ‡ un motif qui doit se rapprocher d'un point (utilisÈ en partie)
	public void setNouvellePositionRapprochement(MotifMobile motif) {

		if (!motif.isEnRapprochement()) {

			motif.setEnRapprochement(true);
			motif.getTimer()
					.schedule(new TacheDeplacement(this, motif), 0, 100);

		} else {

			if (motif.getPourcentage() >= 50) {
				motif.setPourcentage(0);
			}

			motif.refreshTarget();
			// motif.setPourcentage(motif.getPourcentage()+10);

			if (motif.getEncollision() || motif.getEnDeplacement() || motif.getRemove()) {
				motif.setPourcentage(0);
				motif.getTimer().cancel();
				motif.reInitialiserTimer();
				motif.setEnRapprochement(false);
			}

			motif.setX((int) (motif.getPoint(0).x + motif.getCoefX()));
			motif.setY((int) (motif.getPoint(0).y + motif.getCoefY()));

			motif.actualiserSurface();
		}

	}

	//MÈthode qui attribue une nouvelle position ‡ un motif en fonction de son vecteur et du pourcentage d'avancement de la figure
	public void setNouvellePositionCollison(MotifMobile motif, Vecteur vecteur) {

		//Effectue une translation du vecteur pour qu'il ait une distance dÈfinie comme une vitesse parfaite(pour Èviter les problemes 
		// liÈs au bugs de dÈtection du matÈriel)
		
		

		System.out.println("VECTEUR1 : x = "+(vecteur.getX2()-vecteur.getX1())+"\t y = "+(vecteur.getY2()-vecteur.getY1()));

		if (!motif.getEnDeplacement()) {
			
			System.out.println("Ancien Vecteur : x = "+(vecteur.getX2()-vecteur.getX1())+"\t y = "+(vecteur.getY2()-vecteur.getY1()));
			vecteur.translationVecteur();
			System.out.println("Nouveau Vecteur : x = "+(vecteur.getX2()-vecteur.getX1())+"\t y = "+(vecteur.getY2()-vecteur.getY1()));


			motif.setCoefXdepart((vecteur.getX2() - vecteur.getX1()));// valeur
																		// √†
																		// changer
																		// pour
																		// augmenter
																		// la
																		// vitesse
																		// du
																		// motif
			motif.setCoefYdepart((vecteur.getY2() - vecteur.getY1()));

			motif.setCoefX(motif.getCoefXdepart());
			motif.setCoefY(motif.getCoefYdepart());

			motif.setEnDeplacement(true);
			System.out.println("VecteurTMP : x = "+(vecteur.getX2()-vecteur.getX1())+"\t y = "+(vecteur.getY2()-vecteur.getY1()));

			motif.getTimer().schedule(
					new TacheDeplacement(this, motif, vecteur), 0, 100);// lance
																		// une
																		// tache
																		// de
																		// d√©placement
																		// √†
																		// intervalle
																		// r√©gulier
																		// jusqu'√†
																		// annulation
																		// de la
																		// tache
																		// (cancel)
		} else {

			if (motif.getEnCollisionAvecMur() == true) {
				motif.setCoefXdepart((vecteur.getX2() - vecteur.getX1()));
				motif.setCoefYdepart((vecteur.getY2() - vecteur.getY1()));
				
			}

			
			motif.setPourcentage(motif.getPourcentage() + 5);// incr√©mentation
																// du
																// pourcentage


			motif.setCoefX((motif.getCoefXdepart() - (motif.getCoefXdepart()
					* motif.getPourcentage() / 100)));// On enl√®ve au nouveau
														// coefficient 5% par
														// rapport √† l'ancien
			motif.setCoefY((motif.getCoefYdepart() - (motif.getCoefYdepart()
					* motif.getPourcentage() / 100)));

			//Condition pour arreter le mouvement d'une figure
			if (motif.getPourcentage() >= 100 || motif.getRemove()) {
				motif.setPourcentage(0);
				motif.getTimer().cancel();
				motif.reInitialiserTimer();
				motif.setEnDeplacement(false);
				motif.setEnCollisionAvecMur(false);
			}
			
		}
		//System.out.println("VECTEUR2 : x = "+(vecteur.getX2()-vecteur.getX1())+"\t y = "+(vecteur.getY2()-vecteur.getY1()));
		//Attribution des nouvelles coordonnÈes 
		motif.setX((int) (motif.getPoint(0).x + motif.getCoefX()));
		motif.setY((int) (motif.getPoint(0).y + motif.getCoefY()));

		
		motif.actualiserSurface();// on recr√©e une nouvelle surface adapt√©e
									// aux nouvelles coordonn√©es.
		

	}

	

	// M√©thode execut√©e √† chaque d√©placement du corps(lanc√©e par le
	// paint de la toile)
	public void actualiserDeplacement() {
		if (this.collision()) {
			Vecteur v = new Vecteur(this.figureDuCorpsEnCollision.getVecteurMotif().getX1(), this.figureDuCorpsEnCollision.getVecteurMotif().getY1(), this.figureDuCorpsEnCollision.getVecteurMotif().getX2(), this.figureDuCorpsEnCollision.getVecteurMotif().getY2());
			System.out.println("Vecteur du corps en collision : x = "+(v.getX2()-v.getX1())+"\t y = "+(v.getY2()-v.getY1()));
			this.setNouvellePositionCollison(this.figureEnCollision,
					v);
					System.out.println("Vecteur du corps en collision : x = "+(v.getX2()-v.getX1())+"\t y = "+(v.getY2()-v.getY1()));
			
		}
		if(!this.jeu.getJeuTermine()) {
			for (int i = 0; i < this.listeMotifsAlphabet.size(); i++) {
	
				if (!this.listeMotifsAlphabet.get(i).getEncollision()
						&& !this.listeMotifsAlphabet.get(i).getEnDeplacement()) {
					this.setNouvellePositionRapprochement(this.listeMotifsAlphabet
							.get(i));
				}
	
			}
		}
		// this.affichage();
	}

	//Outils de dÈboggage
	public void affichage() {
		System.out.println("Collision ?   = " + this.collision());
		
	}

}
