/*
		ALUMNA: BEATRIZ ALVAREZ DE ARRIBA
 */

package Auxiliar;

public class Casilla {
	
	private int fila;
	private int columna;
	private double valor;
	private int filaAnterior;
	private int columnaAnterior;
	
	public Casilla(int fila, int columna, int valor, int filaAnterior, int columnaAnterior){
		this.fila = fila;
		this.columna = columna;
		this.valor = valor;
		this.filaAnterior = filaAnterior;
		this.columnaAnterior = columnaAnterior;
	}
	
	public int getFila(){ 
		return fila;
	}
	
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	public int getColumna(){ 
		return columna;
	}
	
	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public int getFilaAnterior(){ 
		return filaAnterior;
	}
	
	public void setFilaAnterior(int filaAnterior) {
		this.filaAnterior = filaAnterior;
	}
	
	public int getColumnaAnterior(){ 
		return columnaAnterior;
	}
	
	public void setColumnaAnterior(int columnaAnterior) {
		this.columnaAnterior = columnaAnterior;
	}
	
}
