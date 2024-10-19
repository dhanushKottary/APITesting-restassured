package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTestDD {

	@Test(priority = 1, dataProviderClass = DataProviders.class, dataProvider = "allData")
	public void testCreateUser(String userID, String username, String fname, String lname, String email, String pwd, String phone) {
		
		
		User userPayload = new User();
		
		userPayload.setId(Integer.valueOf(userID));
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		userPayload.setUsername(username);
		
		System.out.println("testCreateUser: ###########");
		Response response = UserEndpoints.createUser(userPayload);
		
		//log response
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("testCreateUser: ~~~~~~~~~~~");
	}
	
	@Test(priority = 2, dataProviderClass = DataProviders.class, dataProvider = "usernamesData", enabled = false)
	public void testGetUser(String username) {
		
		System.out.println("testGetUser: ###########");
		Response response = UserEndpoints.getUser(username);
		
		//log response
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("testGetUser: ~~~~~~~~~~~");
	}
	
	@Test(priority = 4, dataProviderClass = DataProviders.class, dataProvider = "usernamesData")
	public void testDeleteUser(String username) {
		
		System.out.println("testDeleteUser: ###########");
		
		Response response = UserEndpoints.deleteUser(username);
		Response getUserAfterDeletingUser = UserEndpoints.getUser(username);
		
		//log response
		getUserAfterDeletingUser.then().log().all();		
		
		//log response
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		System.out.println("testDeleteUser: ~~~~~~~~~~~~~");
	}
	
	
}
