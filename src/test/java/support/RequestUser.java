package support;

import io.restassured.response.Response;
import objects.DatosRegistroUsuarioPost;

public class RequestUser {

    ApiHelper api;
    //DatosRegistroUsuarioPost registro;
    public static Response respuesta;

    public RequestUser(){
        api = new ApiHelper();
    }

    public void obtenerUsuarios(){
        String url = "https://reqres.in/api/users";
        respuesta = api.get(url);
    }

    public void obtenerUnUsuario(String id){
        String url = "https://reqres.in/api/users/"+id;
        respuesta = api.get(url);
    }

    public void registrarUsuario(String nombre, String puesto){
        String url = "https://reqres.in/api/users";
        /* no me esta funcionando el jackson databind por eso lo hago de manera manual
        registro = new DatosRegistroUsuarioPost(nombre, puesto);*/
        String registrarUser = "{\n" +
                "    \"name\": \""+nombre+"\",\n" +
                "    \"job\": \""+puesto+"\"\n" +
                "}";
        respuesta = api.post(url, registrarUser);
    }

    public void actualizarUsuarioPut(String id, String nombre, String puesto){
        String url = "https://reqres.in/api/users/"+id;
        String updateUserPut = "{\n" +
                "    \"name\": \""+nombre+"\",\n" +
                "    \"job\": \""+puesto+"\"\n" +
                "}";
        respuesta = api.put(url,updateUserPut);
    }

    public void actualizarUsuarioPatch(String id, String nombre, String puesto){
        String url = "https://reqres.in/api/users/"+id;
        String updateUserPatch = "{\n" +
                "    \"name\": \""+nombre+"\",\n" +
                "    \"job\": \""+puesto+"\"\n" +
                "}";
        respuesta = api.patch(url,updateUserPatch);
    }
}
