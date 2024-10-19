package api.testcases;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpointsFromProp;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	Logger logger;
	
	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new User();
		logger = LogManager.getLogger("UserTest");
		
		logger.info("Generating data....");
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUsername(faker.name().username());
		
	}
	
	@Test(priority = 1)
	public void testCreateUser() {
		
		System.out.println("testCreateUser: ###########");
		Response response = UserEndpointsFromProp.createUser(userPayload);
		
		//log response
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("testCreateUser: ~~~~~~~~~~~");
		logger.info("testCreateUser: Testcase passed!!");
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		
		System.out.println("testGetUser: ###########");
		Response response = UserEndpointsFromProp.getUser(this.userPayload.getUsername());
		
		//log response
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("testGetUser: ~~~~~~~~~~~");
		logger.info("testGetUser: Testcase passed!!");
		//throw new SkipException("The testcase is being skipped");
		
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		
		System.out.println("testUpdateUser: ###########");
		
		this.userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndpointsFromProp.updateUser(this.userPayload.getUsername(), this.userPayload);
		Response getUpdatedUserResponse = UserEndpointsFromProp.getUser(this.userPayload.getUsername());
		
		//log response
		getUpdatedUserResponse.then().log().all();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println("testUpdateUser: ~~~~~~~~~~~~~");
		logger.debug("testUpdateUser: Testcase passed!!");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		
		System.out.println("testDeleteUser: ###########");
		
		Response response = UserEndpointsFromProp.deleteUser(userPayload.getUsername());
		Response getDeletedUserResponse = UserEndpointsFromProp.getUser(this.userPayload.getUsername());
		
		//log response
		getDeletedUserResponse.then().log().all();		
		
		//log response
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println("testDeleteUser: ~~~~~~~~~~~~~");
		logger.warn("testDeleteUser: Testcase passed!!");
	}

}
