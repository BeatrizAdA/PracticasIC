// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Auxiliary;

import java.util.ArrayList;

public class Data {

	private  ArrayList<ArrayList<String>> data;
	
	public Data() { data=new ArrayList<ArrayList<String>>(); }
	
	public void addData(String[] list) {
		ArrayList<String> aux= new ArrayList<String>();
		for(String element: list) aux.add(element);
		data.add(aux);
	}
	
	public void deleteData(int pos){
		for(ArrayList<String> aux: data) aux.remove(pos);
	}
	
	public ArrayList<ArrayList<String>> getData(){ return data; }

	public void add(ArrayList<String> line) { data.add(line); }
	
}
