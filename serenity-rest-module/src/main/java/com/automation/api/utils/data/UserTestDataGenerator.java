package com.automation.api.utils.data;

import com.automation.api.models.UserAccountRequest;
import net.datafaker.Faker;

/**
 * Generador de datos de prueba dinámicos para cuentas de usuario.
 * Utiliza DataFaker para evitar datos estáticos y garantizar independencia entre tests.
 */
public class UserTestDataGenerator {

    private static final Faker FAKER = new Faker();
    private static final String[] TITLES = {"Mr", "Mrs", "Miss"};
    private static final String[] COUNTRIES = {"India", "United States", "Canada", "Australia", "Israel",
            "New Zealand", "Singapore"};

    private UserTestDataGenerator() {
        // Clase utilitaria — no instanciable
    }

    /**
     * Genera un UserAccountRequest con datos completamente aleatorios.
     * Incluye los 17 campos requeridos por las APIs 11 y 13.
     */
    public static UserAccountRequest randomUser() {
        return UserAccountRequest.builder()
                .name(FAKER.name().username())
                .email(FAKER.internet().emailAddress("testuser_" + System.currentTimeMillis()))
                .password(FAKER.internet().password(8, 16, true, true))
                .title(TITLES[FAKER.random().nextInt(TITLES.length)])
                .birthDate(String.valueOf(FAKER.number().numberBetween(1, 28)))
                .birthMonth(String.valueOf(FAKER.number().numberBetween(1, 12)))
                .birthYear(String.valueOf(FAKER.number().numberBetween(1970, 2005)))
                .firstname(FAKER.name().firstName())
                .lastname(FAKER.name().lastName())
                .company(FAKER.company().name())
                .address1(FAKER.address().streetAddress())
                .address2(FAKER.address().secondaryAddress())
                .country(COUNTRIES[FAKER.random().nextInt(COUNTRIES.length)])
                .zipcode(FAKER.address().zipCode())
                .state(FAKER.address().state())
                .city(FAKER.address().city())
                .mobileNumber(FAKER.phoneNumber().cellPhone())
                .build();
    }

    /**
     * Genera un UserAccountRequest con un email específico (útil para tests de update).
     */
    public static UserAccountRequest randomUserWithEmail(String email) {
        return UserAccountRequest.builder()
                .name(FAKER.name().username())
                .email(email)
                .password(FAKER.internet().password(8, 16, true, true))
                .title(TITLES[FAKER.random().nextInt(TITLES.length)])
                .birthDate(String.valueOf(FAKER.number().numberBetween(1, 28)))
                .birthMonth(String.valueOf(FAKER.number().numberBetween(1, 12)))
                .birthYear(String.valueOf(FAKER.number().numberBetween(1970, 2005)))
                .firstname(FAKER.name().firstName())
                .lastname(FAKER.name().lastName())
                .company(FAKER.company().name())
                .address1(FAKER.address().streetAddress())
                .address2(FAKER.address().secondaryAddress())
                .country(COUNTRIES[FAKER.random().nextInt(COUNTRIES.length)])
                .zipcode(FAKER.address().zipCode())
                .state(FAKER.address().state())
                .city(FAKER.address().city())
                .mobileNumber(FAKER.phoneNumber().cellPhone())
                .build();
    }
}
