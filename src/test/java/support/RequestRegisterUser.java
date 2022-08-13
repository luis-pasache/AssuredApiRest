package support;

import io.restassured.response.Response;

public class RequestRegisterUser {

    ApiHelper api;
    public static Response respuesta;

    public RequestRegisterUser(){
        api = new ApiHelper();
    }

    public void loguearUsuarioRegistrado(String correo, String pass){
        String url = "https://reqres.in/api/register";
        String loginUser = "{\n" +
                "    \"email\": \""+correo+"\",\n" +
                "    \"password\": \""+pass+"\"\n" +
                "}";
        respuesta = api.post(url,loginUser);
    }
}
