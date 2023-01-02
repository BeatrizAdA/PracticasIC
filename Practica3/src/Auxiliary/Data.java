// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Auxiliary;

import java.util.ArrayList;

public class Data {

	private static ArrayList<String> arrayClasses;
	private static ArrayList<double[][]> arrayData;
	private static ArrayList<double[]> arrayExamples;
	
	public static ArrayList<String> getArrayClasses() { return arrayClasses; }
	public static void setArrayClasses(ArrayList<String> arrayClasses) { Data.arrayClasses = arrayClasses; }
	public static ArrayList<double[][]> getArrayData() { return arrayData; }
	public static ArrayList<double[]> getArrayExamples() { return arrayExamples; }
	
	public static void addArrayData(double[][] data) {
		if(Data.arrayData == null){
			Data.arrayData = new ArrayList<double [][]>();
		}
		Data.arrayData.add(data);
	}
	
	public static void addArrayExamples(double[] example) {
		if(Data.arrayExamples == null){
			Data.arrayExamples = new ArrayList<double []>();
		}
		Data.arrayExamples.add(example);
	}
	
	public static String getExamples(){
		String examples = "";
		int i = 0;
		for(double[] example: arrayExamples){
			for(int j = 0; j < example.length; j++){
				if(j == 0)
					examples += (i+1) + "º = " + example[j];
				else
					examples += ", " + example[j];
			}
			examples += "\n";
			i++;
		}
		return examples;
	}
		
}
