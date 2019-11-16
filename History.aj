import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import ca.uqac.jgnault.echec.*;
import ca.uqac.jgnault.echec.pieces.*;

public aspect History {
		
	
	pointcut mainMethod(): execution(public static void main(String[]));
	
	pointcut move(Plateau plateau, int iniX, int iniY, int finX, int finY, boolean PourVrai):
		target(plateau) &&
		args(iniX, iniY, finX, finY, PourVrai) &&
		execution(public boolean Bouger(int, int, int, int, boolean));
	
	before(): mainMethod() {
		try {
			Files.deleteIfExists(Paths.get("Partie.txt"));
			Files.write(Paths.get("Partie.txt"), ("Historique :" + System.lineSeparator()).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	after(Plateau plateau, int iniX, int iniY, int finX, int finY, boolean PourVrai) returning(boolean isGood): move(plateau, iniX, iniY, finX, finY, PourVrai) {
		if (isGood) {
			Piece piece = plateau.Case(finX, finY);
			if (!piece.isCaseVide()) {
				String coup = "Déplacement de " + piece + " de (x: " + iniX + ", y: " + iniY + ") à (x: " + finX + ", y: " + finY + ")";
				try {
					Files.write(Paths.get("Partie.txt"), (coup +  System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}