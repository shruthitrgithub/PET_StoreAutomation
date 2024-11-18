 package api.endPoints;
import api.payload.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;



//UserEndPoints.java
// Created Perform CRUD Operations

public class UserEndPoints {

	public static Response createUser (User payload)
	{
Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);

return response;
	}
	
	public static Response readUser (String userName)
	{
Response response =given()
.pathParam("username", userName)
		.contentType(ContentType.JSON)
		
		.when()
		.get(Routes.get_url);
return response;
	}
	

	public static Response UpdateUser (String userName,User payload)
	{
		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(Routes.update_url);

		return response;
	}		
	public static Response deleteUser (String userName)
	{
		Response response =given()
		.pathParam("username",userName)
		.when()
		.delete(Routes.delete_url);

return response;
	}
	
	
}





















