package com.restapi.tests;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.apiautomation.constants.AppConfig;
import com.apiautomation.lib.payload.Article;
import com.apiautomation.lib.payload.Authenticate;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ArticleTestsSuite {
	Response userResponse;
	
	HashMap<String, HashMap<String, Object>> articlePayload ;
	HashMap<String,Object> articleInfo;
	Article article ;
	ArrayList<String> taglist;
	
	@BeforeTest
	public void setUp() {
		
		/* Method 1
		 * Authenticate auth1 = new Authenticate();
		 * System.out.println(auth1.AuthenticateUser("guruvshenoy@gmail.com","Passw0rd").asString());		
		 * System.out.println("token is "+AppConfig.TOKEN);
		*/
		
		Authenticate authenticate = new Authenticate();
		HashMap<String, HashMap<String, Object>> userPayload = new HashMap<>();
		HashMap <String,Object> userInfo= new HashMap<>();
		userInfo.put("email", "guruvshenoy@gmail.com");
		userInfo.put("password","Passw0rd");
		userPayload.put("user", userInfo);
		System.out.println(authenticate.AuthenticateUser(userPayload).asString());
		System.out.println("token is "+AppConfig.TOKEN);
	}	
	
	@Test
	public void testCreateNewArticle() {
		article = new Article();
		articleInfo= new HashMap<>();
		articlePayload = new HashMap<>();
		taglist = new ArrayList<>();
		taglist.add("tagged by one");
		taglist.add("tagged by two");
		articleInfo.put("title", "How to train your dragon");
		articleInfo.put("description","Ever wonder how?");
		articleInfo.put("body","Very carefully.");
		articleInfo.put("taglist", taglist);
		articlePayload.put("article", articleInfo);
		JsonPath respAsJson = new JsonPath(article.createArticle(articlePayload).asString());		
		Assert.assertEquals(respAsJson.get("article.description"), "Ever wonder how?");
		System.out.println();
	}
}
