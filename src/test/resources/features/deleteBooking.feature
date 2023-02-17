@deleteBooking @regression
Feature: Delete Booking
  Background:
    Given get request to health check endpoint
    Then response status code should be 201
    When post request to CreateToken endpoint with username and password
    Then response status code should be 200
    And get "token" for new authentication

  Scenario: Delete existing Booking
    When get request to GetBookingIds endpoint and get a existing id
    When post request to DeleteBooking endpoint with "token" "id"
    Then response status code should be 201
    When get request to GetBooking endpoint with deleted booking "id"
    Then response status code should be 404