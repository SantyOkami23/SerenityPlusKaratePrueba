package com.automation.stepdefinitions.api;

import com.automation.api.config.ApiEndpoints;
import com.automation.api.interactions.common.GetResource;
import com.automation.api.interactions.common.PostToResource;
import com.automation.api.interactions.common.PutToResource;
import com.automation.api.interactions.common.DeleteFromResource;
import com.automation.api.questions.ProductsResult;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

public class ProductsStepDefs {

    @When("solicita la lista completa de productos")
    public void solicitaLaListaCompletaDeProductos() {
        theActorInTheSpotlight().attemptsTo(
                GetResource.from(ApiEndpoints.PRODUCTS_LIST)
        );
    }

    @When("envía una petición POST a la lista de productos")
    public void enviaUnaPeticionPOSTALaListaDeProductos() {
        theActorInTheSpotlight().attemptsTo(
                PostToResource.at(ApiEndpoints.PRODUCTS_LIST)
        );
    }

    @When("envía una petición PUT a la lista de productos")
    public void enviaUnaPeticionPUTALaListaDeProductos() {
        theActorInTheSpotlight().attemptsTo(
                PutToResource.at(ApiEndpoints.PRODUCTS_LIST)
        );
    }

    @When("envía una petición DELETE a la lista de productos")
    public void enviaUnaPeticionDELETEALaListaDeProductos() {
        theActorInTheSpotlight().attemptsTo(
                DeleteFromResource.at(ApiEndpoints.PRODUCTS_LIST)
        );
    }

    @And("la lista de productos no debe estar vacía")
    public void laListaDeProductosNoDebeEstarVacia() {
        theActorInTheSpotlight().should(
                seeThat("la lista de productos", ProductsResult.fromLastResponse(), not(empty()))
        );
    }

    @And("cada producto debe tener la estructura completa válida")
    public void cadaProductoDebeTenerLaEstructuraCompletaValida() {
        theActorInTheSpotlight().should(
                seeThat("cada producto tiene la estructura esperada", ProductsResult.fromLastResponse(), 
                        everyItem(allOf(
                                hasKey("id"),
                                hasKey("name"),
                                hasKey("price"),
                                hasKey("brand"),
                                hasKey("category")
                        )))
        );
    }
}
