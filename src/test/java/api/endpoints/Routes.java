package api.endpoints;

public class Routes {
	
	/*Create user	POST	https://petstore.swagger.io/v2/user
Get user using username	GET	https://petstore.swagger.io/v2/user/{username}
Update user	PUT	https://petstore.swagger.io/v2/user/{username}
Delete user	DELETE	https://petstore.swagger.io/v2/user/{username}
*/

	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	//User module URLs
	public static String postUrl = baseUrl+"/user";
	public static String getUrl = baseUrl+"/user/{username}";
	public static String updateUrl = baseUrl+"/user/{username}";
	public static String deleteUrl = baseUrl+"/user/{username}";
	
	//Pet module URLs
	
	//Store module URLs
}
