Feature: JSONPlaceholder API

  Scenario: Fetch a post by ID
    Given I have the JSONPlaceholder API endpoint for posts
    When I send a GET request for post with ID 1
    Then I should receive a valid response
    And the post title should be "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
