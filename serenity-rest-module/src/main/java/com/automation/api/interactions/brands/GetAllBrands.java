package com.automation.api.interactions.brands;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 * Screenplay Interaction: retrieves the complete brands list via GET.
 */
public class GetAllBrands implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/brandsList")
        );
    }

    public static GetAllBrands fromApi() {
        return new GetAllBrands();
    }
}
