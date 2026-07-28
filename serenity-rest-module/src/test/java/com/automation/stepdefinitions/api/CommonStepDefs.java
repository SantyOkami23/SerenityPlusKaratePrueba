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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.automation.api.config.ApiEndpoints;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CommonStepDefs {

    private static final Logger logger = LoggerFactory.getLogger(CommonStepDefs.class);

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el cliente de AutomationExercise está listo")
    public void elClienteDeAutomationExerciseEstaListo() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = environmentVariables.getProperty("restapi.baseurl", ApiEndpoints.BASE_URL);
        
        logger.info("Inicializando cliente con baseUrl: {}", baseUrl);

        // Evitar que SerenityRest agregue '; charset=ISO-8859-1' que rompe el API de AutomationExercise
        net.serenitybdd.rest.SerenityRest.setDefaultConfig(
                net.serenitybdd.rest.SerenityRest.config()
                        .encoderConfig(io.restassured.config.EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false))
        );

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
