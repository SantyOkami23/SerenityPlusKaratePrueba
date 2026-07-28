package com.automation.stepdefinitions.api;

import com.automation.api.config.ApiEndpoints;
import com.automation.api.interactions.common.PostToResource;
import com.automation.api.interactions.search.SearchProduct;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SearchStepDefs {

    @When("busca el producto con término {string}")
    public void buscaElProductoConTermino(String searchTerm) {
        theActorInTheSpotlight().attemptsTo(
                SearchProduct.withTerm(searchTerm)
        );
    }

    @When("realiza una búsqueda sin el parámetro requerido")
    public void realizaUnaBusquedaSinElParametroRequerido() {
        theActorInTheSpotlight().attemptsTo(
                PostToResource.at(ApiEndpoints.SEARCH_PRODUCT)
        );
    }
}
