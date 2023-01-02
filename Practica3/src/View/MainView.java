// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Auxiliary.Data;
import Model.Bayes;

public class MainView extends JFrame{

	private JPanel mainPanel;
	private JPanel examplePanel;
	private JLabel exampleLabel;
	private JTextArea exampleTextArea;
	private JScrollPane exampleScrollPane;
	private JButton exampleButton;
	private JPanel resultsPanel;
	private JLabel resultsLabel;
	private JTextArea resultsTextArea;
	private JScrollPane resultsScrollPane;
	private JButton resultsButton;
	
	public MainView() {
		super("Métodos de clasificación: Bayes");
		mainPanel = new JPanel();
		
		examplePanel = new JPanel();
		examplePanel.setPreferredSize(new Dimension(400, 500));
		exampleLabel = new JLabel("EJEMPLOS");
		exampleLabel.setFont(new Font("Arial", Font.BOLD, 17));
		examplePanel.add(exampleLabel);
		exampleTextArea = new JTextArea();
		exampleTextArea.setText(Data.getExamples());
		exampleScrollPane = new JScrollPane (exampleTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		exampleScrollPane.setPreferredSize(new Dimension(350, 400));
		examplePanel.add(exampleScrollPane);
		exampleButton = new JButton("Añadir ejemplo");
		examplePanel.add(exampleButton);

		exampleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String option = JOptionPane.showInputDialog(
						   null,
						   "Nuevo ejemplo:",
						   "Añadir ejemplo",
						   JOptionPane.PLAIN_MESSAGE);
				
				if(option != null){
					String[] line = option.split(",");
					ArrayList<String> aux = new ArrayList<>();
			    	for(String data : line){		    		
			    		aux.add(data);
			    	}	
				    try{
				    	double[] example = new double[aux.size()];
					    for(int i = 0; i < aux.size(); i++){			
					    	example[i] = Double.parseDouble(aux.get(i));
						}			    
					    if(example.length == Data.getArrayExamples().get(0).length){
					      	Data.addArrayExamples(example);
					      	exampleTextArea.setText(Data.getExamples());
					    }
					    else
					    	JOptionPane.showMessageDialog(null, "Formato incorrecto");
				    } catch( NumberFormatException a){
				    	JOptionPane.showMessageDialog(null, "Formato incorrecto");
				    }
				}				
			}
		});
		mainPanel.add(examplePanel);
		
		resultsPanel = new JPanel();
		resultsPanel.setPreferredSize(new Dimension(400, 500));
		resultsLabel = new JLabel("RESULTADOS");
		resultsLabel.setFont(new Font("Arial", Font.BOLD, 17));
		resultsPanel.add(resultsLabel);
		resultsTextArea = new JTextArea();
		resultsScrollPane = new JScrollPane (resultsTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		resultsScrollPane.setPreferredSize(new Dimension(350, 400));
		resultsPanel.add(resultsScrollPane);				
		resultsButton = new JButton("Comprobar resultados");
		resultsPanel.add(resultsButton);
		mainPanel.add(resultsPanel);
		
		resultsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Bayes bayes = new Bayes();	
				for (int i = 0; i < Data.getArrayClasses().size(); i++) {
					bayes.learnAlgorithm(Data.getArrayData().get(i), Data.getArrayClasses().get(i));
				}
				String result = "";
				int i = 1;
				for (double[] example : Data.getArrayExamples()) {
					result += i + "º = " + bayes.algorithmResult(example);
					result += "\n";
					i++;
				}
				resultsTextArea.setText(result);
			}
		});
		
		getContentPane().add(mainPanel);
		setSize(900, 550);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
