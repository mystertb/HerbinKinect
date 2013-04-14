package modele.motifs.fixes;

/*
 * Cette classe est un point sp�cialement con�u pour �tre un point du squelette. Mais il peut aussi �tre un point normal,
 * pour cela on attribue la valeur "aucun" � la variable "partieDuCorps"
 */

public class PtSkeleton extends Pt {
	
	//Variables de classe
	private Skeleton partieDuCorps;
	
	//Constructeur sans parametre (par d�faut)
	public PtSkeleton() {
		
		super();
		this.setPartieDuCorps(Skeleton.aucun);
		
	}
	
	//Constructeur prenant des coordon�es 2D  en parametres
	public PtSkeleton(double x, double y) {
		
		super(x,y);
		this.setPartieDuCorps(Skeleton.aucun);
		
	}
	
	//Constructeur prenant uniquement la partie du corps en parametres
	public PtSkeleton(Skeleton partieDuCorps) {
		super();
		this.setPartieDuCorps(partieDuCorps);
		
	}
		
	//Constructeur prenant un point en parametres
	public PtSkeleton(Pt pt) {
		
		super(pt.x,pt.y,pt.z);
		this.setPartieDuCorps(Skeleton.aucun);
		
	}
	
	//Constructeur prenant tous les parametres n�cessaire pour faire un ptSkeleton complet (Point + partie du corps)
	public PtSkeleton(Pt pt, Skeleton partieDuCorps) {
		
		super(pt.x,pt.y,pt.z);
		this.setPartieDuCorps(partieDuCorps);
		
	}

	//retourne la partie du corps de ce point
	public Skeleton getPartieDuCorps() {
		return partieDuCorps;
	}

	//attribue une partie du corps au point
	public void setPartieDuCorps(Skeleton partieDuCorps) {
		this.partieDuCorps = partieDuCorps;
	}
	
	

}
