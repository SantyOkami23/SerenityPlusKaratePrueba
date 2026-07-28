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
