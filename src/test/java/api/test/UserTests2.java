package api.test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndPoints;
import api.endPoints.UserEndPoints2;
import api.payload.User;

public class UserTests2 {

	Faker faker;
	User userPayload;
	
	//public Logger logger;
	
	@BeforeClass
public void Setup()	
{
	faker = new Faker();
	userPayload = new User();
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPassword(faker.internet().password(5,10));
    userPayload.setPhone(faker.phoneNumber().cellPhone());	

    //logger = LogManager.getLogger(this.getClass());
}
@Test(priority=1)

public void testPostUser() 
{
	//logger.info("**************  Creating User ****************");
	Response response=UserEndPoints2.createUser(userPayload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(),200);
//	logger.info("************** User is Created ****************");
}
@Test(priority=2)
public void testGetUserByName()
{
	///logger.info("************** Reading User info ****************");
	Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
	response.then().log().all();
Assert.assertEquals(response.getStatusCode(), 200);	
//logger.info("************* User info is Displayed ****************");
}
@Test (priority=3)
public void testUpdateUserbyName() {
	{
// update data using payload take few data to update
//we are not Updating the UserName as we are sending it as pathParameter		
		
	//	logger.info("************** Updating User  ****************");	
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.UpdateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		//logger.info("**************  User is Updated ****************");	
	//checking the data after update
		
		Response responseAfterUpdate =UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);		
		
		
	}
}
@Test(priority=4)

public void testDeleteUserbyName() 
{
	//logger.info("************** Deleting User ****************");
	Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
	Assert.assertEquals(response.getStatusCode(),200);
	//logger.info("************** User Deleted ****************");
}

}




















