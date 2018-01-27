package apps;

import java.io.IOException;
import java.util.ArrayList;

import structures.Graph;

public class driver {

	public static void main(String args[]) throws IOException{
		Graph a = new Graph("graph1.txt");
		System.out.println("the graph is");
		a.print();
		PartialTreeList c = new PartialTreeList();
		c=MST.initialize(a);
		ArrayList<PartialTree.Arc> d = MST.execute(c);
		System.out.println("the arraylist is:");
		System.out.println(d);
	}
}
