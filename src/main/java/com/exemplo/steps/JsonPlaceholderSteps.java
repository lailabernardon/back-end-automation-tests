package com.exemplo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static org.junit.Assert.*;

import io.restassured.response.Response;

import static org.junit.Assert.assertTrue;

import java.util.Map;

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

    @Then("I should receive a invalid response")
    public void i_should_receive_a_invalid_response() {
        assertEquals(404, response.getStatusCode());
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

    @When("I send a GET request for post with ID {string}")
    public void iSendAGETRequestForPostWithID(String id) {
        response = RestAssured.get(endpoint + "/" + id);
        System.out.println(endpoint + "/" + id + " was sent and the response was:" + response);

    }

    @And("the post title should be as {string}")
    public void thePostTitleShouldBeAs(String expectedTitle) {
        String actualTitle = response.jsonPath().getString("title");
        assertEquals(expectedTitle, actualTitle);
        assertNotNull(actualTitle);
        System.out.println("Actual title was: " + actualTitle);
        System.out.println("Expected title was: " + expectedTitle);
    }

    @And("the post title should be as invalid")
    public void thePostTitleShouldBeAsInvalid() {
        String actualTitle = response.jsonPath().getString("title");
        assertNull(actualTitle);
        System.out.println("Actual title was: " + actualTitle);
    }

    @And("the structure of the JSON should be correct")
    public void theStructureOfTheJSONShouldBeCorrect() {
        // Get the response as a single object
        Object jsonResponse = response.jsonPath().get("");
        System.out.println(jsonResponse);
        boolean isStructureValid = true;

        if (jsonResponse instanceof List) {
            // If the response is a list (array of objects)
            List<Map<String, Object>> jsonList = (List<Map<String, Object>>) jsonResponse;

            // Verify that the list is not empty
            assertFalse("The JSON response should contain at least one item", jsonList.isEmpty());

            // Loop through each item to verify fields
            for (Map<String, Object> item : jsonList) {
                assertNotNull("userId should not be null", item.get("userId"));
                assertNotNull("id should not be null", item.get("id"));
                assertNotNull("title should not be null", item.get("title"));
                assertNotNull("body should not be null", item.get("body"));
            }
        } else if (jsonResponse instanceof Map) {
            // If the response is a single object
            Map<String, Object> jsonObject = (Map<String, Object>) jsonResponse;

            // Verify that the single object has all required fields
            assertNotNull("userId should not be null", jsonObject.get("userId"));
            assertNotNull("id should not be null", jsonObject.get("id"));
            assertNotNull("title should not be null", jsonObject.get("title"));
            assertNotNull("body should not be null", jsonObject.get("body"));
        } else {
            // If response is neither a list nor an object, mark it as invalid
            isStructureValid = false;
        }

        assertTrue("The structure of the JSON response is incorrect", isStructureValid);
        System.out.println(isStructureValid);
    }
}
