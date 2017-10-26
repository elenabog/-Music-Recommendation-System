package otherCode;

import tryGui.UserList;

public class Category {
	
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
	private int numberOfClicks;
	private double nextVal;


	private UserList clicks;
	
	private double[] cDescr=new double[10] ;
	private int [] genInf= new int[3];
	
	public Category(){}
	
	public Category(double duration,double key,double keyConfidence, double loudness, int mode,double modeConfidence,double startOfFadeOut,double tempo,
			double timeSignature,double timeSignatureConfidence,int category,int subCategory,UserList clicks){
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
		this.clicks=clicks;
		}

	public String toString() {
		 return ("Center #"+this.duration+"|"+this.key+"|"+this.keyConfidence+"|"+this.loudness+"|"+this.mode+"|"+this.modeConfidence+"|"+this.startOfFadeOut+"|"+this.tempo+"|"+this.timeSignature+"|"+this.timeSignatureConfidence+"|"+this.category+"|"+this.subCategory);
	}
	
	public double getNextVal(){
		return nextVal;
	}
	
	public double[] getDescriptors() { //descriptors of the center
		cDescr[0] = this.duration;
		cDescr[1] = this.key;
		cDescr[2] = this.keyConfidence;
		cDescr[3] = this.loudness;
		cDescr[4] = this.mode;
		cDescr[5] = this.modeConfidence;
		cDescr[6] = this.startOfFadeOut;
		cDescr[7] = this.tempo;
		cDescr[8] = this.timeSignature;
		cDescr[9] = this.timeSignatureConfidence;
		
		return cDescr;
	}
	
	public int[] getGenCenterInfo(){
		genInf[0]=(int) this.category;
		genInf[1]=(int) this.subCategory;

		return genInf;
	}

}
