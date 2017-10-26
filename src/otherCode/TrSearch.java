package otherCode;
import java.util.*;
import java.util.Random;

import tryGui.UserList;

import java.util.ArrayList;
import java.util.List;

public class TrSearch{
 

 private int idCSong;
 
 private Node root;
 private double[] chosenSong=new double[10];
 private double[] clusterSong=new double[10];
 private double[] centerDescr=new double[10];
 private double[] secondNeigh=new double[10];
 private Songs chSong;
 private Songs clsong;
 private int subCategoryOfChosenSong;
 private int subCatCluster;
 private String title;
 private String artistOfSong;
 private double songDistance;
 private double centerDistance;
 private double clusterDistance;
 private int idCenter;
 private int idCenter2;
 private int idCategory;
 private int idCategory2;
 private UserList search;
 
 
	 public TrSearch(Node root,UserList search){
		  this.idCategory=idCategory;
		  this.root = root;
		  this.search=search;
	 }
 
	 //find the nearest node
	 public void FindTheTwoNeighbors(int idCategory, ArrayList<Category> centers, double[] myCenter){
		 
		 double minCDist=100000.0;
		 double minCDist2=1000000.0;
		 double[]temp=new double[10];
		    
		 for(int t=0; t<centers.size(); t++){
		    
		   //find the euclidean distance of every node from myCenter
			 centerDescr=centers.get(t).getDescriptors();
			 temp[0]= Math.abs(centerDescr[0]-myCenter[0]);
			 temp[1]= Math.abs(centerDescr[1]-myCenter[1]);
			 temp[2]= Math.abs(centerDescr[2]-myCenter[2]);
			 temp[3]= Math.abs(centerDescr[3]-myCenter[3]);
			 temp[4]= Math.abs(centerDescr[4]-myCenter[4]);
			 temp[5]= Math.abs(centerDescr[5]-myCenter[5]);
			 temp[6]= Math.abs(centerDescr[6]-myCenter[6]);
			 temp[7]= Math.abs(centerDescr[7]-myCenter[7]);
			 temp[8]= Math.abs(centerDescr[8]-myCenter[8]);
			 temp[9]= Math.abs(centerDescr[9]-myCenter[9]);
			 centerDistance= Math.sqrt((temp[0]*temp[0])+(temp[1]*temp[1])+(temp[2]*temp[2])+(temp[3]*temp[3])+(temp[4]*temp[4])+(temp[5]*temp[5])+
		      +(temp[6]*temp[6])+(temp[7]*temp[7])+(temp[8]*temp[8])+(temp[9]*temp[9]));
		   
			 if ( minCDist<minCDist2 && centerDistance < minCDist) {
				 minCDist = centerDistance;
				 idCenter =centers.get(t).getGenCenterInfo()[1];
				 centerDescr=centerDescr;
			 }else if(minCDist2>minCDist && centerDistance>minCDist && centerDistance<minCDist2 ){
				 minCDist2=centerDistance;
				 idCenter2= centers.get(t).getGenCenterInfo()[1];	
				 secondNeigh=centerDescr;
			 }
		   }
	 }
 
	 //find the nearest song to the previous one
	 public Songs FindTheNearestSong(ArrayList<Songs> allSongs,Songs previousSong){
	  
		 double minDist=10000000000.0;	
		 double[]temp=new double[10];
		 double[] prSong=previousSong.getDescriptors();
	
		 for(Songs s:allSongs){ 
		    chosenSong=s.getDescriptors();
		    temp[0]= Math.abs(chosenSong[0]-prSong[0]);
		    temp[1]= Math.abs(chosenSong[1]-prSong[1]);
		    temp[2]= Math.abs(chosenSong[2]-prSong[2]);
		    temp[3]= Math.abs(chosenSong[3]-prSong[3]);
		    temp[4]= Math.abs(chosenSong[4]-prSong[4]);
		    temp[5]= Math.abs(chosenSong[5]-prSong[5]);
		    temp[6]= Math.abs(chosenSong[6]-prSong[6]);
		    temp[7]= Math.abs(chosenSong[7]-prSong[7]);
		    temp[8]= Math.abs(chosenSong[8]-prSong[8]);
		    temp[9]= Math.abs(chosenSong[9]-prSong[9]);
		    songDistance= Math.sqrt((temp[0]*temp[0])+(temp[1]*temp[1])+(temp[2]*temp[2])+(temp[3]*temp[3])+(temp[4]*temp[4])+(temp[5]*temp[5])+
		         +(temp[6]*temp[6])+(temp[7]*temp[7])+(temp[8]*temp[8])+(temp[9]*temp[9]));
		      
		    if (songDistance < minDist) {
		     minDist = songDistance;
		     chSong=s;
		     subCategoryOfChosenSong=Integer.parseInt(s.getGeneralInfo()[1]);
		     subCatCluster=Integer.parseInt(s.getGeneralInfo()[8]);
		     title=s.getGeneralInfo()[4];
		     artistOfSong=s.getGeneralInfo()[5];
		     setTitle(title);
		    }
		 }
	  return chSong;
	 } 
	 
