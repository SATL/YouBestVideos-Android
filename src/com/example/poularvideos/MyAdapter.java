package com.example.poularvideos;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Checkable;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyAdapter extends SimpleAdapter {
	  ArrayList<HashMap<String, String>> map;
	    String[] from;
	    int layout;
	    int[] to;
	    Context context;
	    LayoutInflater mInflater;
	    public MyAdapter(Context context, ArrayList<HashMap<String, String>> lista,
	            int resource, String[] from, int[] to) {
	        super(context, lista, resource, from, to);
	        this.layout = resource;
	        this.map = lista;
	        this.from = from;
	        this.to = to;
	        this.context = context;
	    }


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    return this.createViewFromResource(position, convertView, parent, layout);
	}

	private View createViewFromResource(int position, View convertView,
	        ViewGroup parent, int resource) {
	    View v;
	    if (convertView == null) {
	        v = mInflater.inflate(resource, parent, false);
	    } else {
	        v = convertView;
	    }

	    this.bindView(position, v);

	    return v;
	}


	private void bindView(int position, View view) {
	    final Map dataSet = map.get(position);
	    if (dataSet == null) {
	        return;
	    }

	    final ViewBinder binder = super.getViewBinder();
	    final int count = to.length;

	    for (int i = 0; i < count; i++) {
	        final View v = view.findViewById(to[i]);
	        if (v != null) {
	            final Object data = dataSet.get(from[i]);
	            String text = data == null ? "" : data.toString();
	            if (text == null) {
	                text = "";
	            }

	            boolean bound = false;
	            if (binder != null) {
	                bound = binder.setViewValue(v, data, text);
	            }

	            if (!bound) {
	                if (v instanceof Checkable) {
	                    if (data instanceof Boolean) {
	                        ((Checkable) v).setChecked((Boolean) data);
	                    } else if (v instanceof TextView) {
	                        // Note: keep the instanceof TextView check at the bottom of these
	                        // ifs since a lot of views are TextViews (e.g. CheckBoxes).
	                        setViewText((TextView) v, text);
	                    } else {
	                        throw new IllegalStateException(v.getClass().getName() +
	                                " should be bound to a Boolean, not a " +
	                                (data == null ? "<unknown type>" : data.getClass()));
	                    }
	                } else if (v instanceof TextView) {
	                    // Note: keep the instanceof TextView check at the bottom of these
	                    // ifs since a lot of views are TextViews (e.g. CheckBoxes).
	                    setViewText((TextView) v, text);
	                } else if (v instanceof ImageView) {
	                    if (data instanceof String) {
	                        
	                        setViewImagen((ImageView) v, text);
	                    }
	                } else {
	                    throw new IllegalStateException(v.getClass().getName() + " is not a " +
	                            " view that can be bounds by this SimpleAdapter");
	                }
	            }
	        }
	    }
	}



	
	private void setViewImagen(ImageView v, String value){
		 super.setViewImage(v, value);
		
		 try
         {
             InputStream is = (InputStream) new URL(value).getContent();
             Drawable d = Drawable.createFromStream(is, "src name");
            v.setImageDrawable(d);
         }catch (Exception e) {
             System.out.println("Exc="+e);
             
         }
			
		    
	}


}