package otherCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import tryGui.UserList;

public class TreeCreation {
	
	 private static Node root;
	 private static TreeSet<Node> nodes; 
	 private  Node elem;
	 private ArrayList<Songs> tracks=new ArrayList<Songs>();
	 private  TrSearch treeSearch;


	 public TreeCreation() {
		 this.nodes=new TreeSet<>(new NodeComparator());
	 }
	
	 public TreeSet createTheTree(int parent, ArrayList<Songs> listOfSongs, ArrayList<Category> cents, UserList tree) {	
		 	
		 root= new Node(parent,tree);
                    
		//fill the tree with songs for every node 
        // For each category..
        int idC1;
        for(int t=0; t<cents.size(); t++){
        		idC1=(int) cents.get(t).getGenCenterInfo()[1];
       
            // Create a new (Tree)Node representing the category
            elem= new Node(parent,cents.get(t),idC1,tracks);
            elem.setIdCenter(idC1);
            elem.setNodeOfSong(elem);
            elem.setNodes(elem);
            // For each song... belongs the song in this subcategory?
            for(int s=0; s<listOfSongs.size(); s++){ 
            		int songSub=Integer.parseInt(listOfSongs.get(s).getGeneralInfo()[1]);
                	if (songSub==idC1) {
                     // Center and song have the same subcategory
                		elem.addChild(listOfSongs.get(s), elem,idC1);
                	}
            }
              // Add treenode on TREE
           		nodes.add(elem);
            } 

	 return nodes;

	 }

	public Node getRoot() {
		return root;	
	}
}
/*Sorts the subCategories nodes according to their ids */
class NodeComparator implements Comparator<Node> {
	@Override
	 public int compare(Node node1, Node node2) {
		 
		 int parentCompare = Integer.compare(node1.getIdCenter(), node2.getIdCenter());
		 return parentCompare;
	 } 
}
