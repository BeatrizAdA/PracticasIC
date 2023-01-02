// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Auxiliary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Attribute {

	private HashMap<String, Double> p, n, merit;
	private HashMap<String, Integer> a;
	private int total;
	private String name;

	public Attribute(String name) {
		this.name = name;
		p = new HashMap<String, Double>();
		n = new HashMap<String, Double>();
		a = new HashMap<String, Integer>();
		merit= new HashMap<String,Double>();
	}

	public void addElement(String attributeName, boolean type) {
		if (type) {
			if (p.containsKey(attributeName)) {
				p.put(attributeName, p.get(attributeName) + 1);
			}
			else {
				p.put(attributeName, 1.0);
			}
		} 
		else {
			if (n.containsKey(attributeName)) {
				n.put(attributeName, n.get(attributeName) + 1);
			}
			else {
				n.put(attributeName, 1.0);
			}
		}
	}	

	public double calculateMerit(String attributeName) {
		double nAux = 0;
		double pAux = 0;
		double rAux = 0;

		if (n.containsKey(attributeName)) {
			nAux = n.get(attributeName);
		} 

		if (p.containsKey(attributeName)) {
			pAux = p.get(attributeName);
		} 
		
		double aux= a.get(attributeName);
		rAux = aux/total;

		double meritAux = 0;

		if (nAux == 0) {
			meritAux = (-pAux * ((Math.log(pAux) / Math.log(2))));
		} 
		else if (pAux == 0) {
			meritAux = (-(nAux * (Math.log(nAux) / Math.log(2))));
		} 
		else {
			meritAux = (-pAux * ((Math.log(pAux) / Math.log(2))) - (nAux * (Math.log(nAux) / Math.log(2))));
		}

		return rAux*meritAux;
	}

	public void delete(){		
		p.clear();
		n.clear();
		a.clear();
		merit.clear();
		total = 0;
	}

	public void update() {
		Set<String> keySet = new HashSet<String>(p.keySet());
		for (String key : n.keySet()) {
			if (!keySet.contains(key)) {
				keySet.add(key);
			}
		}

		for (String key : keySet) {
			if (p.containsKey(key) && n.containsKey(key)) {
				total += (p.get(key).intValue() + n.get(key).intValue());
				a.put(key, p.get(key).intValue() + n.get(key).intValue());
				p.put(key, p.get(key) / a.get(key));
				n.put(key, n.get(key) / a.get(key));
			} 
			else if (p.containsKey(key)) {
				total += p.get(key).intValue();
				a.put(key, p.get(key).intValue());
				p.put(key, p.get(key) / a.get(key));
			} 
			else {
				total += n.get(key).intValue();
				a.put(key, n.get(key).intValue());
				n.put(key, n.get(key) / a.get(key));
			}
		}
		
		for(String key: keySet){
			merit.put(key, calculateMerit(key));
		}
	}

	public double getMerit(){
		double totalMerit = 0;
		for(String value : merit.keySet()){
			totalMerit+=merit.get(value);
		}
		return totalMerit;
	}
	
	public int getPositives() {	return p.size(); }
	public int getNegatives() { return n.size(); }
	public double getA(String attributeName){ return a.get(attributeName); }
	public double getMerit(String attributeName){ return merit.get(attributeName); }
	public Set<String> getKeys(){ return a.keySet(); }
	public String getName() { return name; }
	
}
