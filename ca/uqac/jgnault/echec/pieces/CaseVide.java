package ca.uqac.jgnault.echec.pieces;

import ca.uqac.jgnault.echec.Plateau;

/**
  * Cette classe permet repr�senter dans le jeu une case vide.
  * @see ca.uqac.jgnault.Echec
  */
public class CaseVide extends Piece implements Cloneable{
/**
  * Constructeur par d�faut.
  */
	public CaseVide(Plateau J, int PX, int PY){
		super (J, PX, PY);
	}
	
	
	public CaseVide(CaseVide original){
		super(original);
	}

/**
  * La m�thode Bouger de Piece est ici red�finie pour interdire de bouger 
  * une case vide.
  * @return Toujours faux.
  */
	@Override
	public boolean Bouger (int bidJoueur, int bidIncX, int bidIncY, boolean PourVrai){
		return false;				//On ne peut jouer une case vide
	}
	
	
	public String toString()	{ return " ";			}
	public Object clone() 		{ return super.clone();	}
}
