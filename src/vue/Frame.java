/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import outils.Alphabet;

import modele.Personnage;
import modele.deplacements.Interaction;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;
import modele.motifs.fixes.SkeletonMaker;

/**
 *
 * @author ytiab
 * Classe qui gère la fenêtre et sa contenance
 */


public class Frame extends JFrame  {

	//Variables de classe
    protected PanelKinectArt panelKinectArt;
    protected Alphabet alphabet ;
    protected SkeletonMaker skel;
    protected JTextField mot;
    protected int nbGeneration;
    protected boolean repeindreTout;
    protected Interaction deplacement;
    
    //Constructeur qui initialise tous les composants de la fenetre
    public Frame(PanelKinectArt panelKinect) throws MidiUnavailableException  {
        
		super("Art 3D");
		this.panelKinectArt = panelKinect;
        JPanel panelNiveau = new JPanel();
        panelNiveau.setBackground(new Color(61,89,172));
        
        JPanel pan = new JPanel();
        mot = new JTextField(20);
        mot.addActionListener(new EcouteurText());
        JButton generer = new JButton("Peindre");
        generer.addActionListener(new EcouteurGenerer());
        
        JPanel menu = new JPanel();
        menu.add(mot);
        menu.add(generer);
        
        
        PanelScore score = new PanelScore(panelNiveau);
        score.setPreferredSize(new Dimension(100, panelKinectArt.getHeight()));
        score.setSize(new Dimension(100, panelKinectArt.getHeight()));
        score.setBackground(Color.WHITE);
        
        panelKinect.addObservateurJeu(score);
        panelKinect.addObservateurPersonnage(score);
                        
        PanelVie vie = new PanelVie();
        vie.setBackground(new Color(240, 0, 0));
        vie.setPreferredSize(new Dimension(100, panelKinectArt.getHeight()));
        
        panelKinect.addObservateurJeu(vie);
        panelKinect.addObservateurPersonnage(vie);
        
        pan.setLayout(new BorderLayout());
        
        pan.setBackground(Color.gray);
        pan.add(menu, BorderLayout.NORTH);
        pan.add(panelKinect, BorderLayout.CENTER);
        pan.add(score, BorderLayout.EAST);
        pan.add(vie, BorderLayout.WEST);
        pan.add(panelNiveau, BorderLayout.SOUTH);
        
        this.getContentPane().add(pan);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        


    }
    
    //retourne l'instance d'interaction de la fenetre
    public Interaction getDeplacement() {
    	return this.deplacement;
    }
    
    //classe qui gère les actions à effectuer lorsque l'on clique sur le bouton peindre
    private class EcouteurGenerer implements ActionListener  {
                    
        public void actionPerformed(ActionEvent e) {
                
        String texte = mot.getText();
        panelKinectArt.setText(texte);
        
        }
            
    }
    
  
    private class EcouteurText implements ActionListener  {
                    
        public void actionPerformed(ActionEvent e) {
                
                
        }
    }
    
}
