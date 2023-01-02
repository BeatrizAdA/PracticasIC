/*
		ALUMNA: BEATRIZ ALVAREZ DE ARRIBA
 */

package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Auxiliar.Casilla;
import Negocio.Algoritmo;

public class PanelPrincipal extends JFrame{
	
	// ---------------------- PANEL ----------------------
	private JPanel panel;
	private JPanel panelDimensionesTablero;
	private JPanel panelBotonInicio;
	private JPanel panelBotonFinal;
	private JPanel panelCasillasProhibidas;
	private JPanel panelWayPoints;
	private JPanel panelAlgoritmo;
	private JPanel panelReiniciarIniciar;
	
	// ---------------------- LABEL ----------------------
	private JLabel labelDimensiones;
	private JLabel labelFilas;
	private JLabel labelColumnas;
	private JLabel labelInicio;
	private JLabel labelFilaInicio;
	private JLabel labelColumnaInicio;
	private JLabel labelFinal;
	private JLabel labelFilaFinal;
	private JLabel labelColumnaFinal;
	private JLabel labelCasillasProhibidas;
	private JLabel labelFilaProhibidas;
	private JLabel labelColumnaProhibidas;
	private JLabel labelWayPoints;
	private JLabel labelFilaWayPoints;
	private JLabel labelColumnaWayPoints;
	
	// ---------------------- TEXTFIELD ----------------------
	private JTextField textFila;
	private JTextField textColumna;
	private JTextField textFilaInicio;
	private JTextField textColumnaInicio;
	private JTextField textFilaFinal;
	private JTextField textColumnaFinal;
	private JTextField textFilaProhibidas;
	private JTextField textColumnaProhibidas;
	private JTextField textFilaWayPoints;
	private JTextField textColumnaWayPoints;
	
	// ---------------------- BUTTON ----------------------
	private JButton[][] casillas;
	private JButton casilla;
	private JButton botonDimensiones;
	private JButton botonInicio;
	private JButton botonFinal;
	private JButton botonCasillasProhibidas;
	private JButton botonReiniciar;
	private JButton botonIniciarAlgoritmo;
	private JButton botonWayPoints;
	
	// ---------------------- OTROS ----------------------
	private int fila = -1;
	private int columna = -1;
	private int filaInicio = -1;
	private int columnaInicio = -1;
	private int filaFinal = -1;
	private int columnaFinal = -1;
	private int filaP = -1;
	private int columnaP = -1;
	private int filaWP = -1;
	private int columnaWP = -1;
	private List<Casilla> casillasProhibidas = new ArrayList<Casilla>();
	private List<Casilla> casillasWayPoints = new ArrayList<Casilla>();
	private Tablero tablero;
	private List<Casilla> camino = new ArrayList<Casilla>();
	private List<Casilla> caminoSolucion = new ArrayList<Casilla>();
	private Casilla casillaInicio;
	private Casilla casillaFin;
	private List<Casilla> caminoWP = new ArrayList<Casilla>();
	private List<Casilla> caminoSolWP = new ArrayList<Casilla>();
	
