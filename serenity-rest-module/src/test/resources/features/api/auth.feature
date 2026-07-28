@api @auth
Feature: Authentication API
  Como usuario de AutomationExercise
  Quiero poder iniciar sesión
  Para acceder a mi cuenta

  @positive @smoke
  Scenario: Verificar login con credenciales válidas
    Given el cliente de AutomationExercise está listo
    When intenta iniciar sesión con credenciales válidas
    Then recibe un código de respuesta 200
    And el mensaje de respuesta es "User exists!"

  @negative
  Scenario: Verificar login sin enviar el parámetro email
    Given el cliente de AutomationExercise está listo
    When intenta iniciar sesión omitiendo el email
    Then recibe un código de respuesta 400
    And el mensaje de respuesta contiene "email or password parameter is missing"

  @negative
  Scenario: Enviar método DELETE no soportado al login
    Given el cliente de AutomationExercise está listo
    When envía una petición DELETE al endpoint de login
    Then recibe un código de respuesta 405
    And el mensaje de respuesta es "This request method is not supported."

  @negative
  Scenario: Verificar login con credenciales inválidas
    Given el cliente de AutomationExercise está listo
    When intenta iniciar sesión con credenciales inválidas
    Then recibe un código de respuesta 404
    And el mensaje de respuesta es "User not found!"

  @negative
  Scenario: Verificar login sin enviar el parámetro password
    Given el cliente de AutomationExercise está listo
    When intenta iniciar sesión omitiendo el password
    Then recibe un código de respuesta 400
    And el mensaje de respuesta contiene "email or password parameter is missing"

  @negative
  Scenario: Verificar login omitiendo ambos parámetros
    Given el cliente de AutomationExercise está listo
    When intenta iniciar sesión omitiendo ambos parámetros
    Then recibe un código de respuesta 400
    And el mensaje de respuesta contiene "email or password parameter is missing"

  @negative
  Scenario: Verificar login con formato de email inválido
    Given el cliente de AutomationExercise está listo
    When intenta iniciar sesión con formato de email inválido
    Then recibe un código de respuesta 404
    And el mensaje de respuesta es "User not found!"
