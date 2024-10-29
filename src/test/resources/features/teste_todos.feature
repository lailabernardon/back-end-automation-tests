Feature: JSONPlaceholder API Todos

  Scenario Outline: Fetch a todos by ID - parametrize valid values
    Given I have the JSONPlaceholder API endpoint for todos
    When I send a GET request for post with ID "<id>" for todos
    Then I should receive a valid response for todos
    And the post title should be as "<expectedResult>" for todos
    Examples:
      | id | expectedResult                     |
      | 1  | delectus aut autem                 |
      | 2  | quis ut nam facilis et officia qui |

  Scenario Outline: Fetch a todos by ID - id body, completed verifications
    Given I have the JSONPlaceholder API endpoint for todos
    When I send a GET request for post with ID "<id>" for todos
    Then I should receive a valid response for todos
    And the post id should be as "<id>" for todos
    And the post id should has completed populated
    Examples:
      | id  |
      | 1   |
      | 100 |

  Scenario Outline: Fetch a todos by ID - parametrize invalid values
    Given I have the JSONPlaceholder API endpoint for todos
    When I send a GET request for post with ID "<id>" for todos
    Then I should receive a invalid response for todos
    And the post response should be as "<invalidResponse>" for todos
    Examples:
      | id  | invalidResponse |
      | 201 | {}              |
      | a   | {}              |


  Scenario Outline: Fetch a todos by ID - ResponseOptions Headers, Cookies, time, body
    Given I have the JSONPlaceholder API endpoint for todos
    When I send a GET request for post with ID "<id>" for todos
    Then I should receive a valid response for todos
    And the post response should be as valid for todos
    Examples:
      | id  |
      | 1   |

  Scenario Outline: Fetch a todos by ID - Response body validation not null, structure and types
    Given I have the JSONPlaceholder API endpoint for todos
    When I send a GET request for post with ID "<id>" for todos
    Then I should receive a valid response for todos
    And the post response should have a valid body
    Examples:
      | id  |
      | 1   |

  Scenario Outline: Fetch a todos by ID - Response number of users
    Given I have the JSONPlaceholder API endpoint for todos
    When I send a GET request for post with ID "<id>" for todos
    Then I should receive a valid response for todos
    And the post response for todos must have a valid number of users
    Examples:
      | id  |
      | 1   |
      | 10   |