/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author ytiab
 * Classe qui permet d'attribuer des couleurs aux motifs en fonction du texte entr� grace � l'algorithme d'herbin
 */
public class Alphabet {

    private String mot;
    private Color couleur;
    private double sub;
    private int width, height, nbFormes, noteMusic;
    private char char0, colorToChar, charInverse;
    //private ArrayList<EmplacementForme> listeEmplacements;
    private boolean rondCentraleColorie,demiCercleHautColorie,demiCercleBasColorie;  
    //private int[][] listeSlot;

    //Constructeur avec parametres
    public Alphabet(String mot, int width, int height) {

        this.mot = mot;
        this.width = width;
        this.height = height;
        this.char0 = mot.charAt(0);
        //this.listeEmplacements = new ArrayList<EmplacementForme>();
        rondCentraleColorie=false;
        demiCercleHautColorie=false;      
        demiCercleBasColorie=false;  
        this.sub = 0;

    }
    
    //Constructeur sans parametres
    public Alphabet() {
        
    }

    //Calcule la couleur la plus proche du mot entr� en parametre
    public void couleurLaPlusProche(String s) {

        char c = 0;
        Color c0 = new Color(0,0,0); 
        c0=couleur;
        int rMax = 0, vMax = 0, bMax = 0, sumMax = 256 * 3, j;
        

        if (s.equals("")) {
            j = 26;
            c = 'a';
        } else {
            j = s.length();
        }

        for (int i = 0; i < j; i++) {

            if (!s.equals("")) {
                c = s.charAt(i);
            }
            chargerCouleur(c);
            if (Math.abs(c0.getRed() - couleur.getRed()) + Math.abs(c0.getGreen() - couleur.getGreen()) + Math.abs(c0.getBlue() - couleur.getBlue()) < sumMax) {
                if (!s.equals("")) {
                    charInverse = colorToChar;
                }
                sumMax = Math.abs(c0.getRed() - couleur.getRed()) + Math.abs(c0.getGreen() - couleur.getGreen()) + Math.abs(c0.getBlue() - couleur.getBlue());
                rMax = couleur.getRed();
                vMax = couleur.getGreen();
                bMax = couleur.getBlue();

            }
            c++;
        }

        couleur = new Color(rMax, vMax, bMax);

    }

    //Applique un filtre n�gatif sur la couleur courante
    public void negatif() {

        couleur = new Color(255 - couleur.getRed(), 255 - couleur.getGreen(), 255 - couleur.getBlue());

    }
    
    //attribue une couleur donn�e en parametre � la couleur courante
    public void setCouleur(Color couleur) {
        
        this.couleur=couleur;
        
    }
    
    //retourne la couleur courante
    public Color getCouleur() {
        
        return this.couleur;
        
    }

    //Attribue un mot au mot courant
    public void setMot(String mot) {

        this.mot = mot;

    }

    //Attribue une nouvelle couleur � la couleur courante en fonction d'un caract�re grace � l'algorithme d'herbin
    public void chargerCouleur(char c) {

        int r = 0, v = 0, b = 0;

        switch (c) {
           
           default:
           
                r = (int) (Math.random()*255);
                v = (int) (Math.random()*255);
                b = (int) (Math.random()*255);
                colorToChar = c;
                nbFormes = (int) (Math.random()*5);
           
           break;
           
        }

        noteMusic = 53 - ((((int) (colorToChar)) - 97) * 2);
        noteMusic = noteMusic < 10 ? 10:noteMusic;
        couleur = new Color(r, v, b);

    }

}
