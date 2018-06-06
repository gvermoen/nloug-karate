Feature: Locations Mock Server

  Background:
    * def locations = {}

  Scenario: pathMatches('/mock/locations') && methodIs('get')
    * def response = $locations

  Scenario: pathMatches('/mock/locations') && methodIs('post')
    * def location = request
    * eval locations[location.locationId + ''] = location



