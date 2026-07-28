@api @users
Feature: User Management API Tests

  Background:
    * url baseUrl
    # Generar datos aleatorios de usuario usando JS embebido de Karate
    * def randomEmail = 'karate_user_' + java.lang.System.currentTimeMillis() + '@test.com'
    * def userData = 
      """
      {
        name: 'Karate User',
        email: '#(randomEmail)',
        password: 'password123',
        title: 'Mr',
        birth_date: '1',
        birth_month: '1',
        birth_year: '1990',
        firstname: 'Karate',
        lastname: 'Test',
        company: 'Automation',
        address1: 'Street 1',
        address2: 'Street 2',
        country: 'Canada',
        zipcode: '12345',
        state: 'Ontario',
        city: 'Toronto',
        mobile_number: '1234567890'
      }
      """

  @positive @smoke
  Scenario: Ciclo de vida completo de un usuario (Crear, Obtener, Actualizar, Eliminar)
    
    # 1. Crear Cuenta
    Given path '/api/createAccount'
    And form fields userData
    When method post
    Then status 200
    And match response.responseCode == 201
    And match response.message == 'User created!'
    
    # 2. Obtener Detalles
    Given path '/api/getUserDetailByEmail'
    And param email = randomEmail
    When method get
    Then status 200
    And match response.responseCode == 200
    And match response.user.email == randomEmail
    
    # 3. Actualizar Cuenta (Cambiamos el nombre a 'Karate Updated')
    * def updatedUserData = userData
    * set updatedUserData.name = 'Karate Updated'
    
    Given path '/api/updateAccount'
    And form fields updatedUserData
    When method put
    Then status 200
    And match response.responseCode == 200
    And match response.message == 'User updated!'
    
    # 4. Eliminar Cuenta
    Given path '/api/deleteAccount'
    And form field email = randomEmail
    And form field password = 'password123'
    When method delete
    Then status 200
    And match response.responseCode == 200
    And match response.message == 'Account deleted!'

  @negative
  Scenario: Intentar crear cuenta con email ya existente
    Given path '/api/createAccount'
    And form fields userData
    When method post
    Then status 200
    
    # Segundo intento con mismo email
    Given path '/api/createAccount'
    And form fields userData
    When method post
    Then status 200
    And match response.responseCode == 400
    And match response.message == 'Email already exists!'

  @negative
  Scenario: Intentar crear cuenta sin parámetros requeridos
    Given path '/api/createAccount'
    And form field name = 'Karate'
    # Faltan email y password
    When method post
    Then status 200
    And match response.responseCode == 400
    # Valida mensaje de error apropiado

  @negative
  Scenario: Enviar GET a crear cuenta
    Given path '/api/createAccount'
    When method get
    # Retorna HTTP 405 directamente
    Then status 405

  @negative
  Scenario: Actualizar cuenta sin parámetro email
    Given path '/api/updateAccount'
    And form field name = 'Karate'
    When method put
    Then status 200
    And match response.responseCode == 400

  @negative
  Scenario: Actualizar cuenta inexistente
    Given path '/api/updateAccount'
    And form field email = 'no_existo_karate@test.com'
    And form field password = 'pass'
    When method put
    Then status 200
    And match response.responseCode == 404
    And match response.message == 'Account not found!'

  @negative
  Scenario: Obtener detalles de cuenta inexistente
    Given path '/api/getUserDetailByEmail'
    And param email = 'no_existo_karate@test.com'
    When method get
    Then status 200
    # La API parece retornar 404 para cuenta no encontrada en getUserDetail
    And match response.responseCode == 404
    And match response.message == 'Account not found with this email, try another email!'

  @negative
  Scenario: Obtener detalles de cuenta sin email
    Given path '/api/getUserDetailByEmail'
    When method get
    Then status 200
    And match response.responseCode == 400
    And match response.message == 'Bad request, email parameter is missing in GET request.'

  @negative
  Scenario: Obtener detalles con POST
    Given path '/api/getUserDetailByEmail'
    When method post
    Then status 200
    And match response.responseCode == 405
    And match response.message == 'This request method is not supported.'
