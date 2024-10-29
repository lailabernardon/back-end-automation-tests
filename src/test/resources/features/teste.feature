Feature: JSONPlaceholder API

  Scenario: Fetch a post by ID - passing values directly
    Given I have the JSONPlaceholder API endpoint for posts
    When I send a GET request for post with ID 1
    Then I should receive a valid response
    And the post title should be "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"

  Scenario Outline: Fetch a post by ID - parametrize
    Given I have the JSONPlaceholder API endpoint for posts
    When I send a GET request for post with ID "<id>"
    Then I should receive a valid response
    And the post title should be as "<expectedResult>"
    Examples:
      | id | expectedResult                                                             |
      | 1  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit |
      | 2  | qui est esse                                                               |

  Scenario Outline: Fetch a post by ID - parametrize Negative scenarios
    Given I have the JSONPlaceholder API endpoint for posts
    When I send a GET request for post with ID "<id>"
    Then I should receive a invalid response
    And the post title should be as invalid
    Examples:
      | id   |
      | a    |
      | 1025 |


  Scenario: Validate response structure from JSONPlaceholder API
    Given I have the JSONPlaceholder API endpoint for posts
    When I send a GET request for post with ID 1
    Then I should receive a valid response
    And the structure of the JSON should be correct
