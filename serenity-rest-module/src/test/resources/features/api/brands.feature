@api @brands
Feature: Brands API
  Como usuario de AutomationExercise
  Quiero consultar el catálogo de marcas
  Para conocer las marcas disponibles en la tienda

  @positive @smoke
  Scenario: Obtener lista completa de marcas
    Given el cliente de AutomationExercise está listo
    When solicita la lista completa de marcas
    Then recibe un código de respuesta 200
    And la lista de marcas no debe estar vacía
    And cada marca debe tener un ID válido

  @negative
  Scenario: Enviar método PUT no soportado a la lista de marcas
    Given el cliente de AutomationExercise está listo
    When envía una petición PUT a la lista de marcas
    Then recibe un código de respuesta 405
    And el mensaje de respuesta es "This request method is not supported."
