// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package View;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import Auxiliary.Attribute;
import Auxiliary.Node;

public class MainView  extends JFrame{

	private Node tree;
	private ArrayList<Attribute> attributeList;
	private mxGraph mxGraph;
	private mxIGraphLayout mxIGraphLayout;
	private mxGraphComponent mxGraphComponent;
	private JPanel panelTree;
	
	public MainView(ArrayList<Attribute> attributeList, Node tree){
		this.tree = tree;
		this.attributeList = attributeList;
		mxGraph= new mxGraph();
		
		setTitle("Algoritmo ID3");
		setSize(550, 400);
		setLocationRelativeTo(null);

		paintTree(tree, mxGraph, null);

		mxIGraphLayout = new mxHierarchicalLayout(mxGraph);
		mxIGraphLayout.execute(mxGraph.getDefaultParent());

		mxGraphComponent = new mxGraphComponent(mxGraph);
		mxGraphComponent.setBorder(null);

		panelTree = new JPanel();
		panelTree.add(mxGraphComponent);
		
		this.add(panelTree);
		this.setVisible(true);
	}

	private void paintTree(Node parentNode, mxGraph mxGraph, Object parentVertex){
		Object vertex1;

		if(parentVertex != null){
			vertex1 = parentVertex;
		}
		else {
			vertex1 = mxGraph.insertVertex(mxGraph.getDefaultParent(), null, parentNode.getName(), 0, 0, 90, 60, "strokeColor=black;fillColor=lightgreen");
		}
		for(Node vertexUnion: parentNode.getChild()){
			for(Node vertex: vertexUnion.getChild()) {
				Object vertex2 = mxGraph.insertVertex(mxGraph.getDefaultParent(), null, vertex.getName(), 0, 0, 90, 60, "strokeColor=black;fillColor=lightgreen");
				mxGraph.insertEdge(mxGraph.getDefaultParent(), null, vertexUnion.getName(), vertex1, vertex2, "strokeColor=black");
				if(vertex.getChild().size() > 0) {
					paintTree(vertex, mxGraph, vertex2);
				}
			}
		}
	}
	
}
