package com.apiautomation.lib.payload;

import java.util.HashMap;

import com.apiautomation.constants.AppConfig;
import com.apiautomation.lib.utils.RestUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Article{
	
	/* Sample Payload
		{"article":{"title":"How to train your dragon", 
		"description":"Ever wonder how?", "body":"Very carefully.", 
		"tagList":["dragons","training"]}}
	 */
	private HashMap<String,HashMap<String,Object>> article = new HashMap<>();
	
	public Article() {
		RestUtils.setBaseUrl(AppConfig.BASE_URI, AppConfig.BASE_PATH);
		RestUtils.setHeader("content-type", "application/json");
		RestUtils.setHeader("X-Requested-With","XMLHttpRequest");
		RestUtils.setHeader("Authorization","Token "+AppConfig.TOKEN);
		//RestUtils.setEndpoint("/articles");
	}
	
	public Response createArticle(HashMap<String,HashMap<String,Object>> article) {
		String JSONObject = RestUtils.convertHashToString(article);
		RestUtils.setBody(JSONObject.toString());
		System.out.println(JSONObject.toString());
		Response resp = RestUtils.makeRequest("/articles", "POST");
		JsonPath jsonPath = new JsonPath(resp.asString());
		if(resp.statusCode() == 200) {
			AppConfig.TOKEN=jsonPath.get("user.token");
			return resp;
		}
			return null;
	}
		
}
	
