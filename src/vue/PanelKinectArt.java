/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modele.ExtraChecker;
import modele.InstanceKinect;
import modele.InteractionJeu;
import modele.Personnage;
import modele.deplacements.Interaction;
import modele.interfaces.ObservateurJeu;
import modele.interfaces.ObservateurPersonnage;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.MotifFixeImage;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.SkeletonMaker;
import modele.motifs.mobiles.MotifMobile;
import outils.Sound;

/**
 * 
 * @author ytiab
 */
public class PanelKinectArt extends JPanel {

	private InstanceKinect instanceKinect;
	
	public PanelKinectArt(InstanceKinect instanceKinect) {
		this.setPreferredSize(new Dimension(instanceKinect.getWidth(),instanceKinect.getHeight()));
		this.instanceKinect=instanceKinect;

	}
	
	public void setText(String mot){
		this.instanceKinect.setText(mot);
	}
	
	public void addObservateurJeu(ObservateurJeu obs) {

		this.instanceKinect.addObservateurJeu(obs);

	}

	public void addObservateurPersonnage(ObservateurPersonnage obs) {

		this.instanceKinect.addObservateurPersonnage(obs);

	}

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		Motif motif;

		// motif = listeFiguresDecor.get(listeFiguresDecor.size() - 1);
		//
		// motif.setColor(
		// (motif.getCouleur().getRed() + (int) (Math.random() * 5)),
		// (motif.getCouleur().getGreen() + (int) (Math.random() * 5)),
		// (motif.getCouleur().getBlue() + (int) (Math.random() * 5)));
		//
		// g2d.setColor(motif.getCouleur());
		// g2d.fill(motif);
		// }

		Image img = null;
		MotifFixeImage motifImage;
		for (int i = 0; i < this.instanceKinect.getListeMotifImage().size(); i++) {
			motifImage = this.instanceKinect.getMotifImage(i);
			try {img = motifImage.getImage();
			if(img!=null) {
			g2d.drawImage(img, (int) motifImage.getPoint(0).x,
					(int) motifImage.getPoint(0).y, motifImage.getLargeur(),
					motifImage.getHauteur(), this);
			}
			} catch (Exception e) {
				System.out.println(motifImage.getName()+" n'éxiste pas");
			}
		}
		
		MotifMobile motifMobile;
		
		for (int i = 0; i < this.instanceKinect.getEnvironnementSize(); i++) {
			motifMobile = this.instanceKinect.getEnvironnement(i);
			motifMobile.actualiserSurface();
			if(!motifMobile.getRemove()) { 
				g2d.setColor(motifMobile.getCouleur());
				g2d.fill(motifMobile);
				} else {
					if(motifMobile.increment()<30) {
					Rectangle imgRect = motifMobile.getRectangle();
					g2d.drawImage(instanceKinect.getVarianteSplash(motifMobile.getCouleur()), imgRect.x,imgRect.y, imgRect.width, imgRect.height, this);
					} else {
						instanceKinect.removeEnv(motifMobile);
					}
				}
			
		}

		for (int i = 0; i < this.instanceKinect.getSizeCorps(); i++) {
			motif = instanceKinect.getPartieCorps(i);
			motif.actualiserSurface();
				g2d.setColor(motif.getCouleur());
				g2d.fill(motif);

		}
	}
}