@api @products
Feature: Products API Tests

  Background:
    * url baseUrl

  @positive @smoke
  Scenario: Obtener lista completa de productos
    Given path '/api/productsList'
    When method get
    Then status 200
    And match response.responseCode == 200
    And match response.products != '#[0]'
    And match each response.products contains { id: '#number', name: '#string' }

  @negative
  Scenario: Enviar método POST no soportado a la lista de productos
    Given path '/api/productsList'
    When method post
    Then status 200
    And match response.responseCode == 405
    And match response.message == 'This request method is not supported.'
