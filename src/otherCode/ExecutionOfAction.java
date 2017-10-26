package otherCode;

import java.util.ArrayList;
import java.util.TreeSet;

import tryGui.UserList;

public class ExecutionOfAction {
	
	private int currentState = 0;
	private Songs chosenSong;
	private 	int previousCat;

	
	public ExecutionOfAction() {}

	 public void executeTheAction(int possibleAction, ArrayList<Category>centersList, UserList act, TrSearch treeSearch, TreeSet<Node> nodes) {
		 
	 	    ArrayList<Songs> original= new ArrayList<Songs>();
	 	    double[] myCenter=new double[10];
	 	    
	 	    
			 if(possibleAction==0){
		         
				 previousCat=act.getSubCategory();
		         double valueOfSong=act.getNextVal();
		         
		         for(int h=0; h<centersList.size(); h++){
		        	 	if(centersList.get(h).getGenCenterInfo()[1]==act.getSubCategory()){
			        	  	myCenter=centersList.get(h).getDescriptors();
			        	  	centersList.remove(centersList.get(h)); 
			        	 }
		         }  
		         treeSearch.FindTheTwoNeighbors(act.getSubCategory(),centersList,myCenter); 
		         currentState=treeSearch.getIdCenter();
		         act.setSubCategory(currentState);
		         for (Node element: nodes) {
		                    if((int)(element.getCenter().getGenCenterInfo()[1])==act.getSubCategory()){  
		                     original=element.getSong();//exei ta song tou current subCategory
		                    }
		                }
		         chosenSong=treeSearch.FindTheNearestSong(original, act.getCurrentSong());
		         act.setcurrentSong(chosenSong);
		         original.remove(chosenSong);
		        }
		        if(possibleAction==1){
		               
		        		double valueOfSong=act.getNextVal();
		            previousCat=act.getSubCategory();
		           for(int h=0; h<centersList.size(); h++){
		        	   		if(centersList.get(h).getGenCenterInfo()[1]==act.getSubCategory()){
		        	   			myCenter=centersList.get(h).getDescriptors();
		        	   			centersList.remove(centersList.get(h)); //afairw apo ta kentra to current gia na mh mou bgalei to idio ws geitona
		        	   		}
		           }  
		           treeSearch.FindTheTwoNeighbors(act.getSubCategory(),centersList,myCenter); 
		           currentState=treeSearch.getIdCenter2();
		           act.setSubCategory(currentState);  
		           for (Node element: nodes) {
		        	   		if((int)(element.getCenter().getGenCenterInfo()[1])==act.getSubCategory()){ 
		        	   			original=element.getSong();//exei ta song tou current subCategory
		                 }
		           }
		           chosenSong=treeSearch.FindTheNearestSong(original, act.getCurrentSong());
		           act.setcurrentSong(chosenSong);
		           original.remove(chosenSong);
		        }
		        if(possibleAction==2){
		              
		        		previousCat=act.getSubCategory();
		            act.setSubCategory(act.getSubCategory());
		            for (Node element: nodes) {
		            		if((int)(element.getCenter().getGenCenterInfo()[1])==act.getSubCategory()){ 
		            			original=element.getSong();//exei ta song tou current subCategory
		                 }
		            }   
		            chosenSong=treeSearch.SearchInsideTheClusters(0, original, act.getCurrentSong());  
		            act.setcurrentSong(chosenSong);
		            original.remove(chosenSong);
		         }
		         if(possibleAction==3){
		               
		        	 	previousCat=act.getSubCategory();
		            act.setSubCategory(act.getSubCategory());
		            for (Node element: nodes) {
		            		if((int)(element.getCenter().getGenCenterInfo()[1])==act.getSubCategory()){  
		            			original=element.getSong();//exei ta song tou current subCategory
		                 }
		            }
		            chosenSong=treeSearch.SearchInsideTheClusters(1, original, act.getCurrentSong());
		            act.setcurrentSong(chosenSong);   
		            original.remove(chosenSong);
		          }
		         if(possibleAction==4){
			               
		        	 	previousCat=act.getSubCategory();
			        act.setSubCategory(act.getSubCategory());
			        for (Node element: nodes) {
			        		if((int)(element.getCenter().getGenCenterInfo()[1])==act.getSubCategory()){ 
			        			original=element.getSong();//exei ta song tou current subCategory
			             }
			         }               
			         chosenSong=treeSearch.SearchInsideTheClusters(2, original,act.getCurrentSong());
			         act.setcurrentSong(chosenSong);
			         original.remove(chosenSong);
		          }
		          if(possibleAction==5){
		        	  		previousCat=act.getSubCategory();
		        	  		act.setSubCategory(act.getSubCategory());
		               for (Node element: nodes) {
		                     if((int)(element.getCenter().getGenCenterInfo()[1])==act.getSubCategory()){
		                      original=element.getSong();//exei ta song tou current subCategory
		                     }
		                 }
		               chosenSong=treeSearch.SearchInsideTheClusters(3, original, act.getCurrentSong());
		               act.setcurrentSong(chosenSong);
		               original.remove(chosenSong);

		              }
		 }
	 
	 public int getPreviousCat() {
		 return previousCat;
	 }
}
