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
