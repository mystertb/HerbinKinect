package modele.motifs.fixes;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/*
 * Motif Image fixe dans la toile 
 */

public class MotifFixeImage extends MotifFixeRectangle {

	//Variables
	private Image image;
	private String nom;

	//Constructeur
	public MotifFixeImage(ArrayList<PtSkeleton> listePoints, Color couleur,
			int largeur, int hauteur, String nomSource) {
		super(listePoints, couleur, largeur, hauteur);

		try {
			this.setImage(ImageIO.read(new File(nomSource)));
			this.nom=nomSource;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	//retourne l'image du motif
	public Image getImage() {
		return image;
	}

	//attribue une nouvelle image au motif
	public void setImage(Image image) {
		this.image = image;
	}
	
	 public String getName() {
		  
		  return this.nom;
		  
	 }
	
	

}
