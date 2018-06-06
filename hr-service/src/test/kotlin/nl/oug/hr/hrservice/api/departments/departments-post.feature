Feature: Create new departments

  Background:
    Given url "http://localhost:8080/api/departments"

  Scenario: Create a new department
    Given request { departmentId: 10, departmentName: "Administration", managerId: 200, locationId: 1700 }
    When method post
    Then status 200

  Scenario: Creating a new department fails when departmentId is invalid
    Given request { departmentId: "abc", departmentName: "IT", managerId: 103, locationId: 1400 }
    When method post
    Then status 400