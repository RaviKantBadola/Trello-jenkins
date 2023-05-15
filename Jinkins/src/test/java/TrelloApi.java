import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TrelloApi {
//I am going to test all the end-points of Trello
//I want to create the base us
	public String baseUrl="https://api.trello.com";
	public static String key = "bc4f5a896dba589efd9137b549e78296";
    public static String token ="ATTA984d4ebde3acb19987e03f54d6c20aca907713d6e8853536dfeee121b59d7659C5B21F1B";			
	public static String id;
	
	 public static String requestBody = "{ \"name\": \"IronMan\", \"desc\": \"trust the process\" }";;
	 public static String requestBody1="{\r\n"
	 		+ "                \"name\":Iron man          \r\n"
	 		+ "            }";
    @Test(priority = 0)
    public void CreateBoard() {
    	//Rest-Assured we work with the below aspect
    	//given:request-contains, body,headers-authorization-content-type, quarry parameter
    	//When: resource / board (this will have only resources)
    	//then: response-assertion - String format -- jsonpath
    	
    	//this is to pass my base url 
    	
    	RestAssured.baseURI=baseUrl;
    	//i have to pre-condition 
    Response  response =	given().queryParam("name","ravi")
    	.queryParam("key", key)
    	.queryParam("token",token)
    	.header("Content-Type","application/json")
    	.when()
    	.post("/1/boards")
    	.then()
    	.assertThat().statusCode(200).contentType(ContentType.JSON)
    	.extract().response();
     String jsonresponse = response.asString();
     System.out.println(jsonresponse);
     
   JsonPath   js = new JsonPath(jsonresponse);
     id=js.get("id");
    }
    
    @Test(priority = 1)
    public void updateBoard() {
    	RestAssured.baseURI=baseUrl;
    	
    	Response response = given()
    			.queryParam("key", key)
    	    	.queryParam("token",token)
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/1/boards/{id}",id)
                .then()
                .assertThat().statusCode(200).contentType(ContentType.JSON)
            	.extract().response();
    	String jsonresponse = response.asString();
        System.out.println(jsonresponse);
      
      
    }
    @Test(priority = 2)
    public void getBoard() {
    	
    	RestAssured.baseURI=baseUrl;
    	
    	Response response = given()
    			.queryParam("key", key)
    	    	.queryParam("token",token)
                .header("Content-type", "application/json")
                .when()
                .get("/1/boards/{id}",id)
                .then()
                .assertThat().statusCode(200).contentType(ContentType.JSON)
            	.extract().response();
    	 String jsonresponse = response.asString();
         System.out.println(jsonresponse);
        
    
    }
    @Test(priority = 3)
    public void DeleteBoard() {
    RestAssured.baseURI=baseUrl;
    	
    	Response response = given()
    			.queryParam("key", key)
    	    	.queryParam("token",token)
                .header("Content-type", "application/json")
                .when()
                .delete("/1/boards/{id}",id)
                .then()
                .assertThat().statusCode(200).contentType(ContentType.JSON)
            	.extract().response();
    	 String jsonresponse = response.asString();
         System.out.println(jsonresponse);
    }
   
}
