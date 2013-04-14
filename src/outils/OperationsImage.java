package outils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class OperationsImage {
	
	private ArrayList<BufferedImage> listeVariantes;
	private Alphabet alphabet;
	private BufferedImage src;
	
	public OperationsImage() {

		BufferedImage splash=null;
		try {
			splash = ImageIO.read(new File("waterSplash.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listeVariantes = new ArrayList<BufferedImage>();
		this.alphabet = new Alphabet();
		this.src=splash;
		creerVariantes();
	}
	
	public BufferedImage getVariante(Color couleur) {
		
		for(int i=0;i<this.listeVariantes.size();i++) {
			
			if(couleur.getRGB()==listeVariantes.get(i).getRGB(src.getWidth()/2, src.getHeight()/2)) {
				return listeVariantes.get(i);
			}
			
		}
		
		return src;
		
	}
	
	public void creerVariantes() {
		
		BufferedImage variante;
		
		for(int i=0;i<26;i++) {
			
			alphabet.chargerCouleur((char) (97+i));
			variante = setColor(src,alphabet.getCouleur());
			this.listeVariantes.add(variante);
			
		}
		
	}
	
	public  BufferedImage setColor(BufferedImage imgSrc, Color couleur) {
		
		BufferedImage colorized = new BufferedImage(imgSrc.getWidth(),imgSrc.getHeight(),imgSrc.TYPE_4BYTE_ABGR);
		makeTransparent(colorized);
		for(int w=0;w<imgSrc.getWidth();w++) {
			
			for(int h=0;h<imgSrc.getHeight();h++) {
				
				if(!isTransparent(imgSrc.getRGB(w, h))) {
					
					colorized.setRGB(w, h, couleur.getRGB());
					
					
				}
				
			}
			
		}
		
	
		return colorized;
	}
	
	public  void makeTransparent(BufferedImage image) {
		
		for (int y = 0; y < image.getHeight(); ++y) {
		    for (int x = 0; x < image.getWidth(); ++x) {
		         int argb = image.getRGB(x, y);
		         if ((argb & 0x00FFFFFF) == 0x00D67FFF)
		         {
		              image.setRGB(x, y, 0);
		         }
		    }
		}
		
	}
	
	public static boolean isTransparent( int pixel) {
		
		  if( (pixel>>24) == 0x00 ) {
		      return true;
		  } 
		  
		  return false;
		}

}
