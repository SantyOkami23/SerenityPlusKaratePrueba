package com.automation.api.interactions.products;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * Screenplay Interaction: sends an unsupported POST to the products list endpoint.
 * Expected to return responseCode 405.
 */
public class PostToProducts implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/productsList")
        );
    }

    public static PostToProducts toApi() {
        return new PostToProducts();
    }
}
