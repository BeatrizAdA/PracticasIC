// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Main;

import javax.swing.JOptionPane;
import Auxiliary.ReadFiles;
import View.MainView;

public class Main{
	
	public static void main(String[] args) {
		try {
			ReadFiles.read();			
			MainView mainView = new MainView();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falta algun archivo o el formato es incorrecto");
		}
	}
	
}
