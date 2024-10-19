package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	public static Response createUser(User payload) {
		
		Response response =	given()
							.accept(ContentType.JSON)
							.contentType(ContentType.JSON)
							.body(payload)
							.when()
							.post(Routes.postUrl);
		return response;
	}
	
	public static Response getUser(String username) {
		
		Response response =	given()
							.accept(ContentType.JSON)
							.pathParam("username", username)
							.when()
							.get(Routes.getUrl);
		return response;
	}
	
	public static Response updateUser(String username,User payload) {
		
		Response response =	given()
							.accept(ContentType.JSON)
							.contentType(ContentType.JSON)
							.body(payload)
							.pathParam("username", username)
							.when()
							.put(Routes.updateUrl);
		return response;
	}
	
	public static Response deleteUser(String username) {
		
		Response response =	given()
							.accept(ContentType.JSON)
							.pathParam("username", username)
							.when()
							.delete(Routes.deleteUrl);
		return response;
	}

}
