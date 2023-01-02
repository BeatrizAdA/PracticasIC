// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Auxiliary;

import java.util.ArrayList;

public class Node {

	private ArrayList<Node> childs;
	private double positives, negatives, num, merit;
	private String name;
	private Data data;

	public Node() { childs = new ArrayList<Node>(); }

	public Node(ArrayList<Node> childs, double positives, double negatives, double num, double merit, String name) {
		this.childs = childs;
		this.positives = positives;
		this.negatives = negatives;
		this.num = num;
		this.merit = merit;
		this.name = name;
	}

	public Node(String name) {
		this.childs = new ArrayList<Node>();
		this.positives = 0;
		this.negatives = 0;
		this.num = 0;
		this.merit = 0;
		this.name = name;
	}

	public void addChild(Node node) { childs.add(node); }
	public ArrayList<Node> getChild() { return childs; }
	public double getPositives() { return positives; }
	public double getNegatives() { return negatives; }
	public void setNum(double num) { this.num = num; }
	public String getName() { return name; }
	public void deleteColumn(int col){ data.deleteData(col); }
	public Data getData() { return data; }
	public void setData(Data dataList) { this.data = dataList; }
	public void addPositive() { positives++; }
	public void addNegative() { negatives++; }
	
}
