package outils;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.*;
   
/**
 * Classe qui g�re le son
 * 
 */
public class Sound {
   //Variables de classe
   private ArrayList<Clip> listeClips;
   
   //Constructeur
   public Sound() {
	   
	   this.listeClips = new ArrayList<Clip>();
	   this.listeClips.add(getClip("Varia_Menu.wav"));
	   this.listeClips.add(getClip("Varia_1.wav"));
	   this.listeClips.add(getClip("Varia_Boss.wav"));
	   this.listeClips.add(getClip("splash.wav"));
   }
   
   //M�thode qui permet d'extraire le Clip qu'un fichier audio en prenant comme parametre le nom du fichier
   public Clip getClip(String soundFileName) {
	   
	   Clip clip=null;
	   
	   try {
	         // r�cup�ration de l'url du fichier par son nom et extraction des donn�es du son dans la variable clip
	         URL url = this.getClass().getClassLoader().getResource(soundFileName);
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	         clip = AudioSystem.getClip();
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   
	   return clip;
	   
   }
   
   //Stoppe l'execution de toutes les musiques lanc�es dans le programme
   public void stopAll() {
	   
	   for(int i=0;i<this.listeClips.size();i++) {
		   if(this.listeClips.get(i).isRunning()) {
		   this.listeClips.get(i).stop();
		   }
	   }
	   
   }

   //Lib�re les musiques de la liste(les supprime). Utile pour l'execution simultann�e de plusieurs sons splashs
   public void free() {
	   
	   for(int i=3;i<this.listeClips.size();i++) {
		   
		   if(!this.listeClips.get(i).isRunning()) {
			   this.listeClips.get(i).close();
		   }
		   
	   }
	   
   }
   
   //Joue une musique de splash (son de destruction d'un motif)
   public void playSplash() {
	   
	   this.free();
	   Clip splash = this.getClip("splash.wav");
	   this.listeClips.add(splash);
	   splash.start();
	   
   }
   
   //Joue la musique de d�but de programme
   public void playStart() {
	   
	   stopAll();
	   this.listeClips.get(0).start();
	   
   }
   
   //Joue la musique de l'apparition de la pi�ce
	public void playHome() {
	   
	   stopAll();
	   this.listeClips.get(1).start();
	   
   }
   
	//retourne vrai si aucun son n'est jou� dans le programme
	public boolean aucunSon() {
		
		boolean aucunSon=true;
		
		for(int i=0;i<this.listeClips.size();i++) {
			
			aucunSon = aucunSon&&this.listeClips.get(i).isRunning();
			
		}
		
		return aucunSon;
		
	}
	
	//lance la musique de partie
	public void playGame() {
		
		stopAll();
		this.listeClips.get(2).start();
		
	}
	
	//retourne vrai si le son pr�sent � l'indice i de la liste de son est actuellement jou�.
	public boolean isRunning(int i) {
		
		return this.listeClips.get(i).isRunning();
		
	}
	
   
}