	 //find the nearest song to the previous one, inside the clusters
	 public Songs SearchInsideTheClusters(int clust, ArrayList<Songs> allSongs,Songs prSong){
	   
		  allSongs.remove(prSong); 
		  double minDist=10000000000.0;
		  double[]temp=new double[10];
		  double[] previousSong=prSong.getDescriptors();
		  
		  for(Songs s:allSongs){ 
	
			  int cluster=Integer.parseInt(s.getGeneralInfo()[8]);
			  if(cluster==0 && clust==0){ 
				  	 double[] clusterSong=s.getDescriptors();
				     temp[0]= Math.abs(clusterSong[0]-previousSong[0]);
				     temp[1]= Math.abs(clusterSong[1]-previousSong[1]);
				     temp[2]= Math.abs(clusterSong[2]-previousSong[2]);
				     temp[3]= Math.abs(clusterSong[3]-previousSong[3]);
				     temp[4]= Math.abs(clusterSong[4]-previousSong[4]);
				     temp[5]= Math.abs(clusterSong[5]-previousSong[5]);
				     temp[6]= Math.abs(clusterSong[6]-previousSong[6]);
				     temp[7]= Math.abs(clusterSong[7]-previousSong[7]);
				     temp[8]= Math.abs(clusterSong[8]-previousSong[8]);
				     temp[9]= Math.abs(clusterSong[9]-previousSong[9]);
				     clusterDistance= Math.sqrt((temp[0]*temp[0])+(temp[1]*temp[1])+(temp[2]*temp[2])+(temp[3]*temp[3])+(temp[4]*temp[4])+(temp[5]*temp[5])+
				       +(temp[6]*temp[6])+(temp[7]*temp[7])+(temp[8]*temp[8])+(temp[9]*temp[9]));
				    
				     if (clusterDistance < minDist) {
					      minDist = clusterDistance;
					      clsong=s; 
					      title=s.getGeneralInfo()[4];
					      setTitle(title);
					
				     }
			  }
	       if(cluster==1 && clust==1){ 
			     double[] clusterSong=s.getDescriptors();
			     
			     temp[0]= Math.abs(clusterSong[0]-previousSong[0]);
			     temp[1]= Math.abs(clusterSong[1]-previousSong[1]);
			     temp[2]= Math.abs(clusterSong[2]-previousSong[2]);
			     temp[3]= Math.abs(clusterSong[3]-previousSong[3]);
			     temp[4]= Math.abs(clusterSong[4]-previousSong[4]);
			     temp[5]= Math.abs(clusterSong[5]-previousSong[5]);
			     temp[6]= Math.abs(clusterSong[6]-previousSong[6]);
			     temp[7]= Math.abs(clusterSong[7]-previousSong[7]);
			     temp[8]= Math.abs(clusterSong[8]-previousSong[8]);
			     temp[9]= Math.abs(clusterSong[9]-previousSong[9]);
			     clusterDistance= Math.sqrt((temp[0]*temp[0])+(temp[1]*temp[1])+(temp[2]*temp[2])+(temp[3]*temp[3])+(temp[4]*temp[4])+(temp[5]*temp[5])+
			       +(temp[6]*temp[6])+(temp[7]*temp[7])+(temp[8]*temp[8])+(temp[9]*temp[9]));
			    
			     if (clusterDistance < minDist) {
				      minDist = clusterDistance;
				      clsong=s;
				      title=s.getGeneralInfo()[4];
				      setTitle(title);			
			     }
	        }
	       	if(cluster==2 && clust==2){ 
		        double[] clusterSong=s.getDescriptors();
			     temp[0]= Math.abs(clusterSong[0]-previousSong[0]);
			     temp[1]= Math.abs(clusterSong[1]-previousSong[1]);
			     temp[2]= Math.abs(clusterSong[2]-previousSong[2]);
			     temp[3]= Math.abs(clusterSong[3]-previousSong[3]);
			     temp[4]= Math.abs(clusterSong[4]-previousSong[4]);
			     temp[5]= Math.abs(clusterSong[5]-previousSong[5]);
			     temp[6]= Math.abs(clusterSong[6]-previousSong[6]);
			     temp[7]= Math.abs(clusterSong[7]-previousSong[7]);
			     temp[8]= Math.abs(clusterSong[8]-previousSong[8]);
			     temp[9]= Math.abs(clusterSong[9]-previousSong[9]);
			     clusterDistance= Math.sqrt((temp[0]*temp[0])+(temp[1]*temp[1])+(temp[2]*temp[2])+(temp[3]*temp[3])+(temp[4]*temp[4])+(temp[5]*temp[5])+
			       +(temp[6]*temp[6])+(temp[7]*temp[7])+(temp[8]*temp[8])+(temp[9]*temp[9]));
			    
			     if (clusterDistance < minDist) {
				      minDist = clusterDistance;
				      clsong=s;
				      title=s.getGeneralInfo()[4];
				      setTitle(title);				
			     }
		        }
		       if(cluster==3 && clust==3){ 
			        double[] clusterSong=s.getDescriptors();
			        temp[0]= Math.abs(clusterSong[0]-previousSong[0]);
			        temp[1]= Math.abs(clusterSong[1]-previousSong[1]);
			        temp[2]= Math.abs(clusterSong[2]-previousSong[2]);
			        temp[3]= Math.abs(clusterSong[3]-previousSong[3]);
			        temp[4]= Math.abs(clusterSong[4]-previousSong[4]);
			        temp[5]= Math.abs(clusterSong[5]-previousSong[5]);
			        temp[6]= Math.abs(clusterSong[6]-previousSong[6]);
			        temp[7]= Math.abs(clusterSong[7]-previousSong[7]);
			        temp[8]= Math.abs(clusterSong[8]-previousSong[8]);
			        temp[9]= Math.abs(clusterSong[9]-previousSong[9]);
			        clusterDistance= Math.sqrt((temp[0]*temp[0])+(temp[1]*temp[1])+(temp[2]*temp[2])+(temp[3]*temp[3])+(temp[4]*temp[4])+(temp[5]*temp[5])+
			        		+(temp[6]*temp[6])+(temp[7]*temp[7])+(temp[8]*temp[8])+(temp[9]*temp[9]));
			    
			        if (clusterDistance < minDist) {
				        	minDist = clusterDistance;
				        	clsong=s;
				        	title=s.getGeneralInfo()[4];
				        	setTitle(title);
			     }
		        }	       
	    }
	    return clsong;
	 }
   
   public double[] getTheFirstNeighbor(){
	   return centerDescr;
   }
   
   
   public double[] getTheSectNeighbor(){
	   return secondNeigh;
   }  
   
   public int getIdCenter(){
	   return idCenter;
   }
   
   public int getIdCenter2(){
	   return idCenter2;
   }
   
   public double[] getChosenSong(){
	   return chosenSong;
   }
   
   public int getTheSubCOfChSong(){
	   return subCategoryOfChosenSong;
   }
   
   public int getTheSubCluster(){
	   return subCatCluster;
   }
   
   public void setTitle(String title){
	   this.title=title;
   }
   
   public String getTheTitle(){
	   return title;
   }
   
   public String getTheArtist(){
	   return artistOfSong;
   }
   
}


