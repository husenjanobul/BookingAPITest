@partialUpdateBooking @regression
  Feature: Partial Update Booking
    Background:
      Given get request to health check endpoint
      Then response status code should be 201
      When post request to CreateToken endpoint with username and password
      Then response status code should be 200
      And get "token" for new authentication

    Scenario: Partial Update existing Booking
      When get request to GetBookingIds endpoint and get a existing id
      When post request to UpdateBooking endpoint with "token" "id" and "partial" body
      Then response status code should be 200
      And response body should contains requested body
