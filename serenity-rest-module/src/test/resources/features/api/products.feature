@api @products
Feature: Products API
  Como usuario de AutomationExercise
  Quiero consultar el catálogo de productos
  Para ver la información y precios disponibles

  @positive @smoke
  Scenario: Obtener lista completa de productos
    Given el cliente de AutomationExercise está listo
    When solicita la lista completa de productos
    Then recibe un código de respuesta 200
    And la lista de productos no debe estar vacía
    And cada producto debe tener un ID válido

  @negative
  Scenario: Enviar método POST no soportado a la lista de productos
    Given el cliente de AutomationExercise está listo
    When envía una petición POST a la lista de productos
    Then recibe un código de respuesta 405
    And el mensaje de respuesta es "This request method is not supported."
