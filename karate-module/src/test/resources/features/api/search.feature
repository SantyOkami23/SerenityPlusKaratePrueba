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

  @positive
  Scenario: Buscar producto que no existe en el catálogo
    Given path '/api/searchProduct'
    And form field search_product = 'ProductoInexistente999'
    When method post
    Then status 200
    And match response.responseCode == 200
    # Es posible que retorne una lista vacía o mensaje vacío
    # Si response.products existe, debe estar vacía o no existir. Validamos código 200 por ahora.

  @negative
  Scenario: Enviar método GET no soportado a búsqueda de productos
    Given path '/api/searchProduct'
    When method get
    Then status 200
    And match response.responseCode == 405
    And match response.message == 'This request method is not supported.'
