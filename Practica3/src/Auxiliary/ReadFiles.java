// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Auxiliary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFiles {

	public static void read() throws FileNotFoundException, IOException, NumberFormatException{
		File file = new File("Iris2Clases.txt");
		
		ArrayList<ArrayList<String>> class1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> class2 = new ArrayList<ArrayList<String>>();
		try (BufferedReader bufferRead = new BufferedReader(new FileReader(file))) {
		    String line;
		    int i = 0;
		    while ((line = bufferRead.readLine()) != null) {
		    	String[] lines = line.split(",");
		    	ArrayList<String> aux = new ArrayList<>();
		    	for(String data : lines){		    		
		    		aux.add(data);
		    	}
		    	if(i == 0)
		    		class1.add(aux);
		    	else if (!class1.get(0).get(class1.get(0).size()-1).equals(aux.get(aux.size()-1)))
		    		class2.add(aux);
		    	else 
		    		class1.add(aux);
		    	i++;
		    }
		} catch (IOException e) {
			throw e;
		}
		
		ArrayList<String> arrayNames = new ArrayList<String>();
		arrayNames.add(class1.get(0).get(class1.get(0).size()-1));
		arrayNames.add(class2.get(0).get(class2.get(0).size()-1));
		Data.setArrayClasses(arrayNames);
		
		double[][] class1Data = new double[class1.size()][class1.get(0).size()-1];
		int i = 0;
		for(ArrayList<String> data: class1){
			for(int j = 0; j < class1.get(0).size()-1; j++){
				class1Data[i][j] = Double.parseDouble(data.get(j));
			}
			i++;
		}
		Data.addArrayData(class1Data);
		
		double[][] class2Data = new double[class2.size()][class2.get(0).size()-1];
		i = 0;
		for(ArrayList<String> data: class2){			
			for(int j = 0; j < class2.get(0).size()-1; j++){
				class2Data[i][j] = Double.parseDouble(data.get(j));
			}
			i++;
		}
		Data.addArrayData(class2Data);
		
		exampleFile("TestIris01.txt");
		exampleFile("TestIris02.txt");
		exampleFile("TestIris03.txt");
		
	}
	
	private static void exampleFile(String fileName) throws IOException {
		File file = new File(fileName);
		
		try (BufferedReader bufferRead = new BufferedReader(new FileReader(file))) {
		    String line;
		    ArrayList<String> aux = new ArrayList<>();
		    while ((line = bufferRead.readLine()) != null) {
		    	String[] lines = line.split(",");
		    	for(String data : lines){		    		
		    		aux.add(data);
		    	}
		    }
		    double[] examples = new double[aux.size()-1];
		    for(int i = 0; i < aux.size() - 1; i++){			
		    	examples[i] = Double.parseDouble(aux.get(i));
			}
		    Data.addArrayExamples(examples);
		} catch (IOException e) {
			throw e;
		}
	}
	
}
