package support;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public Response get(String url){
        Response respuestaGet = given().get(url);
        return respuestaGet;
    }

    public Response post(String url, Object json){
        Response respuestaPost = given().body(json).contentType("application/json").post(url);
        return respuestaPost;
    }

    public Response put(String url, Object json){
        Response respuestaPut = given().body(json).contentType("application/json").put(url);
        return respuestaPut;
    }

    public Response patch(String url, Object json){
        Response respuestaPatch = given().body(json).contentType("application/json").patch(url);
        return respuestaPatch;
    }
}
