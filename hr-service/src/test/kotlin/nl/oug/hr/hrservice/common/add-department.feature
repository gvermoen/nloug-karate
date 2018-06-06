@ignore
Feature: Reusable feature to create a department

  Scenario: Create a department
    Given url baseUrl
    And path 'departments'
    And def departmentId = 10
    And def departmentName = 'nloug'
    And def managerId = 200
    And def locationId = 1700
    Given request { departmentId: '#(departmentId)', departmentName: '#(departmentName)', managerId: '#(managerId)', locationId: '#(locationId)' }
    When method post
    Then status 200