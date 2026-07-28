@api @users
Feature: User Management API
  Como usuario de AutomationExercise
  Quiero poder gestionar mi cuenta
  Para registrarme, actualizar mis datos y eliminar mi perfil

  @positive @smoke
  Scenario: Ciclo de vida completo de un usuario (Crear, Obtener, Actualizar, Eliminar)
    Given el cliente de AutomationExercise está listo
    And se han generado datos para un nuevo usuario
    
    When crea la nueva cuenta de usuario
    Then recibe un código de respuesta 201
    And el mensaje de respuesta es "User created!"
    
    When consulta los detalles del usuario por email
    Then recibe un código de respuesta 200
    And los detalles del usuario coinciden con el email registrado
    
    When actualiza los datos de la cuenta
    Then recibe un código de respuesta 200
    And el mensaje de respuesta es "User updated!"
    
    When elimina la cuenta de usuario
    Then recibe un código de respuesta 200
    And el mensaje de respuesta es "Account deleted!"

  @negative
  Scenario: Intentar crear cuenta con email ya existente
    Given el cliente de AutomationExercise está listo
    And se han generado datos para un nuevo usuario
    When crea la nueva cuenta de usuario
    And intenta crear la cuenta de nuevo con el mismo email
    Then recibe un código de respuesta 400
    And el mensaje de respuesta es "Email already exists!"

  @negative
  Scenario: Intentar crear cuenta sin parámetros requeridos
    Given el cliente de AutomationExercise está listo
    When intenta crear cuenta sin parámetros requeridos
    Then recibe un código de respuesta 400

  @negative
  Scenario: Enviar método GET no soportado a crear cuenta
    Given el cliente de AutomationExercise está listo
    When envía una petición GET a crear cuenta
    Then recibe un código de respuesta 405

  @negative
  Scenario: Actualizar cuenta sin parámetro email
    Given el cliente de AutomationExercise está listo
    When intenta actualizar cuenta sin parámetro email
    Then recibe un código de respuesta 400

  @negative
  Scenario: Actualizar cuenta inexistente
    Given el cliente de AutomationExercise está listo
    When intenta actualizar una cuenta inexistente
    Then recibe un código de respuesta 404
    And el mensaje de respuesta es "Account not found!"

  @negative
  Scenario: Obtener detalles de cuenta inexistente
    Given el cliente de AutomationExercise está listo
    When consulta los detalles del usuario con email inexistente
    Then recibe un código de respuesta 404
    And el mensaje de respuesta es "Account not found with this email, try another email!"

  @negative
  Scenario: Obtener detalles de cuenta sin email
    Given el cliente de AutomationExercise está listo
    When consulta los detalles del usuario omitiendo el email
    Then recibe un código de respuesta 400
    And el mensaje de respuesta es "Bad request, email parameter is missing in GET request."

  @negative
  Scenario: Enviar método POST no soportado a obtener detalles
    Given el cliente de AutomationExercise está listo
    When envía una petición POST a obtener detalles
    Then recibe un código de respuesta 405
