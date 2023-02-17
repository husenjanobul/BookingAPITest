@getBookingIds @regression

Feature: Returns the ids of all the bookings that exist within the API

  Background:
    Given get request to health check endpoint
    Then response status code should be 201

  Scenario: Get all bookings ids
    When get request to GetBookingIds endpoint
    Then response status code should be 200
    And response body should be array and contains uniq Ids

  Scenario: Get all bookings ids by checkIn date
    When get request to GetBookingIds endpoint with "checkin" date
    Then response status code should be 200
    And response body should be array and contains uniq Ids

  Scenario: Get all bookings ids by checkOut date
    When get request to GetBookingIds endpoint with "checkout" date
    Then response status code should be 200
    And response body should be array and contains uniq Ids
