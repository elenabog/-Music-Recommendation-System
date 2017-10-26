package otherCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import tryGui.UserList;


public class ReadSongs {
	
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
	private String song;  
	
	 int exist; 

	
	public ReadSongs(String f){
		this.song=f;
	}
	
	public Songs searchSongs(String artistName,UserList clicks)throws IOException{

		Songs retsong=null ;
        String[] songparts=null;
        NumberFormat f = NumberFormat.getInstance(Locale.FRANCE);		
        FileInputStream fin =  new FileInputStream(song);
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        br.readLine();
        String grammh; 
        int i=0;

    		while((grammh=br.readLine())!= null)
    		{
		    songparts = grammh.split(";");
		    try {
		    	if(songparts[16].equalsIgnoreCase(artistName)){
			            this.duration = f.parse(songparts[0]).doubleValue();
			            this.key = f.parse(songparts[1]).doubleValue();
			            this.keyConfidence = f.parse(songparts[2]).doubleValue();
			            this.loudness = f.parse(songparts[3]).doubleValue();
			            this.mode = new Integer(songparts[4]);
			            this.modeConfidence = f.parse(songparts[5]).doubleValue();
			            this.startOfFadeOut = f.parse(songparts[6]).doubleValue();
			            this.tempo = f.parse(songparts[7]).doubleValue();
			            this.timeSignature = f.parse(songparts[8]).doubleValue();
			            this.timeSignatureConfidence = f.parse(songparts[9]).doubleValue();
			            this.category = new Integer(songparts[10]);
			            this.subCategory = new Integer(songparts[11]);
			            this.songClusters=new Integer(songparts[12]);
			            this.track_id = songparts[13];
			            this.audio_md5 = songparts[14];
			            this.title =  songparts[15];
			            this.artist_name=songparts[16];
	
			            retsong = new Songs(duration,key,keyConfidence,loudness,mode,modeConfidence,startOfFadeOut,tempo, 
			        			timeSignature,timeSignatureConfidence,category,subCategory,track_id, audio_md5,title/*,artist_id*/,artist_name,clicks,songClusters);
			            exist=1;
		        	}
    		}
        catch (ParseException ps) {
        		System.out.println(ps.getMessage());
        }
    	}
        	return retsong;
	}

	public int getExist(){
		return exist;
	}
}
