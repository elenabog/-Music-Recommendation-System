package otherCode;

import java.util.ArrayList;
import java.util.TreeSet;

import tryGui.UserList;

public class Node {
	
	private UserList treeset=null;
	private int idCategory;
	private int idSongs;
	private int idCenter;
	private Node centerofSong;
	private int root;
	private Category center;
	private ArrayList<Category> centerList=new ArrayList<Category>();
	private ArrayList<Node> nodes=new ArrayList<Node>();
	private ArrayList<Songs> songs;

	 
	public Node(int idCategory,UserList treeset){
		this.root=idCategory;
		this.treeset=treeset;

	}
	
	public Node(int idCategory,Category center,int idCenter,ArrayList<Songs> song){
		
		this.root=idCategory;	
		this.center=center;
		this.idCenter=idCenter;
		this.songs=new ArrayList<Songs>();
		centerList.add(center);

	}
	
	public boolean getCategoryNode(int subc) {
		if (this.idCenter == subc)
			return true;
		else
			return false;
	}


    public void addChild(Songs child,Node center,int idc) {
    		center.setNodeOfSong(center);
    		center.setIdCenter(idc);
        this.songs.add(child);
    }
    
	public void setNodeOfSong(Node center){
		this.centerofSong=center;
	}
	
	public Node getNodeOfSong(){
		return centerofSong;
	}
		
	public ArrayList getSong(){
		return songs;
	}
	
	public int getRoot(){
		return root;
	}
	
	public ArrayList getCenterList(){
		return centerList;
	}
	
	public Category getCenter(){
		return center;
	}
	
	public void setIdCenter(int idCenter){
		this.idCenter=idCenter;
	}
	
	public int getIdCenter(){
		return idCenter;
	}
	
	public void setNodes(Node n){
		this.nodes.add(n);
	}
}
