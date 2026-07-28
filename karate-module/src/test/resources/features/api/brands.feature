@api @brands
Feature: Brands API Tests

  Background:
    * url baseUrl

  @positive @smoke
  Scenario: Obtener lista completa de marcas
    Given path '/api/brandsList'
    When method get
    Then status 200
    And match response.responseCode == 200
    And match response.brands != '#[0]'
    And match each response.brands contains { id: '#number', brand: '#string' }

  @negative
  Scenario: Enviar método PUT no soportado a la lista de marcas
    Given path '/api/brandsList'
    When method put
    Then status 200
    And match response.responseCode == 405
    And match response.message == 'This request method is not supported.'
