package com.infosys.iFlow;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * 
 * @author sateeshkumar.putta Added content to eGIT v1.1 changes
 *
 */
public class FlowGeneration {

	public static void main(String[] args) throws IOException {

		String baseUri = "https://dbdcab63trial.it-cpitrial02.cfapps.eu10-001.hana.ondemand.com/api/v1/";
		String userId = "sateeshp437@gmail.com";
		String password = "S@teesh7";
		String plainCredentials = userId + ":" + password;
		String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
		
		String packageName = "PackageWithJavaCode3"; //CS_Name
		String packageID = "PackageWithJavaCode3"; // CS_Name
		String iFlowName = "CPIiFlowDeployment4"; // IFlow_NAME
		String iFLowID = "CPIiFlowDeployment4";  //IFlow_NAME
		String iFlowArtifactFileName = "CPIiFlowDeployment.zip";
		
		
		boolean createFlow = true;

		String csrfToken = FlowGeneration.getCSRFToken(baseUri, base64Credentials);
		System.out.println(csrfToken);

		String createPackageResponse = FlowGeneration.createCPIPackage(baseUri, base64Credentials,
				csrfToken.split(",")[1], csrfToken.split(",")[0],packageID , packageName, "PackageWithJavaCode1", "PackageWithJavaCode1");
		System.out.println("\n\n Package Creation: \n"+createPackageResponse);
		
		if(createFlow) {
		
		String createFlowResponse = FlowGeneration.createIFlow(baseUri, base64Credentials,
				csrfToken.split(",")[1], csrfToken.split(",")[0], iFlowName, iFLowID, packageID,iFlowArtifactFileName);
		System.out.println("\n\n Flow Creation \n"+createFlowResponse);
		}
		else {
		
		String updateFlowResponse = FlowGeneration.updateIFlow(baseUri, base64Credentials,
				csrfToken.split(",")[1], csrfToken.split(",")[0], iFLowID, iFlowName, iFlowArtifactFileName);
		System.out.println("\n\n Update Integration Flow: \n"+updateFlowResponse);
		}
		
		String resourceTypeList = "xsd,wsdl,mmap";
		String resourceNameList = "NDFDgenByDayRequest.mt.xsd,GlobalWeather.wsdl,MM_Global_Weather_Request.mmap";
		
		for(int i=0; i< resourceTypeList.split(",").length;i++) {
			
		String resourceType = 	resourceTypeList.split(",")[i];
		String resourceName = 	resourceNameList.split(",")[i];
			
		String addResourcesResponse = FlowGeneration.addResources(baseUri, base64Credentials, csrfToken.split(",")[1], csrfToken.split(",")[0], resourceType,resourceName ,"base64ResourceContent", iFLowID);
		System.out.println("\n\n Resource: \n\n"+addResourcesResponse);	
		
		}	
		
	}
	
	/**
	 * 
	 * @param baseUri
	 * @param base64Credentials
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */

