package com.automation.stepdefinitions.api;

import com.automation.api.config.ApiEndpoints;
import com.automation.api.interactions.auth.VerifyLogin;
import com.automation.api.interactions.auth.VerifyLoginWithoutEmail;
import com.automation.api.interactions.common.DeleteFromResource;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AuthStepDefs {

    @When("intenta iniciar sesión con credenciales válidas")
    public void intentaIniciarSesionConCredencialesValidas() {
        // Asumimos un usuario existente según la API
        theActorInTheSpotlight().attemptsTo(
                VerifyLogin.withCredentials("test@test.com", "test")
        );
    }

    @When("intenta iniciar sesión omitiendo el email")
    public void intentaIniciarSesionOmitiendoElEmail() {
        theActorInTheSpotlight().attemptsTo(
                VerifyLoginWithoutEmail.withPassword("test")
        );
    }

    @When("envía una petición DELETE al endpoint de login")
    public void enviaUnaPeticionDELETEAlEndpointDeLogin() {
        theActorInTheSpotlight().attemptsTo(
                DeleteFromResource.at(ApiEndpoints.VERIFY_LOGIN)
        );
    }

    @When("intenta iniciar sesión con credenciales inválidas")
    public void intentaIniciarSesionConCredencialesInvalidas() {
        theActorInTheSpotlight().attemptsTo(
                VerifyLogin.withCredentials("invalid_email_test_01@test.com", "wrongpass")
        );
    }
}
