Feature:
Restful booker api test automation


  Scenario: Get details of a booking
    Given I have restfulbooker api
    When I send a request to retrieve a bookingId "3011"
    Then the firstname "Lateef", lastname "BrownAdefira" and statusCode 200 are returned

  Scenario: Get all available bookingIds
    Given I have restfulbooker api
    When I send a request to retrieve a bookingIds
    Then statusCode 200 are returned


  @TestTorun
  Scenario: CreateBookings
    Given I have restfulbooker api
    When I send a request to createbooking with firstname "Godswin", lastname "Akpabio", totalPrice "3000", depositPaid "true", checking "2026-01-01", checkout "2027-01-01", additionalNeeds "dinner"
    Then the firstname "Godswin", lastname "Akpabio", totalprice 3000 and statusCode 200 are returned

