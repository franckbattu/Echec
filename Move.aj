import ca.uqac.jgnault.echec.Plateau;
import ca.uqac.jgnault.echec.pieces.CaseVide;
import ca.uqac.jgnault.echec.pieces.Piece;
import ca.uqac.jgnault.echec.pieces.Pion;

public aspect Move {	
	
	pointcut move(Plateau plateau, int iniX, int iniY, int finX, int finY, boolean PourVrai):
		target(plateau) &&
		args(iniX, iniY, finX, finY, PourVrai) &&
		execution(public boolean Bouger(int, int, int, int, boolean));
	
	after(Plateau plateau, int iniX, int iniY, int finX, int finY, boolean PourVrai) returning(boolean isGood): move(plateau, iniX, iniY, finX, finY, PourVrai) {
		
		if (!isGood) {
			Piece piece = plateau.Case(iniX, iniY);
			
			if (piece.getClass() == Pion.class && PourVrai) {
				int incY = finY - iniY;
				int incX = finX - iniX;
				
				if (incY == 2 && iniY == 1 && incX == 0) {
					plateau.setPos(iniX, iniY, new CaseVide(plateau, iniX, iniY));
					plateau.setPos(finX, finY, new Pion(plateau, piece.retCoulBlanc(), finX, finY));
					isGood = true;
					
				}
			}
		}
	}
}
