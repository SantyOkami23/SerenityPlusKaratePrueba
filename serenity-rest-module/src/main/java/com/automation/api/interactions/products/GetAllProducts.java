package com.automation.api.interactions.products;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * Screenplay Interaction: retrieves the complete products list via GET.
 */
public class GetAllProducts implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/productsList")
        );
    }

    public static GetAllProducts fromApi() {
        return new GetAllProducts();
    }
}
