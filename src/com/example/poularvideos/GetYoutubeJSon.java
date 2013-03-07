package com.example.poularvideos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class GetYoutubeJSon extends AsyncTask<Void, Void, ArrayList<HashMap<String,String>>>{
	private Context context;
	int n;
	public GetYoutubeJSon(Context context){
		this.context=context;
	}

	
	
	@Override
	protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
try{
		
		String content =HttpHelper.getHttpContent(context,Youtube.FEED_URL);
		
		JSONObject jsonResponse;
		
		jsonResponse = new JSONObject(content);
		
		JSONObject feed=jsonResponse.getJSONObject(Youtube.FEED);
		
		JSONArray json =feed.getJSONArray(Youtube.ENTRY); //Array de entrada de videos
		
		n = json.length();
		
		for (int x=0; x<n; x++)
		{
		HashMap<String, String> item = new HashMap<String, String>(); //mapa del video
			
		JSONObject entry = json.getJSONObject(x); //object de la lista de entry
			
		final String imagenurl=entry.getJSONObject(Youtube.MEDIA_GROUP).getJSONArray(Youtube.MEDIA_THUMBNAIL).getJSONObject(4).getString("url");
		String url1 =  imagenurl.toString();
        String url = "http://a0.twimg.com/sticky/default_profile_images/default_profile_1_normal.png";
        imagenurl.toString();
		
		final String author ="by "+ entry.getJSONArray(Youtube.AUTHOR).getJSONObject(0).getJSONObject(Youtube.NAME).getString(Youtube.T);
		final String link = entry.getJSONArray(Youtube.LINK).getJSONObject(0).getString(Youtube.HREF);
		final String title = entry.getJSONObject(Youtube.TITLE).getString(Youtube.T);
		final String views=""+entry.getJSONObject("yt$statistics").getString("viewCount")+" views";
		final String likes=""+entry.getJSONObject("yt$rating").getString("numLikes")+" likes  ";
		
		item.put(Youtube.AUTHOR, author);
		item.put(Youtube.LINK, link);
		item.put(Youtube.TITLE, title);
		item.put("likes", likes);
		item.put("views", views);
		item.put("urlimagen", imagenurl);
		
		/*item.put("Category", entry.getJSONArray("category").getJSONObject(1).getString("label"));
			item.put("Date", entry.getJSONObject("updated").getString("$t"));
			item.put("likes", entry.getJSONObject("yt$rating").getString("numLikes"));
			item.put("views", entry.getJSONObject("yt$statistics").getString("viewCount"));
			
			item.put("urlimagen", entry.getJSONArray("media$thumbnail").getJSONObject(0).getString("url"));
			*/
			result.add(item);
		} }catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		return result;
	}
	
		
	/*	
ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		
		try {
			String content = HttpHelper.getHttpContent(context, Youtube.FEED_URL);

			JSONObject jsonResponse;
			
			jsonResponse = new JSONObject(content);

			JSONObject feed = jsonResponse.getJSONObject(Youtube.FEED);

			final JSONArray json = feed.getJSONArray(Youtube.ENTRY);

			final int n = json.length();

			for (int index = 0; index < n; index++) {
				JSONObject entry = json.getJSONObject(index);
				
				final String author = entry.getJSONArray(Youtube.AUTHOR).getJSONObject(0).getJSONObject(Youtube.NAME).getString(Youtube.T);
				final String link = entry.getJSONArray(Youtube.LINK).getJSONObject(0).getString(Youtube.HREF);
				final String title = entry.getJSONObject(Youtube.TITLE).getString(Youtube.T);
				
				final HashMap<String,String> item = new HashMap<String,String>();
				item.put(Youtube.AUTHOR, author);
				item.put(Youtube.LINK, link);
				item.put(Youtube.TITLE, title);
				
				result.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}*/
}
