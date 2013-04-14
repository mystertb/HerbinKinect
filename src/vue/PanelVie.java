package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.InteractionJeu;
import modele.Personnage;
import modele.interfaces.ObservateurJeu;
import modele.interfaces.ObservateurPersonnage;

/*
 *  Cette classe est le Panel qui affiche la vie dans une partie
 */

public class PanelVie extends JPanel implements ObservateurPersonnage, ObservateurJeu {

	//Variables de classe
	private final int tailleTube = 590;
	private int vie;
	private boolean jeuTermine;
	private Image matrixWall;
	private Color couleurVie;
	
	//Constructeur
	public PanelVie() {
		this.vie=0;
		this.jeuTermine=true;
		try {
			this.matrixWall=ImageIO.read(new File("matrixWall.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Méthode de peinture du panel
	public void paint(Graphics g) {
		
		
		Graphics2D dessin = (Graphics2D) g;
		
		if(!this.jeuTermine) {
			dessin.drawImage(matrixWall, 0,0, this.getWidth(),this.getHeight(),this);
			//Remplissage de la jauge
			dessin.setColor(this.couleurVie);
			dessin.fillRect(11, tailleTube-((this.tailleTube*this.vie)/100), 78, ((this.tailleTube*this.vie)/100));
			
		} else {
			Rectangle r4 = new Rectangle(0,0, this.getWidth(), this.getHeight());
			dessin.setColor(new Color(25,37,75));
			dessin.fill(r4);
		}
		
		
	}
	
	//procédure qui actualise les données du jeu dans le panel
	@Override
	public void atualiserJeu(int scoreMax, int niveau, boolean jeuTermine) {
		// TODO Auto-generated method stub
		this.jeuTermine=jeuTermine;
	}

	//Procédure qui actualise les données du personnage (utilisateur) dans le panel
	@Override
	public void actualiserPersonnage(int vie, int score, Color couleurVie) {
		// TODO Auto-generated method stub
		this.vie=vie;
		this.couleurVie=couleurVie;
	}



	
	
}
