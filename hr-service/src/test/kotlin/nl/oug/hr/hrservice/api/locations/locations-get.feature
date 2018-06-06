@wip
Feature: Retrieve locations

  Scenario: Post a location
    Given url "http://localhost:23456/mock/locations"
    And request { locationId:1700, city: "Seattle" }
    When method post
    Then status 200
    Given url "http://localhost:23456/mock/locations"
    And request { locationId:1800, city: "Toronto" }
    When method post
    Then status 200

  Scenario: Retrieve a location
    Given url "http://localhost:23456/mock/locations"
    When method get
    Then status 200
    And print 'response: ', response