	public static String getCSRFToken(String baseUri, String base64Credentials)
			throws ClientProtocolException, IOException {

		String response = "";
		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet get = new HttpGet(baseUri);
			get.addHeader("x-csrf-token", "Fetch");
			get.addHeader("Authorization", "Basic " + base64Credentials);

			CloseableHttpResponse httpResponse = httpclient.execute(get);
			//System.out.println(httpResponse.toString());

			Header[] headers = httpResponse.getAllHeaders();

			for (int i = 0; i < headers.length; i++) {
				Header header = headers[i];
				// System.out.println(header.getName() + " : " + header.getValue());

				if (header.getName().equalsIgnoreCase("set-cookie")) {
					response = header.getValue();
					System.out.println("Cookie is " + header.getValue());
				}
				if (header.getName().equalsIgnoreCase("x-csrf-token")) {
					response = response + "," + header.getValue();
				}

			}

			return response;
		} catch (Exception ex) {

			return "Exception";

		}

	}

	/**
	 * 
	 * @param baseUri
	 * @param base64Credentials
	 * @param csrfToken
	 * @param setCookie
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String createCPIPackage(String baseUri, String base64Credentials, String csrfToken, String setCookie, String packageID, String packageName, String packageDescription, String shortText)
			throws ClientProtocolException, IOException {

		String response = null;
		try {

			String requestBody = "{\r\n  \"Id\": \"%s\",\r\n  \"Name\": \"%s\",\r\n  \"Description\": \"%s\",\r\n  \"ShortText\": \"%s\",\r\n  \"Version\": \"1.0\",\r\n  \"SupportedPlatform\": \"SAP Cloud Integration\",\r\n  \"Products\": \"SAP\",\r\n  \"Keywords\": \"SAP API\",\r\n  \"Countries\": \"\",\r\n  \"Industries\": \"\",\r\n  \"LineOfBusiness\": \"\"\r\n}";

			requestBody = String.format(requestBody, packageID,packageName,packageDescription,shortText);
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(baseUri + "IntegrationPackages");
			post.addHeader("X-CSRF-Token", csrfToken);
			post.addHeader("Cookie", setCookie);
			post.addHeader("Authorization", "Basic " + base64Credentials);
			post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
			post.setEntity(new StringEntity(requestBody));

			CloseableHttpResponse httpResponse = httpclient.execute(post);
			response = EntityUtils.toString(httpResponse.getEntity());

			return response;
		} catch (Exception ex) {

			return "Exception";

		}

	}

	/**
	 * 
	 * @param baseUri
	 * @param base64Credentials
	 * @param csrfToken
	 * @param setCookie
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String createIFlow(String baseUri, String base64Credentials, String csrfToken, String setCookie, String iFLowName, String iFLowID, String packageId, String artifactFileName)
			throws ClientProtocolException, IOException {

		String response = null;
		try {
			
			byte[] fileContent = Files.readAllBytes(Paths.get(artifactFileName));
			String base64ArtifactContent = Base64.getEncoder().encodeToString(fileContent);
			String requestBody = "";

			if (artifactFileName == "" || artifactFileName == null) {
				requestBody = "{\r\n  \"Name\": \"%s\",\r\n  \"Id\": \"%s\",\r\n  \"PackageId\": \"%s\"\r\n}";
				requestBody = String.format(requestBody, iFLowName,iFLowID,packageId);
			}
			else {
			
				requestBody = "{\r\n  \"Name\": \"%s\",\r\n  \"Id\": \"%s\",\r\n  \"PackageId\": \"%s\",\r\n  \"ArtifactContent\": \"%s\"\r\n}";

				requestBody = String.format(requestBody, iFLowName,iFLowID,packageId,base64ArtifactContent);
			}
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(baseUri + "IntegrationDesigntimeArtifacts");
			post.addHeader("X-CSRF-Token", csrfToken);
			post.addHeader("Cookie", setCookie);
			post.addHeader("Authorization", "Basic " + base64Credentials);
			post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
			post.setEntity(new StringEntity(requestBody));

			CloseableHttpResponse httpResponse = httpclient.execute(post);
			response = EntityUtils.toString(httpResponse.getEntity());
			
			Files.deleteIfExists(Paths.get(artifactFileName));
			
			return response;
		} catch (Exception ex) {

			return "Exception";

		}

	}
/**
 * 
 * @param baseUri
 * @param base64Credentials
 * @param csrfToken
 * @param setCookie
 * @return
 * @throws ClientProtocolException
 * @throws IOException
 */
	public static String updateIFlow(String baseUri, String base64Credentials, String csrfToken, String setCookie, String iFlowID, String iFlowName, String artifactFileName)
			throws ClientProtocolException, IOException {

		String response = null;
		try {
			
			byte[] fileContent = Files.readAllBytes(Paths.get(artifactFileName));
			String base64ArtifactContent = Base64.getEncoder().encodeToString(fileContent);

			String requestBody = "{\r\n  \"Name\": \"%s\",\r\n  \"ArtifactContent\": \"%s\"\r\n}";
			
			requestBody = String.format(requestBody, iFlowName, base64ArtifactContent);			
						
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPut put = new HttpPut(baseUri + "IntegrationDesigntimeArtifacts(Id='"+iFlowID+"',Version='active')");
			put.addHeader("X-CSRF-Token", csrfToken);
			put.addHeader("Cookie", setCookie);
			put.addHeader("Authorization", "Basic " + base64Credentials);
			put.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
			put.setEntity(new StringEntity(requestBody));

			CloseableHttpResponse httpResponse = httpclient.execute(put);
			response = EntityUtils.toString(httpResponse.getEntity());			
			Files.deleteIfExists(Paths.get(artifactFileName));
			
			return response;
		} catch (Exception ex) {

			return "Exception";

		}

	}
	
/**
 * 
 * @param baseUri
 * @param base64Credentials
 * @param csrfToken
 * @param setCookie
 * @param resourceType
 * @param resourceName
 * @return
 * @throws ClientProtocolException
 * @throws IOException
 */
	
	public static String addResources(String baseUri, String base64Credentials, String csrfToken, String setCookie, String resourceType, String resourceName, String base64ResourceContent, String iFlowID)
			throws ClientProtocolException, IOException {

		String response = null;
		try {
			
			byte[] fileContent = Files.readAllBytes(Paths.get(resourceName));
			base64ResourceContent = Base64.getEncoder().encodeToString(fileContent);
			

			//String requestBody = "{\r\n  \"Name\": \"FlowWithJavaCode\",\r\n  \"Id\": \"FlowWithJavaCode\",\r\n  \"PackageId\": \"PackageWithJavaCOde\"\r\n}";
			
			//Upload Artifact
			String requestBody = "{\r\n    \"Name\": \"%s\",\r\n    \"ResourceType\": \"%s\",\r\n    \"ResourceContent\": \"%s\"\r\n}";
			
			requestBody = String.format(requestBody, resourceName,resourceType,base64ResourceContent);
			
			//System.out.println("\n\n"+requestBody);
			

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(baseUri + "IntegrationDesigntimeArtifacts(Id='"+iFlowID+"',Version='active')/Resources");
			post.addHeader("X-CSRF-Token", csrfToken);
			post.addHeader("Cookie", setCookie);
			post.addHeader("Authorization", "Basic " + base64Credentials);
			post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
			post.setEntity(new StringEntity(requestBody));

			CloseableHttpResponse httpResponse = httpclient.execute(post);
			response = EntityUtils.toString(httpResponse.getEntity());

			return response;
		} catch (Exception ex) {

			return ex.toString();

		}

	}

	
	
	
}
