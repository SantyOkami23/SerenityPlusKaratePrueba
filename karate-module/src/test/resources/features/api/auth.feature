@api @auth
Feature: Authentication API Tests

  Background:
    * url baseUrl

  @positive @smoke
  Scenario: Verificar login con credenciales válidas
    * def uniqueEmail = 'karate_auth_test_' + java.lang.System.currentTimeMillis() + '@test.com'
    * def uniquePass = 'password123'
    # Crear usuario primero
    Given path '/api/createAccount'
    And form field name = 'Auth Test'
    And form field email = uniqueEmail
    And form field password = uniquePass
    And form field title = 'Mr'
    And form field birth_date = '1'
    And form field birth_month = '1'
    And form field birth_year = '1990'
    And form field firstname = 'Test'
    And form field lastname = 'Auth'
    And form field company = 'K'
    And form field address1 = 'Addr1'
    And form field address2 = 'Addr2'
    And form field country = 'Canada'
    And form field zipcode = '123'
    And form field state = 'State'
    And form field city = 'City'
    And form field mobile_number = '1234'
    When method post
    Then status 200

    # Ahora iniciar sesión
    Given path '/api/verifyLogin'
    And form field email = uniqueEmail
    And form field password = uniquePass
    When method post
    Then status 200
    And match response.responseCode == 200
    And match response.message == 'User exists!'

  @negative
  Scenario: Verificar login sin enviar el parámetro email
    Given path '/api/verifyLogin'
    And form field password = 'test'
    When method post
    Then status 200
    And match response.responseCode == 400
    And match response.message contains 'email or password parameter is missing'

  @negative
  Scenario: Enviar método DELETE no soportado al login
    Given path '/api/verifyLogin'
    When method delete
    Then status 200
    And match response.responseCode == 405
    And match response.message == 'This request method is not supported.'

  @negative
  Scenario: Verificar login con credenciales inválidas
    Given path '/api/verifyLogin'
    And form field email = 'invalid_email_karate_01@test.com'
    And form field password = 'wrongpass'
    When method post
    Then status 200
    And match response.responseCode == 404
    And match response.message == 'User not found!'

  @negative
  Scenario: Verificar login sin enviar el parámetro password
    Given path '/api/verifyLogin'
    And form field email = 'test@test.com'
    When method post
    Then status 200
    And match response.responseCode == 400
    And match response.message contains 'email or password parameter is missing'

  @negative
  Scenario: Verificar login omitiendo ambos parámetros
    Given path '/api/verifyLogin'
    When method post
    Then status 200
    And match response.responseCode == 400
    And match response.message contains 'email or password parameter is missing'

  @negative
  Scenario: Verificar login con formato de email inválido
    Given path '/api/verifyLogin'
    And form field email = 'correo_sin_arroba.com'
    And form field password = 'password123'
    When method post
    Then status 200
    And match response.responseCode == 404
    And match response.message == 'User not found!'
