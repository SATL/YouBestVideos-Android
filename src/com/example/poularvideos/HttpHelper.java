package com.example.poularvideos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;


public class HttpHelper {
	
	
	private static String buildUserAgent(Context context) {
		try {
			final PackageManager manager = context.getPackageManager();
			final PackageInfo info = manager.getPackageInfo(context
					.getPackageName(), 0);

			return info.packageName + "/" + info.versionName + " ("
					+ info.versionCode + ")";
		} catch (NameNotFoundException e) {
			return null;
		}
	}
	
	
	public static String getHttpContent(Context context, String url) throws ClientProtocolException, IOException

	{
		final HttpParams params = new BasicHttpParams();

		// Use generous timeouts for slow mobile networks
		HttpConnectionParams
				.setConnectionTimeout(params, 20 * 1000);
		HttpConnectionParams.setSoTimeout(params, 20 * 1000);

		HttpConnectionParams.setSocketBufferSize(params, 8192);
		HttpProtocolParams.setUserAgent(params, buildUserAgent(context));

		final DefaultHttpClient client = new DefaultHttpClient(params);

		HttpGet request = new HttpGet(url);
		final HttpResponse resp = client.execute(request);
		
	    final int status = resp.getStatusLine().getStatusCode();
	    
	    if (status != HttpStatus.SC_OK) {
	        throw new IOException("Unexpected server response " + resp.getStatusLine()
	                + " for " + request.getRequestLine());
	    }
	
	    final InputStream input = resp.getEntity().getContent();
		
	    try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
			
	    } catch (IOException e) {
			e.printStackTrace();
        } finally {
            if (input != null) input.close();
        }
	    
		return null;
	}
	}

