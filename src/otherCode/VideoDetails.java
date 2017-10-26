package otherCode;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.Duration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tryGui.UserList;
import tryGui.VideoWarning;

public class VideoDetails {
	
	 private JSONObject result1;
	 private JSONObject id;
	 private JSONObject durat;
	 private JSONObject result3;
	 private JSONObject result2;
	 private JSONArray jsonItems;
	 private JSONArray jsonItem;
	 private JSONObject details;
	
	public VideoDetails() {}
	
	 public String makeHttpRequest(URL url) {
		  String jsonResponse = null;
		        HttpURLConnection urlConnection = null;
		        InputStream inputStream = null;
		        try {
		          urlConnection = (HttpURLConnection) url.openConnection();
		          urlConnection.connect();
		          inputStream = urlConnection.getInputStream();
		          jsonResponse = readFromStream(inputStream);
		        } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		        }
		     return jsonResponse;
		    }

		 /* Convert to String format the data of the Json file*/
		 public String readFromStream(InputStream inputStream) {
		     StringBuilder output = new StringBuilder();
		     if (inputStream != null) {
		         InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
		         BufferedReader reader = new BufferedReader(inputStreamReader);
		         String line;
		   try {
		    line = reader.readLine();
		           while (line != null) {
		               output.append(line);
		               line = reader.readLine();
		           }
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		     }
		     return output.toString();
		 }
	
	/* Retrieves the videoId of youtube video from Json file*/
	 public String getVideoID(String songName, String artist){
		 try {
			 String test = makeHttpRequest(new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q="+URLEncoder.encode(songName+" "+artist,"UTF-8")+"&type=video&key=AIzaSyDaEE5DpEdHsTGgH2x0OeOhaxUOa46mlZU"));
			  id = new JSONObject(test);
			  jsonItems = id.getJSONArray("items");
			  result1 = jsonItems.getJSONObject(0);
			  result2 = result1.getJSONObject("id");
			  return result2.getString("videoId");
			  } catch (JSONException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  } catch (MalformedURLException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  } catch (UnsupportedEncodingException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  return null;
	 }
	 
	/* Takes the duration of youtube video by reading the Json file*/
	 public String getDuration (String videoId) {
		 
		 String takeTheDuration;
		 try {
			 takeTheDuration = makeHttpRequest(new URL("https://www.googleapis.com/youtube/v3/videos?id="+videoId+"&key=AIzaSyDaEE5DpEdHsTGgH2x0OeOhaxUOa46mlZU&part=snippet,contentDetails,statistics,status"   ));  	 
			 result3 = new JSONObject(takeTheDuration);     
		    jsonItem = result3.getJSONArray("items");
		    durat = jsonItem.getJSONObject(0);
		    details = durat.getJSONObject("contentDetails");
		    //System.out.println("duration is "+result2.getString("duration"));
		     durationInSec(details.getString("duration"));
		     return details.getString("duration");
			 
			 } catch (MalformedURLException | JSONException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			 }
		    return null;
	}

	 public int durationInSec(String duration) {
		 return (int) Duration.parse(duration).getSeconds();
	} 

	 public void playTheVideo(String vidId, UserList vid,VideoWarning warn ) {
	        if(vidId!=null) {
	      	   Desktop d= Desktop.getDesktop();
	 	            try {
	 	            	d.browse(new URI("https://www.youtube.com/embed/" + vidId));
	 	            getDuration(vidId);
	 	            } catch (IOException e1) {
	 	            		e1.printStackTrace();
	 	            } catch (URISyntaxException e1) {
	 	            		e1.printStackTrace();
	 	            }
	         }
	         else {
	     	   	
	      		vid.setVideoNoExistance(true);
	      		warn.setModal(true);
	      		warn.setVisible(true);
	         }

	         vid.makeItNext();
		}
}
