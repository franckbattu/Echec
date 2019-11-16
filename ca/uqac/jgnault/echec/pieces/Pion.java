package ca.uqac.jgnault.echec.pieces;

import ca.uqac.jgnault.echec.Plateau;

/**
  * Cette classe permet d'implanter des pions au �chec.
  * @see ca.uqac.jgnault.Echec
  */
public class Pion extends Piece implements Cloneable{
	/**
	 * Constructeur standard
	 * @param J le plateau de jeu
	 * @param Coul 1: blanc, -1: noir
	 * @param PX : no colonne
	 * @param PY : no ligne
	 */
	public Pion(Plateau J, int Coul, int PX, int PY){
		super (J, 1, Coul, PX, PY);
	}

/**
  * Il y a ici une validation sp�cifique pour ne permettre que les 
  * mouvement d'avant ou en diagonal pour une prise, et cela d'une seule case.
  * @param incX D�placment demand� en X.
  * @param incY D�placement demand� en Y.
  * @return Vrai si le d�placement a eu lieu correctement, faux sinon.
  */
	public boolean Bouger(int Joueur, int incX, int incY, boolean PourVrai){
		if (incY == CoulBlanc)			//Si c'est la bonne direction
			if (incX == 0)				// s'il avance
				{
					if (Jeu.Case(PosX, PosY+incY).isCaseVide())	//vide devant
						return super.Bouger(Joueur, incX, incY, PourVrai);
				}
			else						// s'il mange
				if (Math.abs(incX) == 1)//S'il va en Dia (manger une piece)
					if (!Jeu.Case(PosX + incX, PosY + incY).isCaseVide())
						return super.Bouger(Joueur, incX, incY, PourVrai);
		return false;
	}

	public String toString()	{ return (retCoulBlanc() == 1 ? "P" : "p");	}
	public Object clone() 		{ return super.clone();	}
}
