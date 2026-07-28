package com.automation.stepdefinitions.api;

import com.automation.api.interactions.users.CreateUserAccount;
import com.automation.api.interactions.users.DeleteUserAccount;
import com.automation.api.interactions.users.GetUserDetailByEmail;
import com.automation.api.interactions.users.UpdateUserAccount;
import com.automation.api.models.UserAccountRequest;
import com.automation.api.utils.data.UserTestDataGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserManagementStepDefs {

    private UserAccountRequest currentUserData;

    @And("se han generado datos para un nuevo usuario")
    public void seHanGeneradoDatosParaUnNuevoUsuario() {
        // Usar Faker para crear un usuario 100% dinámico
        currentUserData = UserTestDataGenerator.randomUser();
    }

    @When("crea la nueva cuenta de usuario")
    public void creaLaNuevaCuentaDeUsuario() {
        theActorInTheSpotlight().attemptsTo(
                CreateUserAccount.withData(currentUserData)
        );
    }

    @When("consulta los detalles del usuario por email")
    public void consultaLosDetallesDelUsuarioPorEmail() {
        theActorInTheSpotlight().attemptsTo(
                GetUserDetailByEmail.withEmail(currentUserData.getEmail())
        );
    }

    @And("los detalles del usuario coinciden con el email registrado")
    public void losDetallesDelUsuarioCoincidenConElEmailRegistrado() {
        // En AutomationExercise el endpoint de user details envuelve el objeto en "user"
        String body = net.serenitybdd.rest.SerenityRest.lastResponse().asString();
        String emailFromResponse = new io.restassured.path.json.JsonPath(body).getString("user.email");
        org.junit.Assert.assertThat("El email del usuario retornado no coincide", emailFromResponse, org.hamcrest.Matchers.equalTo(currentUserData.getEmail()));
    }

    @When("actualiza los datos de la cuenta")
    public void actualizaLosDatosDeLaCuenta() {
        // Actualizamos solo el nombre para no afectar credenciales ni email
        currentUserData = currentUserData.toBuilder()
                .name("Updated Serenity")
                .build();
        theActorInTheSpotlight().attemptsTo(
                com.automation.api.interactions.users.UpdateUserAccount.withData(currentUserData)
        );
    }

    @When("elimina la cuenta de usuario")
    public void eliminaLaCuentaDeUsuario() {
        theActorInTheSpotlight().attemptsTo(
                DeleteUserAccount.withCredentials(currentUserData.getEmail(), currentUserData.getPassword())
        );
    }

    @And("intenta crear la cuenta de nuevo con el mismo email")
    public void intentaCrearLaCuentaDeNuevoConElMismoEmail() {
        theActorInTheSpotlight().attemptsTo(
                CreateUserAccount.withData(currentUserData)
        );
    }

    @When("intenta crear cuenta sin parámetros requeridos")
    public void intentaCrearCuentaSinParametrosRequeridos() {
        theActorInTheSpotlight().attemptsTo(
                net.serenitybdd.screenplay.rest.interactions.Post.to(com.automation.api.config.ApiEndpoints.CREATE_ACCOUNT)
                        .with(request -> request
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .body("name=TestUserOnly"))
        );
    }

    @When("envía una petición GET a crear cuenta")
    public void enviaUnaPeticionGETACrearCuenta() {
        theActorInTheSpotlight().attemptsTo(
                com.automation.api.interactions.common.GetResource.from(com.automation.api.config.ApiEndpoints.CREATE_ACCOUNT)
        );
    }

    @When("intenta actualizar cuenta sin parámetro email")
    public void intentaActualizarCuentaSinParametroEmail() {
        theActorInTheSpotlight().attemptsTo(
                net.serenitybdd.screenplay.rest.interactions.Put.to(com.automation.api.config.ApiEndpoints.UPDATE_ACCOUNT)
                        .with(request -> request
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .body("name=UpdatedNameOnly"))
        );
    }

    @When("intenta actualizar una cuenta inexistente")
    public void intentaActualizarUnaCuentaInexistente() {
        com.automation.api.models.UserAccountRequest fakeUser = com.automation.api.utils.data.UserTestDataGenerator.randomUserWithEmailAndPassword("no_existo_serenity@test.com", "pass123");
        theActorInTheSpotlight().attemptsTo(
                UpdateUserAccount.withData(fakeUser)
        );
    }

    @When("consulta los detalles del usuario con email inexistente")
    public void consultaLosDetallesDelUsuarioConEmailInexistente() {
        theActorInTheSpotlight().attemptsTo(
                GetUserDetailByEmail.withEmail("no_existo_serenity@test.com")
        );
    }

    @When("consulta los detalles del usuario omitiendo el email")
    public void consultaLosDetallesDelUsuarioOmitiendoElEmail() {
        theActorInTheSpotlight().attemptsTo(
                com.automation.api.interactions.common.GetResource.from(com.automation.api.config.ApiEndpoints.GET_USER_DETAIL)
        );
    }

    @When("envía una petición POST a obtener detalles")
    public void enviaUnaPeticionPOSTAObtenerDetalles() {
        theActorInTheSpotlight().attemptsTo(
                com.automation.api.interactions.common.PostToResource.at(com.automation.api.config.ApiEndpoints.GET_USER_DETAIL)
        );
    }
}
