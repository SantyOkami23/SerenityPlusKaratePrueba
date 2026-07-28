package com.automation.stepdefinitions.api;

import com.automation.api.config.ApiEndpoints;
import com.automation.api.interactions.auth.VerifyLogin;
import com.automation.api.interactions.auth.VerifyLoginWithoutEmail;
import com.automation.api.interactions.auth.VerifyLoginWithoutPassword;
import com.automation.api.interactions.auth.VerifyLoginWithoutParams;
import com.automation.api.interactions.common.DeleteFromResource;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AuthStepDefs {

    @When("intenta iniciar sesión con credenciales válidas")
    public void intentaIniciarSesionConCredencialesValidas() {
        // Generar datos y crear un usuario válido para la prueba de login
        com.automation.api.models.UserAccountRequest testUser = com.automation.api.utils.data.UserTestDataGenerator.randomUser();
        theActorInTheSpotlight().attemptsTo(
                com.automation.api.interactions.users.CreateUserAccount.withData(testUser)
        );
        theActorInTheSpotlight().attemptsTo(
                VerifyLogin.withCredentials(testUser.getEmail(), testUser.getPassword())
        );
    }

    @When("intenta iniciar sesión omitiendo el email")
    public void intentaIniciarSesionOmitiendoElEmail() {
        theActorInTheSpotlight().attemptsTo(
                VerifyLoginWithoutEmail.withPassword("test")
        );
    }

    @When("intenta iniciar sesión omitiendo el password")
    public void intentaIniciarSesionOmitiendoElPassword() {
        theActorInTheSpotlight().attemptsTo(
                VerifyLoginWithoutPassword.withEmail("test@test.com")
        );
    }

    @When("intenta iniciar sesión omitiendo ambos parámetros")
    public void intentaIniciarSesionOmitiendoAmbosParametros() {
        theActorInTheSpotlight().attemptsTo(
                VerifyLoginWithoutParams.perform()
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

    @When("intenta iniciar sesión con formato de email inválido")
    public void intentaIniciarSesionConFormatoDeEmailInvalido() {
        theActorInTheSpotlight().attemptsTo(
                VerifyLogin.withCredentials("correo_sin_arroba.com", "password123")
        );
    }
}
