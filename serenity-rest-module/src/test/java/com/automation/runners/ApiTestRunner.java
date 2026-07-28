package com.automation.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner principal de JUnit 4 con Serenity + Cucumber.
 * Ejecuta todas las features que tengan el tag @api.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "com.automation.stepdefinitions.api",
        tags = "@api",
        plugin = {"pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ApiTestRunner {
}
