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
 * Classe qui permet d'attribuer des couleurs aux motifs en fonction du texte entré grace à l'algorithme d'herbin
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

    //Calcule la couleur la plus proche du mot entré en parametre
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

    //Applique un filtre négatif sur la couleur courante
    public void negatif() {

        couleur = new Color(255 - couleur.getRed(), 255 - couleur.getGreen(), 255 - couleur.getBlue());

    }
    
    //attribue une couleur donnée en parametre à la couleur courante
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

    //Attribue une nouvelle couleur à la couleur courante en fonction d'un caractère grace à l'algorithme d'herbin
    public void chargerCouleur(char c) {

        int r = 0, v = 0, b = 0;

        switch (c) {
            case 'a':
                r = 218;
                v = 45;
                b = 184;
                colorToChar = 'a';
                nbFormes = 4;
                break;
            case 'b':
                r = 131;
                v = 18;
                b = 60;
                colorToChar = 'b';
                nbFormes = 2;
                break;
            case 'c':
                r = 168;
                v = 29;
                b = 33;
                colorToChar = 'c';
                nbFormes = 2;
                break;
            case 'd':
                r = 209;
                v = 30;
                b = 35;
                colorToChar = 'd';
                nbFormes = 1;
                break;
            case 'e':
                r = 250;
                v = 12;
                b = 18;
                colorToChar = 'e';
                nbFormes = 1;
                break;
            case 'f':
                r = 251;
                v = 41;
                b = 10;
                colorToChar = 'f';
                nbFormes = 2;
                break;
            case 'g':
                r = 178;
                v = 38;
                b = 24;
                colorToChar = 'g';
                nbFormes = 2;
                break;
            case 'h':
                r = 247;
                v = 121;
                b = 38;
                colorToChar = 'h';
                nbFormes = 2;
                break;
            case 'i':
                r = 246;
                v = 78;
                b = 10;
                colorToChar = 'i';
                nbFormes = 2;
                break;
            case 'j':
                r = 246;
                v = 117;
                b = 10;
                colorToChar = 'j';
                nbFormes = 2;
                break;
            case 'k':
                r = 246;
                v = 146;
                b = 10;
                colorToChar = 'k';
                nbFormes = 2;
                break;
            case 'l':
                r = 246;
                v = 238;
                b = 10;
                colorToChar = 'l';
                nbFormes = 1;
                break;
            case 'm':
                r = 210;
                v = 205;
                b = 74;
                colorToChar = 'm';
                nbFormes = 1;
                break;
            case 'n':
                r = 251;
                v = 251;
                b = 251;
                colorToChar = 'n';
                nbFormes = 4;
                break;
            case 'o':
                r = 45;
                v = 46;
                b = 10;
                colorToChar = 'o';
                nbFormes = 2;
                break;
            case 'p':
                r = 115;
                v = 168;
                b = 77;
                colorToChar = 'p';
                nbFormes = 2;
                break;
            case 'q':
                r = 15;
                v = 134;
                b = 94;
                colorToChar = 'q';
                nbFormes = 2;
                break;
            case 'r':
                r = 15;
                v = 130;
                b = 164;
                colorToChar = 'r';
                nbFormes = 2;
                break;
            case 's':
                r = 20;
                v = 77;
                b = 93;
                colorToChar = 's';
                nbFormes = 2;
                break;
            case 't':
                r = 20;
                v = 50;
                b = 90;
                colorToChar = 't';
                nbFormes = 2;
                break;
            case 'u':
                r = 50;
                v = 70;
                b = 150;
                colorToChar = 'u';
                nbFormes = 1;
                break;
            case 'v':
                r = 0;
                v = 0;
                b = 0;
                colorToChar = 'v';
                nbFormes = 4;
                break;
            case 'w':
                r = 35;
                v = 50;
                b = 105;
                colorToChar = 'w';
                nbFormes = 2;
                break;
            case 'x':
                r = 130;
                v = 44;
                b = 140;
                colorToChar = 'x';
                nbFormes = 2;
                break;
            case 'y':
                r = 100;
                v = 15;
                b = 143;
                colorToChar = 'y';
                nbFormes = 1;
                break;
            case 'z':
                r = 45;
                v = 17;
                b = 81;
                colorToChar = 'z';
                nbFormes = 3;
                break;
        }

        noteMusic = 53 - ((((int) (colorToChar)) - 97) * 2);
        noteMusic = noteMusic < 10 ? 10:noteMusic;
        couleur = new Color(r, v, b);

    }

}
