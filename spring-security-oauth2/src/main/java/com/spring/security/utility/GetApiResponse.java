package com.spring.security.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class GetApiResponse {

	public static String getAuthResponseString(String authUrl, String authToken) throws IOException {
		String responseString = "";
		URL url = new URL(authUrl);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setConnectTimeout(120 * 1000);
		con.setReadTimeout(120 * 1000);
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Language", "en-US");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("X-Zeta-AuthToken", authToken);
		con.setDoOutput(true);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			responseString = response.toString();
			
			return responseString;
		}
	}
	
}
