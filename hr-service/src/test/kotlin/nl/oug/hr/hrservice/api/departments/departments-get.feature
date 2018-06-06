Feature: Retrieve departments

  Background:
    Given url baseUrl

  Scenario: Get all departments
    Given table departments
    | departmentId | departmentName     | managerId | locationId |
    | 10           |'Administration'    | 200       | 1700       |
    | 20           |'Marketing'         | 201       | 1800       |
    | 30           |'Purchasing'        | 114       | 1700       |
    | 40           |'Human Resources'   | 203       | 2400       |
    | 50           |'Shipping'          | 121       | 1500       |
    | 60           | 'IT'               | 103       | 1400       |
    | 70           | 'Public Relations' | 204       | 2700       |
    | 80           | 'Sales'            | 145       | 2500       |
    | 90           | 'Executive'        | 100       | 1700       |
    And call read('../../common/add-department.feature') departments

    Given path 'departments'
    When method GET
    Then status 200
    * print 'response: ', response
    And match each response == {"departmentId":"#notnull","departmentName":"#string","managerId":"##number","locationId":"#ignore"}
