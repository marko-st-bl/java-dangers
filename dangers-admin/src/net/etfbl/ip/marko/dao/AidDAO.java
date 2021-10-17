package net.etfbl.ip.marko.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.etfbl.ip.marko.dto.Aid;

public class AidDAO {
	
	private static final String BASE_URL = "http://localhost:8080/dangers-aid/api/aidservice/aids/";
	
	public List<Aid> getAllAids(){
		List<Aid> retVal = new ArrayList<>();
		try {
			URL url = new URL(BASE_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String output;
			JSONArray result = new JSONArray();
			while ((output = br.readLine()) != null) {
				try {
					result = new JSONArray(output);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			try {
				for (int i=0; i<result.length(); i++) {
					JSONObject jsonAid = result.getJSONObject(i);
					retVal.add(new Aid(jsonAid.getInt("id"), jsonAid.getString("title"), 
							jsonAid.getString("description"), jsonAid.getString("address"), jsonAid.getString("date"), 
							jsonAid.getString("imageUrl"), jsonAid.getString("category"), jsonAid.getBoolean("reportedAsFalse")));
				}
			} catch(JSONException e) {
				e.printStackTrace();
			}
			
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	public void deleteAid(int id) {
		try {
			URL url = new URL(BASE_URL + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
