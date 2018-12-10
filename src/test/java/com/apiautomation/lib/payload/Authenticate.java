package com.apiautomation.lib.payload;

import java.util.HashMap;

import com.apiautomation.constants.AppConfig;
import com.apiautomation.lib.utils.RestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Authenticate {

	private HashMap <String,Object> user = new HashMap<>();
	private HashMap <String,HashMap <String,Object>> userPayload = new HashMap<>();
	
	public Authenticate(){
		RestUtils.setBaseUrl(AppConfig.BASE_URI, AppConfig.BASE_PATH);
		RestUtils.setHeader("content-type", "application/json");
		RestUtils.setEndpoint("/users/login");
	}
		
	public Response AuthenticateUser(HashMap <String,HashMap <String,Object>> userPayload) {	
		String JSONObject = RestUtils.convertHashToString(userPayload);
		RestUtils.setBody(JSONObject.toString());
		Response resp = RestUtils.makeRequest("/users/login", "POST");
		JsonPath jsonPath = new JsonPath(resp.asString());
		if(resp.statusCode() == 200) {
			AppConfig.TOKEN=jsonPath.get("user.token");
			return resp;
		}
			return null;
	}
	
	public Response AuthenticateUser(String email,String password)  {
		user.put("email", email);
		user.put("password", password);
		userPayload.put("user", user);
		return AuthenticateUser(userPayload);	
	}
}
