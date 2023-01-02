/*
		ALUMNA: BEATRIZ ALVAREZ DE ARRIBA
 */

package Negocio;

import java.util.ArrayList;
import java.util.List;
import Auxiliar.Casilla;

public class Algoritmo {

	private List<Casilla> abierta = new ArrayList<Casilla>();
	private List<Casilla> cerrada = new ArrayList<Casilla>();
	private Casilla casilla;
	private Casilla inicio;
	private Casilla fin;
	private int filasT;
	private int columnasT;
	private List<Casilla> prohibidas;
	private boolean solucion = true;
	
	public Algoritmo(Casilla inicio, Casilla fin, int filasT, int columnasT, List<Casilla> prohibidas) {
		this.inicio = inicio;
		this.fin = fin;
		this.filasT = filasT;
		this.columnasT = columnasT;
		this.prohibidas = prohibidas;
	}
	
	public List<Casilla> caminoSolucion() {
		Casilla casillaActual;
		int pos = 0;
		abierta.add(inicio);
		inicio.setValor(calcularCoste(inicio, fin));
		casillaActual = inicio;
		abierta.remove(pos);
		cerrada.add(casillaActual);
		while(casillaActual.getFila() != fin.getFila() || casillaActual.getColumna() != fin.getColumna()) {
			List<Casilla> adyacentes = adyacentesCasilla(filasT, columnasT, casillaActual.getFila(), casillaActual.getColumna());
			for(int i = 0; i < adyacentes.size(); i++) {
				for(int j = 0; j < prohibidas.size(); j++) {
					if(adyacentes.get(i).getFila() == prohibidas.get(j).getFila() && adyacentes.get(i).getColumna() == prohibidas.get(j).getColumna()) {
						adyacentes.get(i).setFila(0);
						adyacentes.get(i).setColumna(0);
					}
				}
				for(int j = 0; j < cerrada.size(); j++) {
					if(adyacentes.get(i).getFila() == cerrada.get(j).getFila() && adyacentes.get(i).getColumna() == cerrada.get(j).getColumna()) {
						adyacentes.get(i).setFila(0);
						adyacentes.get(i).setColumna(0);
					}
				}
			}
			for(int i = 0; i < adyacentes.size(); i++) {
				boolean estaCasillaEnAbierta = false;
				int a = 0;
				if(adyacentes.get(i).getFila() != 0){
					while(!estaCasillaEnAbierta && a < abierta.size()) {
						if(adyacentes.get(i).getFila() != abierta.get(a).getFila() || adyacentes.get(i).getColumna() != abierta.get(a).getColumna()) {
							a++;
						}
						else {
							estaCasillaEnAbierta = true;
						}
					}
					if(!estaCasillaEnAbierta) {
						adyacentes.get(i).setValor(calcularCoste(adyacentes.get(i), fin));
						abierta.add(adyacentes.get(i));
					}
				}
			}
			if(abierta.size() == 0 && cerrada.size() > 0) {
				casillaActual = fin;
				solucion = false;
			}
			else {
				pos = 0;
				casillaActual = abierta.get(0);
				for(int i = 0; i < abierta.size(); i++) {
					if(casillaActual.getValor() > abierta.get(i).getValor()) {
						casillaActual = abierta.get(i);
						pos = i;
					}
				}
				abierta.remove(pos);
				cerrada.add(casillaActual);
			}
		}
		return cerrada;
	}
	
	private List<Casilla> adyacentesCasilla(int filasTablero, int columnasTablero, int fila, int columna){
		List<Casilla> adyacentes = new ArrayList<Casilla>();
		
		if(fila == 1 && columna == 1) {
			casilla = new Casilla(fila, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);				
		}
		else if(fila == 1 && columna == columnasTablero) {
			casilla = new Casilla(fila, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
		}
		else if(fila == filasTablero && columna == 1) {
			casilla = new Casilla(fila-1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
		}
		else if(fila == filasTablero && columna == columnasTablero) {
			casilla = new Casilla(fila-1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
		}
		else if(fila == 1 && columna > 1 && columna < columnasTablero) {
			casilla = new Casilla(fila, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
		}
		else if(fila > 1 && fila < filasTablero && columna == 1) {
			casilla = new Casilla(fila-1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna, 0, fila, columna);
			adyacentes.add(casilla);
		}
		else if(fila == filasTablero && columna > 1 && columna < columnasTablero) {
			casilla = new Casilla(fila, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
		}
		else if(fila > 1 && fila < filasTablero && columna == columnasTablero) {
			casilla = new Casilla(fila-1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna, 0, fila, columna);
			adyacentes.add(casilla);
		}
		else {
			casilla = new Casilla(fila, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila+1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna+1, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna, 0, fila, columna);
			adyacentes.add(casilla);
			casilla = new Casilla(fila-1, columna-1, 0, fila, columna);
			adyacentes.add(casilla);
		}
		
		return adyacentes;
	}
	
	private double calcularCoste(Casilla ini, Casilla fin) {
		return Math.sqrt(Math.pow(fin.getFila() - ini.getFila(), 2) + Math.pow(fin.getColumna() - ini.getColumna(), 2));
	}
	
	public boolean haySolucion() {
		return solucion;
	}
	
}
