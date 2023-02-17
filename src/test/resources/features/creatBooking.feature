@createBooking @regression
Feature: Creat Booking

  Background:
    Given get request to health check endpoint
    Then response status code should be 201

  Scenario: creat a booking with full info
    When post request to CreateBooking endpoint wth client "full" info
    Then response status code should be 200
    And response body should be same the posted client info
