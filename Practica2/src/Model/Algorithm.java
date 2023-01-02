// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Auxiliary.Attribute;
import Auxiliary.Data;
import Auxiliary.Node;

public class Algorithm {

	private ArrayList<Attribute> attributeList;
	private Data data;
	private String last;

	public Algorithm() {
		last = "";
		attributeList = new ArrayList<Attribute>();
		data = new Data();
	}

	public void gameAttributes(String gameAttributesFile) throws IOException {
		String dir = new File("").getAbsolutePath();

		File file = new File(dir, gameAttributesFile);

		String line;
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferReader = new BufferedReader(fileReader);

		while ((line = bufferReader.readLine()) != null) {
			String attributes[] = line.split(",");
			for (String name : attributes) {
				Attribute attribute = new Attribute(name);
				attributeList.add(attribute);
			}
		}
		bufferReader.close();
	}

	public Node game(String gameFile) throws IOException {
		String dir = new File("").getAbsolutePath();

		File file = new File(dir, gameFile);

		String lines;
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferReader = new BufferedReader(fileReader);
		
		while ((lines = bufferReader.readLine()) != null) {
			String attributes[] = lines.split(",");
			data.addData(attributes);
			int i = 0;
			if (last == "") {
				last = attributes[attributes.length - 1];
			}
			for (String name : attributes) {
				if (attributes[attributes.length - 1].compareTo(last) == 0) {
					attributeList.get(i).addElement(name, false);
				}
				else {
					attributeList.get(i).addElement(name, true);
				}
				i++;
			}
		}

		bufferReader.close();

		for (Attribute attribute : attributeList) {
			if (attribute != attributeList.get(attributeList.size() - 1)) {
				attribute.update();
			}
		}

		attributeList.remove(attributeList.size() - 1);

		Node bestNode = updateList(attributeList, data);
		bestNode.setData(data);
		recursion(bestNode, attributeList);

		return bestNode;
	}

	private Node updateList(ArrayList<Attribute> attributeList, Data dataList) {
		Attribute bestAttribute = null;
		double bestMerit = Double.MAX_VALUE;

		int i = 0;

		for (Attribute attribute : attributeList) {
			attribute.delete();
			for (ArrayList<String> dataAux : dataList.getData()) {
				if (dataAux.get(dataAux.size() - 1).compareTo(last) == 0) {
					attribute.addElement(dataAux.get(i), false);
				} 
				else {
					attribute.addElement(dataAux.get(i), true);
				}
			}
			attribute.update();
			double meritAux = attribute.getMerit();
			System.out.println(attribute.getName() + " - " + attribute.getMerit());
			if (meritAux < bestMerit) {
				bestMerit = meritAux;
				bestAttribute = attribute;
			}
			++i;
		}
		System.out.println("-------------------------------------");
		System.out.print("El mejor nodo es: ");
		System.out.println(bestAttribute.getName() + " -> " + bestAttribute.getMerit());
		Node bestNode = new Node(new ArrayList<Node>(), bestAttribute.getPositives(), bestAttribute.getNegatives(), 0, bestAttribute.getMerit(), bestAttribute.getName());

		for (String attribute : bestAttribute.getKeys()) {
			Node nodeAux = new Node(new ArrayList<Node>(), 0, 0, bestAttribute.getA(attribute), bestAttribute.getMerit(attribute), attribute);
			Data list = new Data();
			for (ArrayList<String> d : dataList.getData()) {
				if (d.contains(attribute)) {
					list.add(d);
					if (d.get(d.size() - 1).compareTo(last) == 0) {
						nodeAux.addNegative();
					} 
					else {
						nodeAux.addPositive();
					}
				}
			}
			nodeAux.setNum(nodeAux.getNegatives() + nodeAux.getPositives());
			nodeAux.setData(list);
			bestNode.addChild(nodeAux);
		}
		return bestNode;
	}

	private void recursion(Node bestNode, ArrayList<Attribute> attributes) {
		ArrayList<Attribute> attributesList = new ArrayList<Attribute>();

		if (attributes.size() == 1) {
			for (Node childNode : bestNode.getChild()) {
				if (childNode.getPositives() > 0 && childNode.getNegatives() == 0) {
					childNode.addChild(new Node("SI"));
				} 
				else if (childNode.getNegatives() > 0 && childNode.getPositives() == 0) {
					childNode.addChild(new Node("NO"));
				}
			}
		} else {
			int attrAux = -1;
			for (int i = 0; i < attributes.size(); i++) {
				if (!attributes.get(i).getName().equals(bestNode.getName())) {
					ArrayList<Attribute> attrList = (ArrayList<Attribute>) attributes.clone();
					attributesList.add(attrList.get(i));
				} 
				else {
					attrAux = i;
				}
			}
			for (Node childNode : bestNode.getChild()) {
				if (childNode.getPositives() > 0 && childNode.getNegatives() == 0) {
					childNode.addChild(new Node("SI"));
				} else if (childNode.getNegatives() > 0 && childNode.getPositives() == 0) {
					childNode.addChild(new Node("NO"));
				} else {
					childNode.deleteColumn(attrAux);
					Node best = updateList(attributesList, childNode.getData());
					childNode.addChild(best);
					recursion(best, attributesList);
				}

			}
		}
	}

	public ArrayList<Attribute> getAttributes() { return attributeList; }

}
