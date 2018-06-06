Feature: Retrieve departments

  Background:
    Given url baseUrl

  Scenario: Get all departments
    Given path 'departments'
    When method GET
    Then status 200
    * print 'response: ', response
    And match each response == {"departmentId":"#notnull","departmentName":"#string","managerId":"##number","locationId":"#ignore"}
