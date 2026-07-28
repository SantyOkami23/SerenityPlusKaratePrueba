package com.automation.stepdefinitions.api;

import com.automation.api.questions.ResponseCode;
import com.automation.api.questions.ResponseMessage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CommonStepDefs {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el cliente de AutomationExercise está listo")
    public void elClienteDeAutomationExerciseEstaListo() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = environmentVariables.getProperty("restapi.baseurl", "https://automationexercise.com");
        
        theActorCalled("Cliente").whoCan(CallAnApi.at(baseUrl));
    }

    @Then("recibe un código de respuesta {int}")
    public void recibeUnCodigoDeRespuesta(int expectedCode) {
        theActorInTheSpotlight().should(
                seeThat("el código de respuesta de la API", ResponseCode.fromLastResponse(), equalTo(expectedCode))
        );
    }

    @Then("el mensaje de respuesta es {string}")
    public void elMensajeDeRespuestaEs(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat("el mensaje de respuesta", ResponseMessage.fromLastResponse(), equalTo(expectedMessage))
        );
    }

    @Then("el mensaje de respuesta contiene {string}")
    public void elMensajeDeRespuestaContiene(String expectedMessageSubstring) {
        theActorInTheSpotlight().should(
                seeThat("el mensaje de respuesta", ResponseMessage.fromLastResponse(), containsString(expectedMessageSubstring))
        );
    }
}
