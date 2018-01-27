package apps;

import structures.*;
import java.util.ArrayList;

import apps.PartialTree.Arc;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList InitialTree = new PartialTreeList();
		int size = graph.vertices.length;
		for (int i = 0; i < size; i++){
			Vertex vertex = graph.vertices[i];
			PartialTree newTree = new PartialTree(vertex);
			Vertex.Neighbor nextPQ  = vertex.neighbors;
			while(nextPQ!=null){
				
				PartialTree.Arc newArc = new PartialTree.Arc(vertex,nextPQ.vertex,nextPQ.weight);
				newTree.getArcs().insert(newArc);
				nextPQ = nextPQ.next;
			}
			InitialTree.append(newTree);
			
		}
		
		/* COMPLETE THIS METHOD */
		
		return InitialTree;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		ArrayList<PartialTree.Arc> aArc = new ArrayList<PartialTree.Arc>();
		if(ptlist==null){
			return null;
		}
		else if(ptlist.size()==1){
			aArc.add(ptlist.remove().getArcs().deleteMin());
		}
		else {
			while(ptlist.size()>1){
				
			int size = ptlist.size();
		 PartialTree Rtree = ptlist.remove();
		 MinHeap<Arc> arclist = Rtree.getArcs();
		 Arc PriArc = arclist.deleteMin();
		 Vertex root = Rtree.getRoot();
		 Vertex Rroot = PriArc.v2.getRoot();
		 while(root.equals(PriArc.v2.getRoot())){
			PriArc=arclist.deleteMin();
			 
			 /*if(!root.name.equals(PriArc.v2.name)){
				 root = root.parent;
			 }
			 else{
				
				 PriArc = arclist.deleteMin();
				 root= Rtree.getRoot();
			 }*/
		 }
		 aArc.add(PriArc);
		 PartialTree Mtree = ptlist.removeTreeContaining(PriArc.v2);
		 Rtree.merge(Mtree);
		 ptlist.append(Rtree);
		}
		}
		/* COMPLETE THIS METHOD */
		
		return aArc ;
	}
}
