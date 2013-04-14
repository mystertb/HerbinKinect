package modele;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import modele.motifs.fixes.Motif;
import modele.motifs.fixes.MotifFixeImage;
import modele.motifs.fixes.MotifFixePolygone;
import modele.motifs.fixes.Pt;
import modele.motifs.fixes.PtSkeleton;

public class Decor {

	private ArrayList<Motif> listeFiguresDecor;
	private ArrayList<MotifFixeImage> listeCompleteMotifsImages;
	private ArrayList<MotifFixeImage> listeValideMotifsImages;
	int width, height;

	public Decor(int width, int height) {

		setListeFiguresDecor(new ArrayList<Motif>());
		listeCompleteMotifsImages = new ArrayList<MotifFixeImage>();
		listeValideMotifsImages  = new ArrayList<MotifFixeImage>();
		this.height = height;
		this.width = width;
		// creerDecor();

	}

	public void creerListeImages() {
		
		ArrayList<PtSkeleton> listePoints;
		listePoints = new ArrayList<PtSkeleton>();
		listePoints.add(new PtSkeleton(0,0));
		MotifFixeImage imgMatrix = new MotifFixeImage(listePoints,Color.black,width,height,"matrix2.jpg");
		this.listeCompleteMotifsImages.add(imgMatrix);
		MotifFixeImage imgArt = new MotifFixeImage(listePoints,Color.black,width,height,"art.jpg");
		this.listeCompleteMotifsImages.add(imgArt);
		MotifFixeImage imgWin = new MotifFixeImage(listePoints,Color.black,width,height,"winner.png");
		this.listeCompleteMotifsImages.add(imgWin);
		listePoints = new ArrayList<PtSkeleton>();
		listePoints.add(new PtSkeleton(-10, (int) (0.5 * height - 100)));
		MotifFixeImage imgTv = new MotifFixeImage(listePoints,Color.black,140, 150,"tvlcd.gif");
		this.listeCompleteMotifsImages.add(imgTv);
		
	}

	public ArrayList<MotifFixeImage> getListeMotifImage() {
		
		return this.listeValideMotifsImages;
		
	}
	
	public void reinit() {
		for(int i=0;i<this.listeValideMotifsImages.size();i++) {
		this.listeValideMotifsImages.remove(i);
		}
	}
	
	public void validate(String name) {
		
		this.listeValideMotifsImages.add(this.getMotifImage(name));
		
	}
	
	public MotifFixeImage getMotifImage(String name) {

		for (int i = 0; i < this.listeCompleteMotifsImages.size(); i++) {

			if (this.listeCompleteMotifsImages.get(i).getName().equals(name)) {
				return this.listeCompleteMotifsImages.get(i);
			}

		}

		return null;
	}

