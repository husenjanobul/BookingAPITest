@getBookingById @regression
Feature: Get booking

  Background:
    Given get request to health check endpoint
    Then response status code should be 201

  Scenario: get booking by id
    When get request to GetBookingIds endpoint
    Then response status code should be 200
    And response body should be array and contains uniq Ids
    When get request to GetBooking endpoint with path param "id"
    Then response status code should be 200
    And response should be contains following information
      | firstname       |
      | lastname        |
      | totalprice      |
      | depositpaid     |
      | bookingdates    |
      | checkin         |
      | checkout        |
      | additionalneeds |