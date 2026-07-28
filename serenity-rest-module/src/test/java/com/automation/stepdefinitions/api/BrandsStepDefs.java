package com.automation.stepdefinitions.api;

import com.automation.api.config.ApiEndpoints;
import com.automation.api.interactions.common.GetResource;
import com.automation.api.interactions.common.PostToResource;
import com.automation.api.interactions.common.PutToResource;
import com.automation.api.interactions.common.DeleteFromResource;
import com.automation.api.questions.BrandsResult;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

public class BrandsStepDefs {

    @When("solicita la lista completa de marcas")
    public void solicitaLaListaCompletaDeMarcas() {
        theActorInTheSpotlight().attemptsTo(
                GetResource.from(ApiEndpoints.BRANDS_LIST)
        );
    }

    @When("envía una petición PUT a la lista de marcas")
    public void enviaUnaPeticionPUTALaListaDeMarcas() {
        theActorInTheSpotlight().attemptsTo(
                PutToResource.at(ApiEndpoints.BRANDS_LIST)
        );
    }

    @When("envía una petición POST a la lista de marcas")
    public void enviaUnaPeticionPOSTALaListaDeMarcas() {
        theActorInTheSpotlight().attemptsTo(
                PostToResource.at(ApiEndpoints.BRANDS_LIST)
        );
    }

    @When("envía una petición DELETE a la lista de marcas")
    public void enviaUnaPeticionDELETEALaListaDeMarcas() {
        theActorInTheSpotlight().attemptsTo(
                DeleteFromResource.at(ApiEndpoints.BRANDS_LIST)
        );
    }

    @And("la lista de marcas no debe estar vacía")
    public void laListaDeMarcasNoDebeEstarVacia() {
        theActorInTheSpotlight().should(
                seeThat("la lista de marcas", BrandsResult.fromLastResponse(), not(empty()))
        );
    }

    @And("cada marca debe tener un ID válido")
    public void cadaMarcaDebeTenerUnIDValido() {
        theActorInTheSpotlight().should(
                seeThat("cada marca tiene la estructura esperada", BrandsResult.fromLastResponse(), 
                        everyItem(allOf(
                                hasKey("id"),
                                hasKey("brand")
                        )))
        );
    }
}
