Feature:
  Restful booker api test automation duplicate


  Scenario: Get details of a booking duplicate
    Given I have restfulbooker api
    When I send a request to retrieve a bookingId "3011"
    Then the firstname "Lateef", lastname "BrownAdefira" and statusCode 200 are returned
    And the firstname "Lateef", lastname "BrownAdefira" and statusCode 200 are returned
    And the firstname "Lateef", lastname "BrownAdefira" and statusCode 200 are returned