package com.example.poularvideos;



import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

@SuppressLint("NewApi")
public class MainActivity extends ListActivity {

	
	
	ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
	SimpleAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_PROGRESS);
		
		setContentView(R.layout.activity_main);
		//setTitle(R.string.title);
		

        final String[] from = {"title", "author", "views", "likes", "urlimagen"};
        final int[] to = { R.id.Titulo, R.id.Autor, R.id.Views, R.id.Categoria, R.id.imagen };
        
		mAdapter = new MyAdapter(this, lista, R.layout.feeds_item, from , to);
		
		setListAdapter(mAdapter);
		

		new DownloadData().execute();
		
		
		
		}
	

	protected void onListItemClick(ListView l, View v, int position, long id) {
		final HashMap<String, String> video = lista.get(position);
		final Uri uri = Uri.parse(video.get(Youtube.LINK));
		
		final Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(uri);
		
		startActivity(intent);
	}
		
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@SuppressLint("NewApi")
	private class DownloadData extends GetYoutubeJSon{
		
		public DownloadData() {
			super(MainActivity.this);
			// TODO Auto-generated constructor stub
		}




		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			setProgressBarIndeterminate(true);
			setProgressBarVisibility(true);
		}
			
			
		
		
		protected void onPostExecute(ArrayList<HashMap<String, String>> result){
			super.onPostExecute(result);
			
			lista.clear();
			lista.addAll(result);
			mAdapter.notifyDataSetChanged();
			setProgressBarVisibility(false);
		}
		
	}
	
	
	
	
		

		
	}
	


