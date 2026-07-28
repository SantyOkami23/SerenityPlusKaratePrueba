@api @auth
Feature: Authentication API Tests

  Background:
    * url baseUrl

  @positive @smoke
  Scenario: Verificar login con credenciales válidas
    Given path '/api/verifyLogin'
    And form field email = 'test@test.com'
    And form field password = 'test'
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
