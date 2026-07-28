@api @search
Feature: Search Product API
  Como usuario de AutomationExercise
  Quiero buscar productos específicos
  Para encontrar rápidamente lo que necesito

  @positive @smoke
  Scenario: Buscar productos con un término válido
    Given el cliente de AutomationExercise está listo
    When busca el producto con término "top"
    Then recibe un código de respuesta 200
    And la lista de productos no debe estar vacía

  @negative
  Scenario: Buscar productos sin enviar el parámetro requerido
    Given el cliente de AutomationExercise está listo
    When realiza una búsqueda sin el parámetro requerido
    Then recibe un código de respuesta 400
    And el mensaje de respuesta contiene "search_product parameter is missing"

  @positive
  Scenario: Buscar producto que no existe en el catálogo
    Given el cliente de AutomationExercise está listo
    When busca el producto con término "ProductoInexistente999"
    Then recibe un código de respuesta 200

  @negative
  Scenario: Enviar método GET no soportado a búsqueda de productos
    Given el cliente de AutomationExercise está listo
    When envía una petición GET a la búsqueda de productos
    Then recibe un código de respuesta 405
    And el mensaje de respuesta es "This request method is not supported."
