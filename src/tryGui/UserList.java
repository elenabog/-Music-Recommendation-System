package tryGui;
import java.awt.EventQueue;
import javafx.scene.Scene;
import javafx.scene.media.*;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.html.HTML;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.portable.ApplicationException;
import otherCode.Category;
import otherCode.ExecutionOfAction;
import otherCode.Node;
import otherCode.ReadAllSongs;
import otherCode.ReadCenters;
import otherCode.ReadSongs;
import otherCode.Songs;
import otherCode.TrSearch;
import otherCode.TreeCreation;
import otherCode.VideoDetails;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Map.Entry;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.application.Platform;

public class UserList extends Application {
	
	 private JFrame frame=new JFrame();
	 private double nextVal;
	 private ReadAllSongs reader= null;
	 private static UserList window;
	 private Songs songs=null;
	 private static ArrayList<Category> centers;
	 private ArrayList<Songs> allSong;
	 private static TreeSet<Node> nodes; 
	 private int category;
	 private int existValue;
	 private String artistName;
	 private int subCategory;
	 private File song;
	 private File center;
	 private JTextField txtTypeAnArtist;
	 private  Node elem;
	 private  ArrayList<Songs> basicPlaylist=new ArrayList<Songs>();
	 private  ArrayList<Songs> guiplayList=new ArrayList<Songs>();
	 private  Songs chosenSong;
	 private String songName;
	 private String artistname;
	 private  JLabel lblNewLabel_1;
	 private  JLabel lblNewLabel ;
	 private  JButton btnNext;
	 private JButton btnVideo;
	 private  long result;
	 private static double reward; 
	 private  int possibleAction;
	 private int previousPossibleAction=0;
	 private  TrSearch treeSearch;
	 private Songs currentsong;
	 private  JPanel panel = new JPanel();
	 private int initializeEpisode;
	 private  final static int Q_SIZE = 101;
	 private static final double GAMMA = 0.95;
	 public static  double q[][] = new double[Q_SIZE][7];
	 private static  int currentState = 0;
	 private  static double alpha = 0.09;
	 private  double epsilon = 0.7;
	 private static double delta=0.01;
	 public static String videoID;
	 public static String currentArtist;
	 private VideoWarning warn=new VideoWarning();
	 public static  boolean videoNoExistance=false;
	 public static boolean noRelatedVideo=false;
	 public static double temp1;
	 private Thread tr;
	 private int flag;
	 private int	 previousCat;
	 private VideoDetails showVideo=new VideoDetails();
   
 @Override
	 public void start(Stage stage) throws Exception {
	  // TODO Auto-generated method stub
	  
	 }
    
    /* Calculates the reward for a specific action and state */
    private static double reward(int action,double nextValOfSong ,int currentState, int previousCat)
    {
    	
	     double temp2;
	     double temp3;
	     
	     temp1=(1-alpha)*(q[previousCat][action]);
	     temp2=  GAMMA * maximum(currentState);
	     temp3=  (alpha*(nextValOfSong+temp2));
	     reward= temp1+temp3;

	      return reward;
    }
    
