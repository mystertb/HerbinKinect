package tracker;

import java.awt.Point;
import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;


import modele.InstanceKinect;
import modele.motifs.fixes.Pt;
import modele.motifs.fixes.PtSkeleton;
import modele.motifs.fixes.Skeleton;

import processing.core.PApplet;
import processing.core.PVector;
import vue.Frame;
import vue.PanelKinectArt;
import SimpleOpenNI.*;

 public class SkeletonTracker extends PApplet {
	 
	SimpleOpenNI  context;
	Frame l;
	PtSkeleton tete,cou,epaule_gauche,epaule_droite,coude_gauche,coude_droit,main_gauche,main_droite,hanche_gauche,hanche_droite,genou_gauche,genou_droit,pied_gauche,pied_droit;
	PanelKinectArt panelKinect;
	InstanceKinect instanceKinect;
	
	public void setup()
	{

		ArrayList<PtSkeleton> listeSkel = new ArrayList<PtSkeleton>();
	 
	  tete = new PtSkeleton(Skeleton.tete);
	  listeSkel.add(tete);
	  System.out.println("\n"+tete);
	  cou  = new PtSkeleton(Skeleton.cou);
	  listeSkel.add(cou);
	  epaule_gauche = new PtSkeleton(Skeleton.epaule_gauche);
	  listeSkel.add(epaule_gauche);
	  epaule_droite = new PtSkeleton(Skeleton.epaule_droite);
	  listeSkel.add(epaule_droite);
	  coude_gauche= new PtSkeleton(Skeleton.coude_gauche);
	  listeSkel.add(coude_gauche);
	  coude_droit= new PtSkeleton(Skeleton.coude_droit);
	  listeSkel.add(coude_droit);
	  main_droite= new PtSkeleton(Skeleton.main_droite);
	  listeSkel.add(main_droite);
	  main_gauche= new PtSkeleton(Skeleton.main_gauche);
	  listeSkel.add(main_gauche);
	  hanche_gauche= new PtSkeleton(Skeleton.hanche_gauche);
	  listeSkel.add(hanche_gauche);
	  hanche_droite= new PtSkeleton(Skeleton.hanche_droite);
	  listeSkel.add(hanche_droite);
	  genou_gauche= new PtSkeleton(Skeleton.genou_gauche);
	  listeSkel.add(genou_gauche);
	  genou_droit= new PtSkeleton(Skeleton.genou_droit);
	  listeSkel.add(genou_droit);
	  pied_gauche= new PtSkeleton(Skeleton.pied_gauche);
	  listeSkel.add(pied_gauche);
	  pied_droit= new PtSkeleton(Skeleton.pied_droit);
	  listeSkel.add(pied_droit);
	  
	  
	  context = new SimpleOpenNI(this);
	 
	  // enable depthMap generation 
	  context.enableDepth();


		  	this.instanceKinect = new InstanceKinect(800,600, listeSkel);
		  	this.panelKinect = new PanelKinectArt(instanceKinect);
			try {
				l = new Frame(this.panelKinect);
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	  // enable skeleton generation for all joints
	  context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);
	 
	}
	 
	public void draw()
	{
		context.update();
		//image(context.depthImage(),0,0); 
		
		int i;
		
		for(i=0;i<=10;i++) {
		    // check if the skeleton is being tracked
		    if(context.isTrackingSkeleton(i))
		    {
		      //drawSkeleton(i); 
		      updateSkelPos(i);
		    }
		}

	}

	
	public void updateSkelPos(int userId)
	{
	  // get 3D position of a joint
		
	  PVector positionReelPartieDuCorps = new PVector();
	  PVector positionPartieDuCorps = new PVector(); 
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_HEAD,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  tete.setPoint(new Pt((context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));
		 
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_HAND,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  main_droite.setPoint(new Pt((context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));
		 
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_HAND,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  main_gauche.setPoint(new Pt((context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));
	   
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_NECK,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  cou.setPoint(new Pt((context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_SHOULDER,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  epaule_droite.setPoint(new Pt( (context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_SHOULDER,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  epaule_gauche.setPoint(new Pt( (context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));
	  
	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_ELBOW,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  coude_droit.setPoint(new Pt( (context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_ELBOW,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  coude_gauche.setPoint(new Pt((context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_HIP,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  hanche_droite.setPoint(new Pt((context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_HIP,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  hanche_gauche.setPoint(new Pt( (context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_KNEE,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  genou_droit.setPoint(new Pt( (context.depthWidth()-positionPartieDuCorps.x),(positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_KNEE,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  genou_gauche.setPoint(new Pt( (context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_FOOT,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  pied_droit.setPoint(new Pt((context.depthWidth()-positionPartieDuCorps.x), (positionPartieDuCorps.y), (positionPartieDuCorps.z)));

	  context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_FOOT,positionReelPartieDuCorps);
	  context.convertRealWorldToProjective(positionReelPartieDuCorps,positionPartieDuCorps);
	  pied_gauche.setPoint(new Pt( (context.depthWidth()-positionPartieDuCorps.x),(positionPartieDuCorps.y), (positionPartieDuCorps.z)));
	
	 
	  this.instanceKinect.actualiser();
	  this.panelKinect.repaint();
	  this.l.repaint();
	}
	 
	// draw the skeleton with the selected joints
	public void drawSkeleton(int userId)
	{  

	}
	 
	public void onNewUser(int userId)
	{
		System.out.println("New User Detected - userId: " + userId);
	 
	 // start pose detection
	  context.startPoseDetection("Psi",userId);
	}
	
	public void onLostUser(int userId)
	{
		System.out.println("User Lost - userId: " + userId);
	}
	 
	public void onStartPose(String pose,int userId)
	{
		System.out.println("Start of Pose Detected  - userId: " + userId + ", pose: " + pose);
	 
	  // stop pose detection
	  context.stopPoseDetection(userId); 
	 
	  // start attempting to calibrate the skeleton
	  context.requestCalibrationSkeleton(userId, true); 
	}
	 
	// when calibration begins
	public void onStartCalibration(int userId)
	{
		System.out.println("Beginning Calibration - userId: " + userId);
	}
	 
	// when calibaration ends - successfully or unsucessfully 
	public void onEndCalibration(int userId, boolean successfull)
	{
		System.out.println("Calibration of userId: " + userId + ", successfull: " + successfull);
	 
	  if (successfull) 
	  { 
		  System.out.println("  User calibrated !!!");
		  instanceKinect.detected();
	    // begin skeleton tracking
	    context.startTrackingSkeleton(userId); 
	  } 
	  else 
	  { 
		  System.out.println("  Failed to calibrate user !!!");
	 
	    // Start pose detection
	    context.startPoseDetection("Psi",userId);
	  }
	}

}
