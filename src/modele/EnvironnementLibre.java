package modele;

import java.awt.Color;
import java.util.ArrayList;

import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;
import modele.motifs.mobiles.MotifMobile;
import modele.motifs.mobiles.MotifMobileCercle;
import modele.motifs.mobiles.MotifMobileDCercle;
import modele.motifs.mobiles.MotifMobileRectangle;
import modele.motifs.mobiles.MotifMobileTriangle;

public class EnvironnementLibre extends Environnement{

 public EnvironnementLibre(int width, int height,ArrayList<PtSkeleton> listePtSkel) {
  super(width, height, listePtSkel);
 }

 
 @Override
 public void creerListeMotifs(int niveau, int maxNiveau) {
  
	 
		
		
		
			ArrayList<PtSkeleton> listePtFigures; 
			MotifMobile s;
			
			int x,y,motifWidth,motifHeight;
			
			//Generation des rectangles
			
				listePtFigures = new ArrayList<PtSkeleton>(); 
				motifWidth=(int) (Math.random()*niveau*10+30);
				motifHeight=(int) (Math.random()*niveau*10+30);
				x = (int)(100);
				y=(int) (200);
				alphabet.chargerCouleur((char) (97+Math.random()*7)); 
				listePtFigures.add(new PtSkeleton(x,y));
				listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
				listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
				s = new MotifMobileRectangle(listePtFigures,alphabet.getCouleur(), motifWidth, motifHeight,1);
				this.listeMotifs.add(s);
				
				
			
			//Generation des cercles
			
				listePtFigures = new ArrayList<PtSkeleton>(); 
				motifWidth=(int) (Math.random()*niveau*10+30);
				x = (int)(200);
				y=(int) (200);
				alphabet.chargerCouleur((char) (97+12+Math.random()*7)); 
				listePtFigures.add(new PtSkeleton(x,y));
				listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
				listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
				s = new MotifMobileCercle(listePtFigures, alphabet.getCouleur(), motifWidth,1);
				this.listeMotifs.add(s);
				
				
			
			//Generation des triangles
			
				listePtFigures = new ArrayList<PtSkeleton>(); 
				motifWidth=(int) (Math.random()*niveau*20+20);
				x = (int)(this.width-200);
				y=(int) (200);
				alphabet.chargerCouleur((char) (97+6+Math.random()*7)); 
				listePtFigures.add(new PtSkeleton(x,y));
				listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
				listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
				s = new MotifMobileTriangle(listePtFigures, alphabet.getCouleur(), motifWidth,2);
				this.listeMotifs.add(s);
				
				
			
			//Generation des demi cercles
			
				listePtFigures = new ArrayList<PtSkeleton>(); 
				motifWidth=(int) (Math.random()*niveau*10+35);
				x = (int)(this.width-200);
				y=(int) (100);
				alphabet.chargerCouleur((char) (97+18+Math.random()*7)); 
				listePtFigures.add(new PtSkeleton(x,y));
				listePtFigures.add(this.getUpdatedPt(Skeleton.tete));
				listePtFigures.add(this.getUpdatedPt(Skeleton.hanche_droite));
				s = new MotifMobileDCercle(listePtFigures, alphabet.getCouleur(), motifWidth,2);
				this.listeMotifs.add(s);
				
				
			
			
		
		
	 
 }


 
}