	public void creerDecor() {

		Motif motif;
		ArrayList<PtSkeleton> listePoints = new ArrayList<PtSkeleton>();
		Pt milieu_haut_gauche, milieu_haut_droit, milieu_bas_gauche, milieu_bas_droit, haut_gauche, haut_droit, bas_gauche, bas_droit;
		Color murs, sol, plafond, ombre;
		murs = new Color(185, 122, 87);
		plafond = new Color(239, 228, 176);
		sol = new Color(255, 127, 39);
		ombre = new Color(0, 0, 0);
		haut_gauche = new Pt(0, 0);
		haut_droit = new Pt(width, 0);
		bas_gauche = new Pt(0, height);
		bas_droit = new Pt(width, height);
		milieu_haut_gauche = new Pt((0.15 * width), (0.25 * height));
		milieu_haut_droit = new Pt((0.85 * width), (0.25 * height));
		milieu_bas_droit = new Pt((0.85 * width), (0.75 * height));
		milieu_bas_gauche = new Pt((0.15 * width), (0.75 * height));

		// sol
		listePoints.add(new PtSkeleton(bas_gauche));
		listePoints.add(new PtSkeleton(milieu_bas_gauche));
		listePoints.add(new PtSkeleton(milieu_bas_droit));
		listePoints.add(new PtSkeleton(bas_droit));

		motif = new MotifFixePolygone(listePoints, sol);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// plafond
		listePoints.add(new PtSkeleton(haut_gauche));
		listePoints.add(new PtSkeleton(milieu_haut_gauche));
		listePoints.add(new PtSkeleton(milieu_haut_droit));
		listePoints.add(new PtSkeleton(haut_droit));

		motif = new MotifFixePolygone(listePoints, plafond);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// mur face
		listePoints.add(new PtSkeleton(milieu_haut_gauche));
		listePoints.add(new PtSkeleton(milieu_haut_droit));
		listePoints.add(new PtSkeleton(milieu_bas_droit));
		listePoints.add(new PtSkeleton(milieu_bas_gauche));

		motif = new MotifFixePolygone(listePoints, murs);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// mur gauche
		listePoints.add(new PtSkeleton(haut_gauche));
		listePoints.add(new PtSkeleton(milieu_haut_gauche));
		listePoints.add(new PtSkeleton(milieu_bas_gauche));
		listePoints.add(new PtSkeleton(bas_gauche));

		motif = new MotifFixePolygone(listePoints, murs);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// mur droit
		listePoints.add(new PtSkeleton(haut_droit));
		listePoints.add(new PtSkeleton(milieu_haut_droit));
		listePoints.add(new PtSkeleton(milieu_bas_droit));
		listePoints.add(new PtSkeleton(bas_droit));

		motif = new MotifFixePolygone(listePoints, murs);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		int ecart = 2;

		// ombre gauche

		listePoints.add(new PtSkeleton(milieu_haut_gauche.x - ecart,
				milieu_haut_gauche.y));
		listePoints.add(new PtSkeleton(milieu_haut_gauche.x + ecart,
				milieu_haut_gauche.y));
		listePoints.add(new PtSkeleton(milieu_bas_gauche.x + ecart,
				milieu_bas_gauche.y));
		listePoints.add(new PtSkeleton(milieu_bas_gauche.x - ecart,
				milieu_bas_gauche.y));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// ombre d'roite

		listePoints.add(new PtSkeleton(milieu_haut_droit.x - ecart,
				milieu_haut_droit.y));
		listePoints.add(new PtSkeleton(milieu_haut_droit.x + ecart,
				milieu_haut_droit.y));
		listePoints.add(new PtSkeleton(milieu_bas_droit.x + ecart,
				milieu_bas_droit.y));
		listePoints.add(new PtSkeleton(milieu_bas_droit.x - ecart,
				milieu_bas_droit.y));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// ombre droite

		listePoints.add(new PtSkeleton(haut_gauche.x - ecart, haut_gauche.y));
		listePoints.add(new PtSkeleton(haut_gauche.x + ecart, haut_gauche.y));
		listePoints.add(new PtSkeleton(milieu_haut_gauche.x + ecart,
				milieu_haut_gauche.y));
		listePoints.add(new PtSkeleton(milieu_haut_gauche.x - ecart,
				milieu_haut_gauche.y));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// ombre droite

		listePoints.add(new PtSkeleton(haut_droit.x - ecart, haut_droit.y));
		listePoints.add(new PtSkeleton(haut_droit.x + ecart, haut_droit.y));
		listePoints.add(new PtSkeleton(milieu_haut_droit.x + ecart,
				milieu_haut_droit.y));
		listePoints.add(new PtSkeleton(milieu_haut_droit.x - ecart,
				milieu_haut_droit.y));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// ombre droite

		listePoints.add(new PtSkeleton(bas_gauche.x - ecart, bas_gauche.y));
		listePoints.add(new PtSkeleton(bas_gauche.x + ecart, bas_gauche.y));
		listePoints.add(new PtSkeleton(milieu_bas_gauche.x + ecart,
				milieu_bas_gauche.y));
		listePoints.add(new PtSkeleton(milieu_bas_gauche.x - ecart,
				milieu_bas_gauche.y));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// ombre droite

		listePoints.add(new PtSkeleton(bas_droit.x - ecart, bas_droit.y));
		listePoints.add(new PtSkeleton(bas_droit.x + ecart, bas_droit.y));
		listePoints.add(new PtSkeleton(milieu_bas_droit.x + ecart,
				milieu_bas_droit.y));
		listePoints.add(new PtSkeleton(milieu_bas_droit.x - ecart,
				milieu_bas_droit.y));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// ombre droite

		listePoints.add(new PtSkeleton(milieu_haut_gauche.x,
				milieu_haut_gauche.y - ecart));
		listePoints.add(new PtSkeleton(milieu_haut_droit.x, milieu_haut_droit.y
				- ecart));
		listePoints.add(new PtSkeleton(milieu_haut_droit.x, milieu_haut_droit.y
				+ ecart));
		listePoints.add(new PtSkeleton(milieu_haut_gauche.x,
				milieu_haut_gauche.y + ecart));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// ombre droite

		listePoints.add(new PtSkeleton(milieu_bas_gauche.x, milieu_bas_gauche.y
				- ecart));
		listePoints.add(new PtSkeleton(milieu_bas_droit.x, milieu_bas_droit.y
				- ecart));
		listePoints.add(new PtSkeleton(milieu_bas_droit.x, milieu_bas_droit.y
				+ ecart));
		listePoints.add(new PtSkeleton(milieu_bas_gauche.x, milieu_bas_gauche.y
				+ ecart));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

		listePoints = new ArrayList<PtSkeleton>();

		// TV

		listePoints.add(new PtSkeleton(18, 216));
		listePoints.add(new PtSkeleton(110, 254));
		listePoints.add(new PtSkeleton(110, 320));
		listePoints.add(new PtSkeleton(18, 317));

		motif = new MotifFixePolygone(listePoints, ombre);
		this.listeFiguresDecor.add(motif);

	}

	public ArrayList<Motif> getListeFiguresDecor() {
		return listeFiguresDecor;
	}

	public void setListeFiguresDecor(ArrayList<Motif> listeFiguresDecor) {
		this.listeFiguresDecor = listeFiguresDecor;
	}

}
