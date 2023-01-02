/*
		ALUMNA: BEATRIZ ALVAREZ DE ARRIBA
 */

package Presentacion;

import java.awt.Color;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import Auxiliar.Casilla;

public class Tablero{
	
	private PanelPrincipal panelPrincipal;
	private JButton[][] casillas;
	private int filas;
	private int columnas;
	private JPanel panelAlgoritmo;
	private JButton casilla;
	private int filaInicio;
	private int columnaInicio;
	private int filaFinal;
	private int columnaFinal;
	private List<Casilla> casillasProhibidas;
	private List<Casilla> casillasWayPoints;
	private List<Casilla> caminoSolucion;

	public Tablero(int filas, int columnas, JPanel panelAlgoritmo, int filaInicio, int columnaInicio, int filaFinal, int columnaFinal, List<Casilla> casillasProhibidas, List<Casilla> casillasWayPoints, List<Casilla> caminoSolucion) {
		panelPrincipal = new PanelPrincipal();
		casillas = new JButton[filas][columnas];
		this.filas = filas;
		this.columnas = columnas;
		this.panelAlgoritmo = panelAlgoritmo;
		this.filaInicio = filaInicio;
		this.columnaInicio = columnaInicio;
		this.filaFinal = filaFinal;
		this.columnaFinal = columnaFinal;
		this.casillasProhibidas = casillasProhibidas;
		this.casillasWayPoints = casillasWayPoints;
		this.caminoSolucion = caminoSolucion;
	}
	
	public void pintarTablero() {
		panelAlgoritmo.removeAll();
		for(int i = filas; i > 0; i--) {
			for(int j = 1; j <= columnas; j++) {
				casilla = new JButton();
				casilla.setBackground(Color.WHITE);
				if(caminoSolucion.size() > 0) {
					for(int k = 1; k < caminoSolucion.size() - 1; k++) {
						if(i == caminoSolucion.get(k).getFila() && j == caminoSolucion.get(k).getColumna()) {
							casilla.setBackground(Color.BLACK);
						}
					}
				}
				if(casillasWayPoints.size() > 0) {
					for(int k = 1; k < casillasWayPoints.size(); k++) {
						if(i == casillasWayPoints.get(k).getFila() && j == casillasWayPoints.get(k).getColumna()) {
							casilla.setBackground(Color.YELLOW);
						}
					}
				}
				if(i == filaInicio && j == columnaInicio) {
					casilla.setBackground(Color.BLUE);
				}
				if (i == filaFinal && j == columnaFinal) {
					casilla.setBackground(Color.GREEN);
				}
				if(casillasProhibidas.size() > 0){
					for(int k = 0; k < casillasProhibidas.size(); k++) {
						if(i == casillasProhibidas.get(k).getFila() && j == casillasProhibidas.get(k).getColumna()) {
							casilla.setBackground(Color.RED);
						}
					}
				}
				panelAlgoritmo.add(casilla);
			}
		}
	}
	
}
