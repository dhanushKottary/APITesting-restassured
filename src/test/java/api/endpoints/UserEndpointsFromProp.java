package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.Properties;
import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpointsFromProp {

	
	static ResourceBundle getURL() {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Routes");//Loads Routes.properties file
		return resourceBundle;
	}
	
	public static Response createUser(User payload) {
		
		String postUrl = getURL().getString("postUrl");
		Response response =	given()
							.accept(ContentType.JSON)
							.contentType(ContentType.JSON)
							.body(payload)
							.when()
							.post(postUrl);
		return response;
	}
	
	public static Response getUser(String username) {
		
		String getUrl = getURL().getString("getUrl");
		Response response =	given()
							.accept(ContentType.JSON)
							.pathParam("username", username)
							.when()
							.get(Routes.getUrl);
		return response;
	}
	
	public static Response updateUser(String username,User payload) {
		
		String updateUrl = getURL().getString("updateUrl");
		Response response =	given()
							.accept(ContentType.JSON)
							.contentType(ContentType.JSON)
							.body(payload)
							.pathParam("username", username)
							.when()
							.put(updateUrl);
		return response;
	}
	
	public static Response deleteUser(String username) {
		
		String deleteUrl = getURL().getString("deleteUrl");
		Response response =	given()
							.accept(ContentType.JSON)
							.pathParam("username", username)
							.when()
							.delete(deleteUrl);
		return response;
	}

}
