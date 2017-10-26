package otherCode;

import java.util.ArrayList;

import tryGui.UserList;

public class Songs {

	
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
	private double nextVal;
	private UserList clicks;
	private long elapsedTime;
	UserList next= null;
	double[] descr= new double[10];
	String[] generalInfo=new String[10];

	public Songs(){
		
	}
	public Songs(double duration,double key,double keyConfidence, double loudness, int mode,double modeConfidence,double startOfFadeOut,double tempo,
			double timeSignature,double timeSignatureConfidence,int category,int subCategory,String track_id,String audio_md5,String title/*,String artist_id*/,String artist_name,UserList clicks,int cluster){
		this.duration=duration;
		this.key=key;
		this.keyConfidence=keyConfidence;
		this.loudness=loudness;
		this.mode=mode;
		this.modeConfidence=modeConfidence;
		this.startOfFadeOut=startOfFadeOut;
		this.tempo=tempo;
		this.timeSignature=timeSignature;
		this.timeSignatureConfidence=timeSignatureConfidence;
		this.category=category;
		this.subCategory=subCategory;
		this.songClusters=cluster;
        this.track_id = track_id;
        this.audio_md5 = audio_md5;
        this.title =  title;
		this.artist_name=artist_name;
		this.elapsedTime=elapsedTime;
		this.nextVal=nextVal;
		this.clicks=clicks;
	}
	
	public long getElapsedTime(){
		elapsedTime=clicks.getTheElapsedTime();
		return elapsedTime;
	}
	
	public void setFeedBackOfSong(){
		float time=getElapsedTime();
		if(time<13000){ //pressed the button in less of 13 secs
			nextVal= -100.0;
		}else if((time>13000)&&(time<55000)){ //pressed the button between 13 and 55 secs
			nextVal=0;
		}
		else{
			nextVal=+100;
		}
	}
	
	public double getNextVal(){
		return nextVal;
	}
	
	public String toString() {
		 return ("Song #"+this.numberOfSong+"	"+this.duration+"|"+this.key+"|"+this.keyConfidence+"|"+this.loudness+"|"+this.mode+"|"+this.modeConfidence+"|"+this.startOfFadeOut+"|"+this.tempo+"|"+this.timeSignature+"|"+this.timeSignatureConfidence+"|"+this.category+"|"+this.subCategory+"|"+this.songClusters+"|"+this.track_id+"|"+this.audio_md5+"|"+this.title+"|"+this.artist_id+"|"+this.artist_name);
	}
	
	public double[] getDescriptors() { 
		descr[0] = this.duration;
		descr[1] = this.key;
		descr[2] = this.keyConfidence;
		descr[3] = this.loudness;
		descr[4] = this.mode;
		descr[5] = this.modeConfidence;
		descr[6] = this.startOfFadeOut;
		descr[7] = this.tempo;
		descr[8] = this.timeSignature;
		descr[9] = this.timeSignatureConfidence;
		
		return descr;
	}
	
	public String[] getGeneralInfo(){
		generalInfo[0] = Integer.toString(this.category);
		generalInfo[1] = Integer.toString(this.subCategory);
		generalInfo[2] = this.track_id;
		generalInfo[3] = this.audio_md5;
		generalInfo[4] = this.title;
		generalInfo[5] = this.artist_name;
		generalInfo[6] =Integer.toString(this.numberOfSong);
		generalInfo[7] = Double.toString(this.nextVal);
		generalInfo[8] = Integer.toString(this.songClusters);

		return generalInfo;
		
	}
}
