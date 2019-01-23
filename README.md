This maven java project is created using google Rest Assured. It has library which makes rest calls, constants which is the place holder constants like base path,base uri and endpoint information, example tests.
The code is in tests folder i.e. 

https://github.com/gurudattvshenoy/RestAssured/tree/master/src/test/java/com/apiautomation

Files under Constants directory

AppConfig.java - This contains all the constants required by the application like base url, base path, user credentials and JWT token generated after login.

EndPoint.java - This contains endpoints information. Maintaining in the class makes it easy to  change in one place if the endpoint is changed in the future without having to change in test scripts.

Files in the payload directory

Authenticate.java and Article.java file have implementations to make calls to authenticate and also to create articles.

This can be changed to have a separate models for each kind of payload where 

public class Article {
    private String title ;
    private String description;
    Private ArrayList <String> tagList;
    public String getTitle() { return this.title; }
    public void   setDescription(String description){ this.description = description;}
 ….    
}

Convert string to JSON.
String articlePayload = "{ \”title\”:\”Toyota\", \”description\”:”this is best car” }";

Article article = objectMapper.readValue(articlePayload, Article.class);


RestUtils.java
This contains all the methods required for making REST Call i.e setting headers, payload, making GET,PUT,POST, DELETE rest calls.
