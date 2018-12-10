package com.apiautomation.lib.utils;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {
	final static Logger logger = Logger.getLogger(RestUtils.class);
	public static RequestSpecBuilder rspecBuilder =new RequestSpecBuilder();
	public static RequestSpecification requestSpec;
	public static HashMap <String,String> body= new HashMap<String,String>();
	public static String endPoint;
	
	public static void setBaseUrl(String baseUri,String basePath) {
		logger.info("Base Uri "+baseUri);
		logger.info("Base path "+basePath);
		rspecBuilder.setBaseUri(baseUri);
		rspecBuilder.setBasePath(basePath);	
	}
	
	public static void setHeader(String name,String value) {
		logger.info("Header Name:"+name);
		logger.info("Header Value:"+value);
		rspecBuilder.addHeader(name, value);
	}
	
	public static void setHeader(HashMap<String,String> header) {
		rspecBuilder.addHeaders(header);
	}
	
	public static void setPathParam(String name,String value) {
		rspecBuilder.addPathParam(name, value);
	}
	
	public static void setPathParams(HashMap<String,String> pathParams) {
		rspecBuilder.addPathParams(pathParams);	
	}
	
	public static void setBody(Object body) {
		System.out.println("BPDY is"+body.toString());
		rspecBuilder.setBody(body);
	}
	
	public  static void setEndpoint(String ep) {
		endPoint = ep;
	}
	
	public static Response makeRequest(String endpoint,String restMethod) {	
		System.out.println("Endpoint= "+endpoint+" rest method ="+restMethod);
		Response response = null;
		requestSpec = rspecBuilder.build();
		restMethod = restMethod.toLowerCase();
		switch(restMethod) {
			case "get":
				response = given().spec(requestSpec).get(endPoint);
			break;
			case "post":
				response = given().spec(requestSpec).post(endpoint);
			break;
			case "put":
				response = given().spec(requestSpec).put(endPoint);
			break;
			case "delete":
				response = given().spec(requestSpec).delete(endPoint);
			break;
			default:
				System.out.println("Not supported methods");
		}
		response.then().log().all();
		return response;
	}
	
	public JsonPath getJsonPath(Response res) {
		String path = res.asString();
		return new JsonPath(path);
	}
	
	public static String objectToString(Object obj) {
		
		String jsonStr=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
             jsonStr = mapper.writeValueAsString(obj);        
        } catch (IOException e) {
            e.printStackTrace();
        }
		return jsonStr;
	}
	
	public static String convertHashToString(HashMap<String,HashMap <String,Object>> userPayload) {
		GsonBuilder gsonMapBuilder = new GsonBuilder();
		Gson gsonObject = gsonMapBuilder.create(); 
		String JSONObject = gsonObject.toJson(userPayload);
		return JSONObject;
	}
}

