package modele;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import outils.Alphabet;

import modele.deplacements.Mur;
import modele.deplacements.TypeMur;
import modele.motifs.fixes.Motif;
import modele.motifs.fixes.MotifFixeCercle;
import modele.motifs.fixes.MotifFixeRectangle;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;
import modele.motifs.mobiles.MotifMobile;
import modele.motifs.mobiles.MotifMobileCercle;
import modele.motifs.mobiles.MotifMobileDCercle;
import modele.motifs.mobiles.MotifMobileRectangle;
import modele.motifs.mobiles.MotifMobileTriangle;

public abstract class Environnement {

	
	protected ArrayList<MotifMobile> listeMotifs;
	protected ArrayList<Mur> listeMurs;
	protected int width,height;
	protected ArrayList<PtSkeleton> listePtSkel;
	protected Alphabet alphabet;
	
	public Environnement(int width, int height,ArrayList<PtSkeleton> listePtSkel) {
		this.listeMotifs = new ArrayList<MotifMobile>();
		this.listeMurs = new ArrayList<Mur>();
		this.listePtSkel = listePtSkel;
		this.width=width;
		this.height=height;
		this.alphabet=new Alphabet();
	}
	
	
	
    public PtSkeleton getUpdatedPt (Skeleton s) {
    	
    	for(int i=0;i<listePtSkel.size();i++) {
    		
    		if(listePtSkel.get(i).getPartieDuCorps().equals(s)) {
    			return listePtSkel.get(i);
    		}
    		
    	}
    	
    	return new PtSkeleton(Skeleton.aucun);
    }
	
	public abstract void creerListeMotifs(int niveau, int maxNiveau);
	
	public void creerMurs() {
		
		listeMurs = new ArrayList<>();
	       int epaisseurMur = 10;
	       PtSkeleton ptMurGauche = new PtSkeleton(0,0);
	       PtSkeleton ptMurDroit = new PtSkeleton(this.width-epaisseurMur,0);
	       PtSkeleton ptPlafond = new PtSkeleton(0,0);
	       PtSkeleton ptSol = new PtSkeleton(0,this.height-epaisseurMur);
	       ArrayList<PtSkeleton> listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptMurGauche);
	       Mur murGauche = new Mur(listePt,Color.black,epaisseurMur,this.height,TypeMur.murGauche);
	       listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptMurDroit);
	       Mur murDroit = new Mur(listePt,Color.black,this.width-epaisseurMur,this.height,TypeMur.murDroit);
	       listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptPlafond);
	       Mur plafond = new Mur(listePt,Color.black,this.width,epaisseurMur,TypeMur.plafond);
	       listePt = new ArrayList<PtSkeleton>();
	       listePt.add(ptSol);
	       Mur sol = new Mur(listePt,Color.black,this.width,epaisseurMur,TypeMur.murDroit);
	      
	       this.listeMurs.add(murGauche);
	       this.listeMurs.add(murDroit);
	       this.listeMurs.add(plafond);
	       this.listeMurs.add(sol);
		
	}
	
	public ArrayList<MotifMobile> getListeMotifs() {
		return this.listeMotifs;
	}
	
	public ArrayList<Mur> getListeMurs() {
		return this.listeMurs;
	}
	
	
}
