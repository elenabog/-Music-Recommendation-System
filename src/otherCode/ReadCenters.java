package otherCode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import tryGui.UserList;

import java.util.List;
import java.util.ListIterator;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


public class ReadCenters {
	
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
	private String center;
	
	private Category centers= null;
	private ArrayList<Category> centersList=new ArrayList<Category>();


	//constructor
	public ReadCenters(UserList object, String f){
		this.center=f;
	}
	String[] centerParts;

		
	public ArrayList<Category> searchCenters(/*String center, */int idCategory,String chosenArtist,UserList object)throws IOException{

		String grammh; 
		FileInputStream fin =  new FileInputStream(center);
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		br.readLine();
        NumberFormat f = NumberFormat.getInstance(Locale.FRANCE);   
		
        while((grammh=br.readLine())!= null)
    		{
    			centerParts = grammh.split(";");
		    try {  	   
		    		this.duration = f.parse(centerParts[0]).doubleValue();
		        this.key = f.parse(centerParts[1]).doubleValue();
		        this.keyConfidence = f.parse(centerParts[2]).doubleValue();
				this.loudness = f.parse(centerParts[3]).doubleValue();
				this.mode = new Integer(centerParts[4]);
				this.modeConfidence = f.parse(centerParts[5]).doubleValue();
		        	this.startOfFadeOut = f.parse(centerParts[6]).doubleValue();
		        	this.tempo = f.parse(centerParts[7]).doubleValue();
				this.timeSignature = f.parse(centerParts[8]).doubleValue();
				this.timeSignatureConfidence = f.parse(centerParts[9]).doubleValue();
				this.subCategory = new Integer(centerParts[10]);
				this.category = new Integer(centerParts[11]);
				centers=new Category(duration,key,keyConfidence,loudness,mode,modeConfidence,startOfFadeOut,tempo,timeSignature,
								timeSignatureConfidence,category,subCategory,object);
	          centersList.add(centers);		          			          
			}
		    catch (ParseException ps) {
		    		System.out.println(ps.getMessage());
			           }
    		}
       	return centersList;

	}
}
