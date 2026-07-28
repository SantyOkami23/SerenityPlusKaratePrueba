package com.automation.stepdefinitions.api;

import com.automation.api.interactions.users.CreateUserAccount;
import com.automation.api.interactions.users.DeleteUserAccount;
import com.automation.api.interactions.users.GetUserDetailByEmail;
import com.automation.api.interactions.users.UpdateUserAccount;
import com.automation.api.models.UserAccountRequest;
import com.automation.api.utils.data.UserTestDataGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserManagementStepDefs {

    private UserAccountRequest currentUserData;

    @And("se han generado datos para un nuevo usuario")
    public void seHanGeneradoDatosParaUnNuevoUsuario() {
        // Usar Faker para crear un usuario 100% dinámico
        currentUserData = UserTestDataGenerator.randomUser();
    }

    @When("crea la nueva cuenta de usuario")
    public void creaLaNuevaCuentaDeUsuario() {
        theActorInTheSpotlight().attemptsTo(
                CreateUserAccount.withData(currentUserData)
        );
    }

    @When("consulta los detalles del usuario por email")
    public void consultaLosDetallesDelUsuarioPorEmail() {
        theActorInTheSpotlight().attemptsTo(
                GetUserDetailByEmail.withEmail(currentUserData.getEmail())
        );
    }

    @And("los detalles del usuario coinciden con el email registrado")
    public void losDetallesDelUsuarioCoincidenConElEmailRegistrado() {
        // En AutomationExercise el endpoint de user details envuelve el objeto en "user"
        String emailFromResponse = SerenityRest.lastResponse().path("user.email");
        assertThat("El email del usuario retornado no coincide", emailFromResponse, equalTo(currentUserData.getEmail()));
    }

    @When("actualiza los datos de la cuenta")
    public void actualizaLosDatosDeLaCuenta() {
        // Mantener mismo email, pero actualizar todo lo demás
        UserAccountRequest updatedUserData = UserTestDataGenerator.randomUserWithEmail(currentUserData.getEmail());
        
        theActorInTheSpotlight().attemptsTo(
                UpdateUserAccount.withData(updatedUserData)
        );
        
        // Actualizar referencia local por si lo usamos después
        currentUserData = updatedUserData;
    }

    @When("elimina la cuenta de usuario")
    public void eliminaLaCuentaDeUsuario() {
        theActorInTheSpotlight().attemptsTo(
                DeleteUserAccount.withCredentials(currentUserData.getEmail(), currentUserData.getPassword())
        );
    }
}
