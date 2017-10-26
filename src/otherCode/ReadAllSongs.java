package otherCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tryGui.UserList;

public class ReadAllSongs {

	private double duration;
	private double key;
	private double keyConfidence;
	private double loudness;
	private int mode;
	private double modeConfidence;
	private double startOfFadeOut;
	private double tempo;
	private double timeSignature;
	private double timeSignatureConfidence;
	private int category;
	private int subCategory;
	private String title;
	private String artist_id;
	private String track_id;
	private String audio_md5;
	private String artist_name;
	private int numberOfSong;
	private int songClusters;

	ArrayList<Songs> list= new ArrayList<Songs>(10);;
	UserList next= null;

	public ReadAllSongs(){
		this.list= new ArrayList<Songs>();
	}
	
	/*Returns a list with the whole number of songs of the specific Category */
	public ArrayList <Songs> search(int idCategory,UserList clicks) throws IOException{


		String filename = new String();
		filename = idCategory+".csv";		
		File track= new File(filename);
		Songs retsong=null ;
		String[] songparts=new String[19];
		NumberFormat p = NumberFormat.getInstance(Locale.FRANCE);
		FileInputStream fin =  new FileInputStream(track.getAbsolutePath());
		BufferedReader b = new BufferedReader(new InputStreamReader(fin));
		b.readLine();
		String line;
		while((line=b.readLine())!= null)
		{
			songparts = line.split(";");
	
	       try {
		            this.duration = p.parse(songparts[0]).doubleValue();
		            this.key = p.parse(songparts[1]).doubleValue();
		            this.keyConfidence = p.parse(songparts[2]).doubleValue();
		            this.loudness = p.parse(songparts[3]).doubleValue();
		            this.mode = new Integer(songparts[4]);
		            this.modeConfidence = p.parse(songparts[5]).doubleValue();
		            this.startOfFadeOut = p.parse(songparts[6]).doubleValue();
		            this.tempo = p.parse(songparts[7]).doubleValue();
		            this.timeSignature = p.parse(songparts[8]).doubleValue();
		            this.timeSignatureConfidence = p.parse(songparts[9]).doubleValue();
		            this.category = new Integer(songparts[10]);
		            this.subCategory = new Integer(songparts[11]);
		    			this.songClusters=new Integer(songparts[12]);
		            this.track_id = songparts[13];
		            this.audio_md5 = songparts[14];
		            this.title =  songparts[15];
			        this.artist_id = songparts[16];
		    			this.artist_name=songparts[17];

		    		 retsong = new Songs(duration,key,keyConfidence,loudness,mode,modeConfidence,startOfFadeOut,tempo,
		        			timeSignature,timeSignatureConfidence,category,subCategory,track_id, audio_md5,title/*,artist_id*/,artist_name,clicks,songClusters);
			         
		    		 list.add(retsong);
			}
	       
	       catch (ParseException ps) {
	    	   System.out.println(ps.getMessage());
	       }
		}
		return list;
	}
}