	public PanelPrincipal() {
		setTitle("Algoritmo A*");
		setSize(800, 700);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		getContentPane().add(panel);
		
		// ---------------------- DIMENSIONES ----------------------
		panelDimensionesTablero = new JPanel();
		panelDimensionesTablero.setPreferredSize(new Dimension(130, 80));
		panelDimensionesTablero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.add(panelDimensionesTablero);
		labelDimensiones = new JLabel("         DIMENSIONES         ");
		labelDimensiones.setFont(new Font("Arial", Font.BOLD, 11));
		panelDimensionesTablero.add(labelDimensiones);
		labelFilas = new JLabel("F:");
		labelFilas.setFont(new Font("Arial", Font.BOLD, 11));
		panelDimensionesTablero.add(labelFilas);
		textFila = new JTextField(2);
		panelDimensionesTablero.add(textFila);
		labelColumnas = new JLabel("C:");
		labelColumnas.setFont(new Font("Arial", Font.BOLD, 11));
		panelDimensionesTablero.add(labelColumnas);
		textColumna = new JTextField(2);
		panelDimensionesTablero.add(textColumna);
		botonDimensiones = new JButton("Aceptar");
		botonDimensiones.setFont(new Font("Arial", Font.BOLD, 11));
		panelDimensionesTablero.add(botonDimensiones);		
		
		// ---------------------- INICIO ----------------------
		panelBotonInicio = new JPanel();
		panelBotonInicio.setPreferredSize(new Dimension(130, 80));
		panelBotonInicio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.add(panelBotonInicio);
		labelInicio = new JLabel("               INICIO               ");
		labelInicio.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonInicio.add(labelInicio);
		labelFilaInicio = new JLabel("F:");
		labelFilaInicio.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonInicio.add(labelFilaInicio);
		textFilaInicio = new JTextField(2);
		panelBotonInicio.add(textFilaInicio);
		labelColumnaInicio = new JLabel("C:");
		labelColumnaInicio.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonInicio.add(labelColumnaInicio);
		textColumnaInicio = new JTextField(2);
		panelBotonInicio.add(textColumnaInicio);
		botonInicio = new JButton("Aceptar");
		botonInicio.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonInicio.add(botonInicio);
		
		// ---------------------- FINAL ----------------------
		panelBotonFinal = new JPanel();
		panelBotonFinal.setPreferredSize(new Dimension(130, 80));
		panelBotonFinal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.add(panelBotonFinal);
		labelFinal = new JLabel("                 FINAL                 ");
		labelFinal.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonFinal.add(labelFinal);
		labelFilaFinal = new JLabel("F:");
		labelFilaFinal.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonFinal.add(labelFilaFinal);
		textFilaFinal = new JTextField(2);
		panelBotonFinal.add(textFilaFinal);
		labelColumnaFinal = new JLabel("C:");
		labelColumnaFinal.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonFinal.add(labelColumnaFinal);
		textColumnaFinal = new JTextField(2);
		panelBotonFinal.add(textColumnaFinal);
		botonFinal = new JButton("Aceptar");
		botonFinal.setFont(new Font("Arial", Font.BOLD, 11));
		panelBotonFinal.add(botonFinal);
		
		// ---------------------- CASILLAS PROHIBIDAS ----------------------
		panelCasillasProhibidas = new JPanel();
		panelCasillasProhibidas.setPreferredSize(new Dimension(130, 80));
		panelCasillasProhibidas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.add(panelCasillasProhibidas);
		labelCasillasProhibidas = new JLabel(" CASILLAS PROHIBIDAS ");
		labelCasillasProhibidas.setFont(new Font("Arial", Font.BOLD, 11));
		panelCasillasProhibidas.add(labelCasillasProhibidas);
		labelFilaProhibidas = new JLabel("F:");
		labelFilaProhibidas.setFont(new Font("Arial", Font.BOLD, 11));
		panelCasillasProhibidas.add(labelFilaProhibidas);
		textFilaProhibidas = new JTextField(2);
		panelCasillasProhibidas.add(textFilaProhibidas);
		labelColumnaProhibidas = new JLabel("C:");
		labelColumnaProhibidas.setFont(new Font("Arial", Font.BOLD, 11));
		panelCasillasProhibidas.add(labelColumnaProhibidas);
		textColumnaProhibidas = new JTextField(2);
		panelCasillasProhibidas.add(textColumnaProhibidas);
		botonCasillasProhibidas = new JButton("Aceptar");
		botonCasillasProhibidas.setFont(new Font("Arial", Font.BOLD, 11));
		panelCasillasProhibidas.add(botonCasillasProhibidas);
		
		// ---------------------- WAY POINTS ----------------------
		panelWayPoints = new JPanel();
		panelWayPoints.setPreferredSize(new Dimension(130, 80));
		panelWayPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.add(panelWayPoints);
		labelWayPoints = new JLabel("        WAY POINTS        ");
		labelWayPoints.setFont(new Font("Arial", Font.BOLD, 11));
		panelWayPoints.add(labelWayPoints);
		labelFilaWayPoints = new JLabel("F:");
		labelFilaWayPoints.setFont(new Font("Arial", Font.BOLD, 11));
		panelWayPoints.add(labelFilaWayPoints);
		textFilaWayPoints = new JTextField(2);
		panelWayPoints.add(textFilaWayPoints);
		labelColumnaWayPoints = new JLabel("C:");
		labelColumnaWayPoints.setFont(new Font("Arial", Font.BOLD, 11));
		panelWayPoints.add(labelColumnaWayPoints);
		textColumnaWayPoints = new JTextField(2);
		panelWayPoints.add(textColumnaWayPoints);
		botonWayPoints = new JButton("Aceptar");
		botonWayPoints.setFont(new Font("Arial", Font.BOLD, 11));
		panelWayPoints.add(botonWayPoints);
		
		// ---------------------- REINICIAR E INICIAR ALGORITMO ----------------------
		panelReiniciarIniciar = new JPanel();
		panelReiniciarIniciar.setPreferredSize(new Dimension(100, 80));
		panel.add(panelReiniciarIniciar);
		botonReiniciar = new JButton("Reiniciar");
		botonReiniciar.setFont(new Font("Arial", Font.BOLD, 11));
		panelReiniciarIniciar.add(botonReiniciar);
		botonIniciarAlgoritmo = new JButton("Iniciar");
		botonIniciarAlgoritmo.setFont(new Font("Arial", Font.BOLD, 11));
		panelReiniciarIniciar.add(botonIniciarAlgoritmo);
				
		// ---------------------- ALGORITMO ----------------------
		panelAlgoritmo = new JPanel();
		panelAlgoritmo.setPreferredSize(new Dimension(650, 550));
		panelAlgoritmo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.add(panelAlgoritmo);
		
		// ---------------------- PINTAR TABLERO ----------------------
		botonDimensiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				fila = Integer.parseInt(textFila.getText());
	            columna = Integer.parseInt(textColumna.getText());
	            if(fila > 30 || columna > 30) {
	            	JOptionPane.showMessageDialog(null,"Dimensiones superiores a 30");
	            }
	            else {
	            	casillas = new JButton[fila][columna];
	            	panelAlgoritmo.setLayout(new GridLayout(fila, columna));
		            tablero = new Tablero(fila, columna, panelAlgoritmo, -1, -1, -1, -1, casillasProhibidas, casillasWayPoints, caminoSolucion);
		            tablero.pintarTablero();
		            panelAlgoritmo.updateUI();
	            }
			}
		});
		
		// ---------------------- RENICIAR TABLERO ----------------------
		botonReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				panelAlgoritmo.removeAll();
				casillasProhibidas = new ArrayList<Casilla>();
				casillasWayPoints = new ArrayList<Casilla>();
				caminoSolucion = new ArrayList<Casilla>();
				panelAlgoritmo.updateUI();
			}
		});
		
		// ---------------------- PONER INICIO ----------------------
		botonInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				filaInicio = Integer.parseInt(textFilaInicio.getText());
				columnaInicio = Integer.parseInt(textColumnaInicio.getText());
				if(filaInicio > fila || filaInicio < 1 || columnaInicio > columna || columnaInicio < 1) {
					JOptionPane.showMessageDialog(null,"La fila o la columna no están dentro de las dimensiones del tablero");
				}
				else {
					casillasWayPoints.add(new Casilla(filaInicio, columnaInicio, 0, 0, 0));
					tablero = new Tablero(fila, columna, panelAlgoritmo, filaInicio, columnaInicio, -1, -1, casillasProhibidas, casillasWayPoints, caminoSolucion);
					tablero.pintarTablero();
		            panelAlgoritmo.updateUI();
				}
			}
	    });
		
		// ---------------------- PONER FINAL ----------------------
		botonFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				filaFinal = Integer.parseInt(textFilaFinal.getText());
				columnaFinal = Integer.parseInt(textColumnaFinal.getText());
				if(filaFinal > fila || filaFinal < 1 || columnaFinal > columna || columnaFinal < 1) {
					JOptionPane.showMessageDialog(null,"La fila o la columna no están dentro de las dimensiones del tablero");
				}
				else {
					tablero = new Tablero(fila, columna, panelAlgoritmo, filaInicio, columnaInicio, filaFinal, columnaFinal, casillasProhibidas, casillasWayPoints, caminoSolucion);
					tablero.pintarTablero();
		            panelAlgoritmo.updateUI();
				}	
			}
		});
		
		// ---------------------- PONER CASILLAS PROHIBIDAS ----------------------
		botonCasillasProhibidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				filaP = Integer.parseInt(textFilaProhibidas.getText());
				columnaP = Integer.parseInt(textColumnaProhibidas.getText());
				if(filaP > fila || filaP < 1 || columnaP > columna || columnaP < 1) {
					JOptionPane.showMessageDialog(null,"La fila o la columna no están dentro de las dimensiones del tablero");
				}
				else {
					Casilla casilla = new Casilla(filaP, columnaP, 0, 0, 0);
			        casillasProhibidas.add(casilla);
					tablero = new Tablero(fila, columna, panelAlgoritmo, filaInicio, columnaInicio, filaFinal, columnaFinal, casillasProhibidas, casillasWayPoints, caminoSolucion);
					tablero.pintarTablero();
				    panelAlgoritmo.updateUI();
				}
			}
		});
		
		// ---------------------- PONER WAY POINTS ----------------------
		botonWayPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				filaWP = Integer.parseInt(textFilaWayPoints.getText());
				columnaWP = Integer.parseInt(textColumnaWayPoints.getText());
				if(filaWP > fila || filaWP < 1 || columnaWP > columna || columnaWP < 1) {
					JOptionPane.showMessageDialog(null,"La fila o la columna no están dentro de las dimensiones del tablero");
				}
				else {
					Casilla casilla = new Casilla(filaWP, columnaWP, 0, 0, 0);
			        casillasWayPoints.add(casilla);
					tablero = new Tablero(fila, columna, panelAlgoritmo, filaInicio, columnaInicio, filaFinal, columnaFinal, casillasProhibidas, casillasWayPoints, caminoSolucion);
					tablero.pintarTablero();
				    panelAlgoritmo.updateUI();
				}
			}
		});
		
		// ---------------------- ALGORITMO A* ----------------------
		botonIniciarAlgoritmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(casillasWayPoints.size() > 1) {
					boolean solucion = true;
					casillasWayPoints.add(new Casilla(filaFinal, columnaFinal, 0, 0, 0));
					for(int i = 1; i < casillasWayPoints.size(); i++) {
						casillaInicio = casillasWayPoints.get(i-1);
						casillaFin = casillasWayPoints.get(i);
						Algoritmo algoritmo = new Algoritmo(casillaInicio, casillaFin, fila, columna, casillasProhibidas);
						camino = algoritmo.caminoSolucion();
						if(!algoritmo.haySolucion()) {
							solucion = false;
						}
						else {
							caminoSolucion.add(camino.get(camino.size()-1));
							Casilla casillaAux = camino.get(camino.size()-1);
							for(int j = camino.size() - 2; j >= 0; j--) {
								if(casillaAux.getFilaAnterior() == camino.get(j).getFila() && casillaAux.getColumnaAnterior() == camino.get(j).getColumna()) {
									caminoSolucion.add(camino.get(j));
									casillaAux = camino.get(j);
								}
							}
							for(int k = caminoSolucion.size() - 1; k > 0; k--) {
								caminoSolWP.add(caminoSolucion.get(k));
							}
							caminoSolucion = new ArrayList<Casilla>();
						}
					}
					caminoSolWP.add(casillaFin);
					if(!solucion) {
						JOptionPane.showMessageDialog(null,"No se ha encontrado camino");
					}
					else {
						tablero = new Tablero(fila, columna, panelAlgoritmo, filaInicio, columnaInicio, filaFinal, columnaFinal, casillasProhibidas, casillasWayPoints, caminoSolWP);
						tablero.pintarTablero();
					    panelAlgoritmo.updateUI();
					    System.out.println("Algoritmo A* --> Camino");
					    for(int i = 0; i < caminoSolWP.size(); i++) {
							System.out.println("(" + caminoSolWP.get(i).getFila() + "," + caminoSolWP.get(i).getColumna() + ")");
						}
					}
				}
				else {
					casillaInicio = new Casilla(filaInicio, columnaInicio, 0, 0, 0);
					casillaFin = new Casilla(filaFinal, columnaFinal, 0, 0, 0);
					Algoritmo algoritmo = new Algoritmo(casillaInicio, casillaFin, fila, columna, casillasProhibidas);
					camino = algoritmo.caminoSolucion();
					if(!algoritmo.haySolucion()) {
						JOptionPane.showMessageDialog(null,"No se ha encontrado camino");
					}
					else {
						caminoSolucion.add(camino.get(camino.size()-1));
						Casilla casillaAux = camino.get(camino.size()-1);
						for(int i = camino.size() - 2; i >= 0; i--) {
							if(casillaAux.getFilaAnterior() == camino.get(i).getFila() && casillaAux.getColumnaAnterior() == camino.get(i).getColumna()) {
								caminoSolucion.add(camino.get(i));
								casillaAux = camino.get(i);
							}
						}
						tablero = new Tablero(fila, columna, panelAlgoritmo, filaInicio, columnaInicio, filaFinal, columnaFinal, casillasProhibidas, casillasWayPoints, caminoSolucion);
						tablero.pintarTablero();
					    panelAlgoritmo.updateUI();
					    System.out.println("Algoritmo A* --> Camino");
					    for(int i = caminoSolucion.size() - 1; i >= 0; i--) {
							System.out.println("(" + caminoSolucion.get(i).getFila() + "," + caminoSolucion.get(i).getColumna() + ")");
						}
					}
				}
			}
		});
		
	}
	
}
