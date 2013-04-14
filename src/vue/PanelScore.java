package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.InteractionJeu;
import modele.Personnage;
import modele.interfaces.ObservateurJeu;
import modele.interfaces.ObservateurPersonnage;

/*
 *  Cette classe est le Panel qui affiche le score dans une partie
 */

public class PanelScore extends JPanel implements ObservateurJeu, ObservateurPersonnage {

		//Variables de classe
		private final int tailleTube = 590;
		private int score, scoreMax, vie;
		private JPanel panelNiveau;
		private JLabel labelNiveau;
		private Image matrixWall;
		private boolean jeuTermine;
		private int niveau;
		
		//Constructeur
		public PanelScore(JPanel panelNiveau) {
			
			this.jeuTermine=true;
			this.niveau=0;
			this.score=0;
			this.scoreMax=0;
			
			JLabel labelNiveau = new JLabel("Niveau 1");
			this.labelNiveau=labelNiveau;
	        labelNiveau.setFont(new Font("sansserif", Font.BOLD, 52));
	        labelNiveau.setForeground(new Color(0,255,0));
	        panelNiveau.add(labelNiveau);
			try {
				 matrixWall = ImageIO.read(new File("matrixWall.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Méthode de peinture du panel
		@Override
		public void paint(Graphics g) {
			
			
			Graphics2D dessin = (Graphics2D) g;
			
			if(!this.jeuTermine) {
				this.labelNiveau.setText("Vie: "+this.vie+"      Niveau "+this.niveau+"      Score:"+this.score);
				dessin.drawImage(matrixWall, 0,0, this.getWidth(),this.getHeight(),this);
				//Remplissage de la jauge
				Color couleurScore;
				if(score>0.66*scoreMax) {
					couleurScore = new Color(242,249,0);
				} else if (score>0.33*scoreMax) {
					couleurScore = new Color(239,210,10);
				} else {
					couleurScore = new Color(209,141,14);
				}
				dessin.setColor(couleurScore);
				dessin.fillRect(11, tailleTube-((this.tailleTube*this.score)/this.scoreMax), 78, ((this.tailleTube*this.score)/scoreMax));
			} else {
				this.labelNiveau.setText("Interaction Libre");
				Rectangle r4 = new Rectangle(0,0, this.getWidth(), this.getHeight());
				
				dessin.setColor(new Color(29,42,86));
				dessin.fill(r4);
			}
			
			
		}
		
		//procédure qui actualise les données du jeu dans le panel
		@Override
		public void actualiserPersonnage(int vie, int score, Color couleurVie) {
			// TODO Auto-generated method stub
			this.vie=vie;
			this.score=score;
			this.repaint();
			
		}

		//Procédure qui actualise les données du personnage (utilisateur) dans le panel
		@Override
		public void atualiserJeu(int scoreMax, int niveau, boolean jeuTermine) {
			// TODO Auto-generated method stub
			this.scoreMax=scoreMax;
			this.niveau=niveau;
			this.jeuTermine=jeuTermine;
			this.repaint();
			
		}


	
}