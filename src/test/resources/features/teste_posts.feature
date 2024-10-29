Feature: JSONPlaceholder API Posts

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