    /* Iterates the Q , in order to find the maximum action for a specific state */
    private static int maximum(final int state)
    {

    		int winner = 0;
        boolean foundNewWinner = false;
        boolean done = false;

        while(!done){
            foundNewWinner = false;
            for(int i = 0; i < 6; i++){          
                if(i != winner){
                    if(q[state][i] > q[state][winner]){
                        winner = i;
                        foundNewWinner = true;
                    }
                }
            }
            if(foundNewWinner == false){
                done = true;
            }
        }
        return winner;
    }
    
    
 /**
  * Launch the application.
  */
	 public static void main(String[] args) {
		 EventQueue.invokeLater(new Runnable() {
			 public void run() {
				 try {
					 window = new UserList();
					 window.frame.setVisible(true);
					 window.btnNext.setEnabled(false);
					 window.btnVideo.setEnabled(false);
				 } catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
		 });	  
	 }

	 /**
	  * Create the application.
	  */
	 public UserList() {
		  this.reader = new ReadAllSongs();
		  initialize();
	 }

	 /**
	  * Initialize the contents of the frame.
	  */
	 public void initialize() { 
	        
		 btnVideo = new JButton("Not related video");
		 btnVideo.addActionListener(new ActionListener(){
		 NotRelatedVideo sorry= new NotRelatedVideo();
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			noRelatedVideo=true;
			sorry.setModal(true);
			sorry.setVisible(true);	
		}	 
	 	});
		 btnVideo.setForeground(new Color(153, 0, 0));
		 btnVideo.setBackground(new Color(153, 0, 0));
		 btnVideo.setBounds(352, 268, 147, 38);
		 btnVideo.setActionCommand("Not related video");
		 panel.add(btnVideo); 	 
	     btnNext = new JButton("Next");
	     btnNext.addActionListener(new ActionListener()
	     {
	    		int nextIndex=0;
	    	    int currentIndex= 0; //iterate the arraylist of songs in next button
	    	    long lastClick=0;
	    	    double sum= 0.0;
     	    double val=0.0;
	    	    double nextValOfSong=-1;
	        double averageOfReward;
	        ArrayList<Songs> original= new ArrayList<Songs>();
	        ArrayList<Category> centersList= new ArrayList<Category>();
	   	    double[] myCenter=new double[10];
          
	   	    @SuppressWarnings("restriction")
	   	    public void actionPerformed(ActionEvent e) {

	   	    		flag =2;
	   	    		Random randomno = new Random();
	   	    		double p= randomno.nextDouble();
	   	    		int temp=0; 
	   	    		epsilon=getEpsilon();
     
	   	    		if(p>epsilon){
    	 		   
	   	    			previousPossibleAction=getPrevPossibleAction();
	   	    			possibleAction=maximum(getSubCategory()); 
	   	    		}
	   	    		else{
	   	    			// Randomly choose a possible action connected to the current state.
	   	    			previousPossibleAction=getPrevPossibleAction();
	   	    			possibleAction = new Random().nextInt(6);
	   	    		}
	   	    		guiplayList=getTheBasicPlayList();
	   	    		centersList=getTheCenters();
     
	   	    		/* Executes the chosen action */
	   	    		ExecutionOfAction act= new ExecutionOfAction();
	   	    		act.executeTheAction(possibleAction, centersList, UserList.this, treeSearch,nodes);
	   	    		previousCat=act.getPreviousCat();
	   	    		chosenSong=getCurrentSong();   
	   	    		setBasicPlayList(chosenSong);
	   	    		nextIndex = currentIndex+1;
	   	    		nextIndex = guiplayList.size()-1; 
	   	    		/*Displays the title of song and the Artist on gui */
	   	    		lblNewLabel_1.setText(guiplayList.get(nextIndex).getGeneralInfo()[4]);
	   	    		lblNewLabel.setText(guiplayList.get(nextIndex).getGeneralInfo()[5]);
	   	    		currentIndex = nextIndex;
	   	    		long currentClick = System.currentTimeMillis(); 
	   	    		/* calculates the elapsed time between successive press of "Next" */
	   	    		result = currentClick-lastClick;
	   	    		lastClick = currentClick;            
                if(nextIndex>0){
                		guiplayList.get(nextIndex).setFeedBackOfSong();
	                nextValOfSong=guiplayList.get(nextIndex).getNextVal();
	                setNextval(nextValOfSong);
	                /* if video youtube does not respond to the requested one, then don't "blame" the subCategory */
	                if(videoNoExistance==true||noRelatedVideo==true) {
	                		q[previousCat][previousPossibleAction]=0.0;
	                }
	                else {
	                		if(previousCat==initializeEpisode) {
	                			q[previousCat][previousPossibleAction] =reward(previousPossibleAction,0.0, getSubCategory(),previousCat);
	                     }
	                     else {
	                    	 	q[previousCat][previousPossibleAction] =reward(previousPossibleAction,getNextVal(), getSubCategory(),previousCat);
	                     }
	                }
	            		videoNoExistance=false;
                 }    
                setEpsilon(epsilon); //reduce the value of epsilon
                setPrevPossibleAction(possibleAction);         
                printq(previousCat);	
			        /* shows the video youtube*/       
		       videoID = showVideo.getVideoID(chosenSong.getGeneralInfo()[4],chosenSong.getGeneralInfo()[5]);
		       setVideoId(videoID);
		       showVideo.playTheVideo(videoID, UserList.this, warn);
		       noRelatedVideo=false;

   }
  });
	     btnNext.setForeground(new Color(153, 0, 0));
	     btnNext.setBackground(new Color(153, 0, 0));
	     btnNext.setBounds(352, 317, 147, 38);
	     btnNext.setActionCommand("Next");
	     panel.add(btnNext);  
	     frame.setBounds(100, 100, 550, 395);
	     frame.getContentPane().add(panel, BorderLayout.CENTER);
	     frame.setResizable(false);
	     panel.setBackground(Color.BLACK);
	     panel.setLayout(null);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     JLabel lblPlaylist = new JLabel("Playlist");
	     lblPlaylist.setFont(new Font("Lucida Grande", Font.ITALIC, 18));
	     lblPlaylist.setForeground(new Color(153, 0, 0));
	     lblPlaylist.setBounds(188, 22, 71, 16);
	     panel.add(lblPlaylist);
	     txtTypeAnArtist = new JTextField();
	     txtTypeAnArtist.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 artistName=txtTypeAnArtist.getText();
	    		 System.out.println("Artist"+" "+artistName); 
	    		 try {
	    			 TryAgain exist= new TryAgain();
	    			 song= new File("NormalizedData.csv");
	    			 ReadSongs initSong1= new ReadSongs(song.getAbsolutePath());
	    			 songs = initSong1.searchSongs(artistName,UserList.this);
	    			 setcurrentSong(songs);
	    			 setBasicPlayList(songs);
	    			 existValue=initSong1.getExist();
	    			 exist.setModal(true);
	    			 /* The artist name does not exist*/
	    			 if(existValue==0){
		              exist.setVisible(true);
	    			 }  
	    			 if (songs != null) { 
			        txtTypeAnArtist.setVisible(false);
			        btnNext.setEnabled(true);
			        btnVideo.setEnabled(true);
			        category=new Integer(songs.getGeneralInfo()[0]);
			        subCategory=new Integer(songs.getGeneralInfo()[1]);
			        songName=songs.getGeneralInfo()[4];
			        artistName=songs.getGeneralInfo()[5];
			        String display1 = new String();
			        display1 = "Title:"+" "+songName;
			        String display2 = new String();
			        display2 = "Artist:"+" "+artistName;
			        lblNewLabel_1.setText(lblNewLabel_1.getText()+display1);
			        lblNewLabel.setText(lblNewLabel.getText()+display2);
			        
			        /* shows the video youtube*/       
			       videoID=showVideo.getVideoID(songName,artistName);
			       setVideoId(videoID);
				   showVideo.playTheVideo(videoID, UserList.this, warn);

				   String filename = new String();
				   filename = "Centers"+category+".csv";
				   center= new File(filename);
				   ReadCenters initCenter= new ReadCenters(UserList.this,center.getAbsolutePath());
				   centers=initCenter.searchCenters(category,artistName,UserList.this); 

           			// initialize the episode with a random state
        	   			initializeEpisode = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        	   			System.out.println("initializeEpisode with "+initializeEpisode);
        	   			setSubCategory(initializeEpisode);     	           
        	   			allSong=reader.search(category,UserList.this);
        	          
        	           /* Creates the tree */
        	           TreeCreation trCreate= new TreeCreation();
        	           nodes=trCreate.createTheTree(category, allSong, centers, UserList.this);
        	           Node r= trCreate.getRoot();
        	           treeSearch = new TrSearch(r,UserList.this); 
	    			 }
       }catch (IOException e1) {
       e1.printStackTrace();
      }
     }
    });
	     txtTypeAnArtist.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
	     txtTypeAnArtist.setForeground(new Color(178, 34, 34));
	     txtTypeAnArtist.setText("Type the full name of your favorite artist/band & press Enter");
	     txtTypeAnArtist.setBounds(26, 52, 467, 26);
	     txtTypeAnArtist.setColumns(10);
	     panel.add(txtTypeAnArtist);
  
	     /* song's label */
	     lblNewLabel_1 = new JLabel(" ");
	     lblNewLabel_1.setBackground(Color.RED);
	     lblNewLabel_1.setForeground(Color.RED);
	     lblNewLabel_1.setBounds(44, 115, 225, 29);
	     panel.add(lblNewLabel_1);

	     /* Artist's label */
	     lblNewLabel = new JLabel(" ");
	     lblNewLabel.setBackground(Color.RED);
	     lblNewLabel.setForeground(Color.RED);
	     lblNewLabel.setBounds(44, 161, 225, 34);
	     panel.add(lblNewLabel);

	 } 	


	/* Using a thread, we count the time of the playback and if the button next is not pressed, we pressed it automatically */
	 public void makeItNext() {
	  
		  long startTime = System.currentTimeMillis();
		     String dur= showVideo.getDuration(getIdOfVideo());

		    tr = new Thread() {
		         public void run() {
		          int currentDuration = showVideo.durationInSec(dur);
		          
		          while(flag!=1 && flag !=2) {
		                  if((System.currentTimeMillis() - startTime) / 1000 == currentDuration) {
		                	  	flag=1;
		                	  	System.out.print("mphka sto 1o if kai exw flag= "+flag);
		                  
		           } else if ((System.currentTimeMillis() - startTime) / 1000 >= showVideo.durationInSec(dur)){
		             break;
		           }
		          }
		          if(flag ==1 ) {
		              clickButton();
		          }
		         }
		     };
		     flag = 0; 
		     tr.start();   
	 }
	 
	 public void setVideoId(String id){
		 videoID=id;
	 }
	
	 public String getIdOfVideo(){
		 return videoID;
	 }
	 
	 /* Sets the value of an action according to calculations of "reward" function */
	 public void setNextval(double nextVal){
		 	this.nextVal=nextVal;
	 }
	 
	 public double getNextVal(){
		 return nextVal;
	 }
	 
	  public Node getElem(){
		  return elem;
	 }

	 public  TreeSet getNodes(){
	    return nodes;
	 }
	 
	 public  void setcurrentSong(Songs song){
	    currentsong=song;
	 }
	 
	 public Songs getCurrentSong(){
		 return currentsong;
	 }
	  
	  public void setSubCategory(int subcategory){
		  subCategory=subcategory;
	  }
	  
	  public int getSubCategory(){
		  return subCategory;
	  }
  
	  public  void setBasicPlayList(Songs song){
	    basicPlaylist.add(song);
	  }
	  
	 /* If the button "Next" is not pressed manually, then clicked automatically */
	  public void clickButton() {
		  System.out.println("Button clicked programmatically");
		  btnNext.doClick();
	  
	  }
  
	  public  ArrayList getTheBasicPlayList(){
	    return basicPlaylist;
	   }
	  
	  public  ArrayList getTheCenters(){
	   return centers;
	  }
	  
	  /* Update the elapsedTime. ElapsedTime is the time between successive press of the button "Next" */
	  public void setTheElapsedTime(long elapsedTime){
		  this.result=elapsedTime;
	  }
	  
 
	  public long getTheElapsedTime(){
	   return result;
	  }
	  
	  /*Reduce the value of epsilon*/
	  public void setEpsilon(double epsilonOld){
		  if (epsilonOld >= 0.15) {
			  epsilon=(1-delta)*epsilonOld;
		  }
		  else {
			  epsilon=epsilonOld;
		  }
	  }
  
	  public double getEpsilon(){
	   return epsilon;
	  }
	  
	 /* Update the action */
	 public void setPrevPossibleAction(int action) {
		 previousPossibleAction=action;
	 }
	 
	 public int getPrevPossibleAction() {
		 return previousPossibleAction;
	 }
	 
	 public void printq(int currentState){
		 
		    	    for(int i=0; i<6; i++){
		    	    		double n=q[currentState][i];
		    	    		System.out.println("\n q["+currentState+"]["+i+"]" + q[currentState][i]);
		    	    }
	 }
	
	 public void setVideoNoExistance(boolean val) {
		 videoNoExistance=val;
	 }
	 
	 public boolean getvideoNoExistance() {
		 return videoNoExistance;
	 }
}

