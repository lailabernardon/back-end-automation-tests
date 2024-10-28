package com.exemplo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonPlaceholderSteps {

    private String endpoint;
    private Response response;

    @Given("I have the JSONPlaceholder API endpoint for posts")
    public void i_have_the_jsonplaceholder_api_endpoint_for_posts() {
        endpoint = "https://jsonplaceholder.typicode.com/posts";
        System.out.println(endpoint + " is beeing tested");
    }

    @When("I send a GET request for post with ID {int}")
    public void i_send_a_get_request_for_post_with_id(int id) {
        response = RestAssured.get(endpoint + "/" + id);
        System.out.println(endpoint + "/" + id + " was sent and the response was:" + response);
    }

    @Then("I should receive a valid response")
    public void i_should_receive_a_valid_response() {
        assertEquals(200, response.getStatusCode());
        System.out.println("Status code: " + response.getStatusCode());
    }

    @Then("the post title should be {string}")
    public void the_post_title_should_be(String expectedTitle) {
        String actualTitle = response.jsonPath().getString("title");
        assertEquals(expectedTitle, actualTitle);
        assertNotNull(actualTitle);
        System.out.println("Actual title was: " + actualTitle);
        System.out.println("Expected title was: " + expectedTitle);
    }
}
