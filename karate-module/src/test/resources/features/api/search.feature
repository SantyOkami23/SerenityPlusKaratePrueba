@api @search
Feature: Search Product API Tests

  Background:
    * url baseUrl

  @positive @smoke
  Scenario: Buscar productos con un término válido
    Given path '/api/searchProduct'
    And form field search_product = 'top'
    When method post
    Then status 200
    And match response.responseCode == 200
    And match response.products != '#[0]'

  @negative
  Scenario: Buscar productos sin enviar el parámetro requerido
    Given path '/api/searchProduct'
    When method post
    Then status 200
    And match response.responseCode == 400
    And match response.message contains 'search_product parameter is missing'
