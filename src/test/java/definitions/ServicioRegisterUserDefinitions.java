package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.RequestRegisterUser;

import java.util.List;
import java.util.Map;

public class ServicioRegisterUserDefinitions {

    RequestRegisterUser register;

    public ServicioRegisterUserDefinitions(){
        register = new RequestRegisterUser();
    }
    @Given("usuario que no existe")
    public void usuarioQueNoExiste() {
    }

    @When("registrar datos del usuario con correo")
    public void registrarDatosDelUsuarioConCorreo(DataTable dt) {
        List<Map<String, String>> data = dt.asMaps(String.class,String.class);
        for (int i = 0; i < data.size(); i++){
            register.loguearUsuarioRegistrado(data.get(i).get("email"),data.get(i).get("pass"));
        }
    }

    @And("validar el codigo de respuesta exitoso {string}")
    public void validarElCodigoDeRespuestaExitoso(String codigo) {
        Assert.assertEquals(Integer.parseInt(codigo), RequestRegisterUser.respuesta.getStatusCode());
    }

    @Then("mostrar el registro guardado")
    public void mostrarElRegistroGuardado() {
        ResponseBody<Response> body = RequestRegisterUser.respuesta;
        System.out.println(body.asString());
    }
}
