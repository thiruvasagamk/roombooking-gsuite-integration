package com.api.testingevents;

import java.util.Collections;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.cloudsearch.v1.CloudSearch;
import com.google.api.services.cloudsearch.v1.CloudSearchScopes;
public class Authorisation {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		CloudSearch service=getCloudSearchAPIService("thiruvasagam@ation.co");
	}
	/** Path to the Service Account's Private Key file */
	private static final String SERVICE_ACCOUNT_FILE_PATH = "D:\\Workspace\\MeetingRoom-Booking\\google-calendar-api-master\\src\\main\\resources\\key.json";  

	/**
	 * Build and return a Cloud Search service object authorized with the service
	 * account that acts on behalf of the given user.
	 *
	 * @param userEmail The email of the user to impersonate. Needs permissions to access Cloud Search.
	 * @return CloudSearch service object that is ready to make requests.
	 */
	public static CloudSearch getCloudSearchAPIService(String userEmail)
	    throws FileNotFoundException, IOException {

	  FileInputStream credsFile = new FileInputStream(SERVICE_ACCOUNT_FILE_PATH);

	  GoogleCredential init = GoogleCredential.fromStream(credsFile);

	  HttpTransport httpTransport = init.getTransport();
	  JsonFactory jsonFactory = init.getJsonFactory();

	  GoogleCredential creds = new GoogleCredential.Builder()
	      .setTransport(httpTransport)
	      .setJsonFactory(jsonFactory)
	      .setServiceAccountId(init.getServiceAccountId())
	      .setServiceAccountPrivateKey(init.getServiceAccountPrivateKey())
	      .setServiceAccountScopes(Collections.singleton(CloudSearchScopes.CLOUD_SEARCH_QUERY))
	      .setServiceAccountUser(userEmail)
	      .build();

	 CloudSearch service = new CloudSearch.Builder(httpTransport, jsonFactory, creds).build();
    
	 return service;
	}
}
