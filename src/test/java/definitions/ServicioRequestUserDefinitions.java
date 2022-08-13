package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.RequestUser;

import java.util.List;
import java.util.Map;

public class ServicioRequestUserDefinitions {

    RequestUser user;

    public ServicioRequestUserDefinitions(){
        user = new RequestUser();
    }

    @Given("obtener usuarios")
    public void listarUsuarios() {
        user.obtenerUsuarios();
    }

    @When("mostrar el listado de usuarios")
    public void mostrarElListadoDeUsuarios() {
        ResponseBody<Response> body = RequestUser.respuesta;
        System.out.println(body.asString());
    }

    @Then("validar el numero de usuarios")
    public void validarElNumeroDeUsuarios() {
        ResponseBody<Response> body = RequestUser.respuesta;
        JsonPath json = new JsonPath(body.asString());
        List<String> listaUsuarios = JsonPath.with(body.asString()).get("data");
        int cantidad = json.getInt("per_page");
        Assert.assertEquals(cantidad, listaUsuarios.size());
    }

    @Given("obtener un usuario por id {string}")
    public void listarUnUsuarioPorId(String id) {
        user.obtenerUnUsuario(id);
    }

    @When("mostrar al usuario")
    public void mostrarAlUsuario() {
        mostrarElListadoDeUsuarios();
    }

    @And("validar el codigo de respuesta {string}")
    public void validarElCodigoDeRespuesta(String codigo) {
        Assert.assertEquals(Integer.parseInt(codigo), RequestUser.respuesta.getStatusCode());
        System.out.println("Codigo de respuesta: "+RequestUser.respuesta.getStatusCode());
    }

    @Then("validar la informacion de usuario")
    public void validarLaInformacionDeUsuario(DataTable dt) {
        ResponseBody<Response> body = RequestUser.respuesta;
        JsonPath json = new JsonPath(body.asString()).setRootPath("data");
        List<Map<String, String>> data = dt.asMaps(String.class,String.class);
        for (int i = 0; i < data.size(); i++){
            Assert.assertEquals(data.get(i).get("email"), json.getString("email"));
            Assert.assertEquals(data.get(i).get("nombre"), json.getString("first_name"));
            Assert.assertEquals(data.get(i).get("apellido"), json.getString("last_name"));
        }
    }

    @Given("no existe usuario registrado")
    public void noExisteUsuarioRegistrado() {
    }

    @When("registrar datos del usuario")
    public void registrarDatosDelUsuario(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class,String.class);
        for (int i = 0; i < data.size(); i++){
            user.registrarUsuario(data.get(i).get("nombre"),data.get(i).get("puesto"));
            validarElCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }
    }

    @Then("mostrar el registro")
    public void mostrarElRegistro() {
        mostrarElListadoDeUsuarios();
    }

    @Given("que el usuario este registrado")
    public void queElUsuarioEsteRegistrado() {
    }

    @When("actualizar datos del usuario")
    public void actualizarDatosDelUsuario(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class,String.class);
        for (int i = 0; i < data.size(); i++){
            user.actualizarUsuarioPut(data.get(i).get("id"),data.get(i).get("nombre"),data.get(i).get("puesto"));
            validarElCodigoDeRespuesta(data.get(i).get("codigo"));
            mostrarElListadoDeUsuarios();
        }
    }
}
