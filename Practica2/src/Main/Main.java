// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Main;

import java.io.IOException;
import javax.swing.JOptionPane;
import Auxiliary.Node;
import Model.Algorithm;
import View.MainView;

public class Main {

	public static void main(String[] args) {
		Algorithm algorithmId3 = new  Algorithm();
		Node tree;
		
		try {
			algorithmId3.gameAttributes("AtributosJuego.txt");
			tree = algorithmId3.game("Juego.txt");

			MainView view = new MainView(algorithmId3.getAttributes(), tree);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al intentar leer los archivos");
		}
	}
	
}