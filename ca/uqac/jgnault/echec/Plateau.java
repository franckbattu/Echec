package ca.uqac.jgnault.echec;

import ca.uqac.jgnault.echec.pieces.*;

/**
  * Cette classe permet de construire, afficher en console et valider certains
  * d�placement dans un jeu d'�chec.  
  * @see ca.uqac.jgnault.Echec ca.uqac.jgnault.echec.Piece
  */
public class Plateau{
	private final int T = 8;
	private Piece Jeu[][];
	
	private int Joueur = 1;		// 1 Blanc, -1 Noir

/**
  * Constructeur qui fait un jeu de taille et de disposition standard.
  */
	public Plateau(){
		Jeu = new Piece[T][T];

		for (int y=2; y < T-2; y++)					// Les cases du centre
			for (int x=0; x<T; x++)
				Jeu[y][x] = new CaseVide (this, x, y);
				
		for (int x=0; x < T; x++){					// Les pions
			Jeu[1][x] = new Pion (this, 1, x, 1);
			Jeu[6][x] = new Pion (this, -1, x, 6);
		}
		for (int x=2; x<8; x +=3){					// Les 4 Fous
			Jeu[0][x] = new Fou (this, 1, x, 0);
			Jeu[7][x] = new Fou (this, -1, x, 7);
		}
		
		for (int x=1; x<8; x +=5){					// Les 4 Cavaliers
			Jeu[0][x] = new Cavalier (this, 1, x, 0);
			Jeu[7][x] = new Cavalier (this, -1, x, 7);
		}

		for (int x=0; x< 8; x+=7){					// Les 4 tours		
			Jeu[0][x] = new Tour (this, 1, x, 0);
			Jeu[7][x] = new Tour (this, -1, x, 7);
		}
		Jeu[0][3] = new Reine (this, 1, 3, 0);		// Reine Blanche
		Jeu[7][3] = new Reine (this, -1,3, 7);		// Reine Noire

		Jeu[0][4] = new Roi (this, 1, 4, 0);		// Roi Blanc
		Jeu[7][4] = new Roi (this, -1,4, 7);		// Roi Noir
	}
	
	
	/**
	 * Cette m�thode effectue une copie du plateau, mais pas des pi�ces.
	 */
	public Object clone(){
		Plateau Ret = new Plateau ();
		
		Ret.Jeu = new Piece[T][T];
		for (int y = 0; y < T; y++)	
			for (int x = 0; x < T; x++)
				Ret.Jeu[y][x] = (Piece)Jeu[y][x].clone();
				
		Ret.Joueur = Joueur;		// 1 Blanc, -1 Noir
		
		return Ret;
	}	
	

/**
  * Affiche le jeu au complet en utilisant le polymorphisme des pi�ces.
  */
	public void Afficher(){
		for (int y = T - 1; y >= 0; y--){
			System.out.print ((char)('1' + y) + "| ");
			for (int x=0; x < T; x++)
				if (Jeu[y][x] != null){
					Jeu[y][x].Afficher();
					System.out.print (" ");
				}					
			System.out.println ();
		}
		
		System.out.print ("   ");
		for (int x=0; x < T; x++)
			System.out.print ((char)('a'+ x) + " ");
		System.out.println ();
	}
	

/**
  * Encapsuleur du tableau de Piece.
  */
	public Piece Case(int x, int y){
		return Jeu[y][x];
	}

	
/**
  * Utilise le polymorphisme pour Appeler jouer sur le bon objet.
  * @return Vrai si le coup est valide, faux sinon.
  * @param IniX Valeur initiale en X.
  * @param IniY Valeur initiale en Y.
  * @param finX Valeur finale en Y.
  * @param finX Valeur finale en Y
  */  
  	public boolean Bouger (int iniX, int iniY, int finX, int finY, 
  							boolean PourVrai){
		if (finX-iniX == 0 && finY-iniY == 0) return false;
		boolean isGood = false;
		try{
			isGood = Jeu[iniY][iniX].Bouger(Joueur, finX-iniX, finY-iniY, 
											PourVrai);
		}
		catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
		if (isGood){
			Joueur = -Joueur;
		}
		
		return isGood;
	}

/** Encapsulateur qui permet de mettre des pi�ces ou l'on veux dans le jeu.
  * @param X Posision en X.
  * @param Y Position en Y.
  * @param P La pi�ce � ajouter.
  */
	public void setPos(int X, int Y, Piece P){
		Jeu[Y][X] = P;
	}		
}