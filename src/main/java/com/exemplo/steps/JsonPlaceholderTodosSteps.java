package com.exemplo.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.exemplo.Utilities.Utils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonPlaceholderTodosSteps extends Utils {

    private String endpointTodos;
    private Response responseTodos;

    @Given("I have the JSONPlaceholder API endpoint for todos")
    public void i_have_the_jsonplaceholder_api_endpoint_for_posts() {
        endpointTodos = "https://jsonplaceholder.typicode.com/todos";
        System.out.println(endpointTodos + " is beeing tested");
    }

    @When("I send a GET request for post with ID {string} for todos")
    public void iSendAGETRequestForPostWithIDForTodos(String id) {
        responseTodos = RestAssured.get(endpointTodos + "/" + id);
        System.out.println(endpointTodos + "/" + id + " was sent and the response was:" + responseTodos);
    }

    @Then("I should receive a valid response for todos")
    public void iShouldReceiveAValidResponseForTodos() {
        assertEquals(200, responseTodos.getStatusCode());
        System.out.println("Status code: " + responseTodos.getStatusCode());
    }

    @And("the post title should be as {string} for todos")
    public void thePostTitleShouldBeAsForTodos(String expectedTitle) {
        String actualTitle = responseTodos.jsonPath().getString("title");
        assertEquals(expectedTitle, actualTitle);
        assertNotNull(actualTitle);
        System.out.println("Actual title was: " + actualTitle);
        System.out.println("Expected title was: " + expectedTitle);
    }

    @And("the post id should be as {string} for todos")
    public void thePostIdShouldBeAsForTodos(String id) {
        String actualId = responseTodos.jsonPath().getString("id");
        assertEquals(id, actualId);
        System.out.println("Actual id was: " + actualId);
        System.out.println("Expected is was: " + id);
    }

    @Then("I should receive a invalid response for todos")
    public void iShouldReceiveAInvalidResponseForTodos() {
        assertEquals(404, responseTodos.getStatusCode());
        System.out.println("Status code: " + responseTodos.getStatusCode());
    }

    @And("the post response should be as {string} for todos")
    public void thePostResponseShouldBeAsForTodos(String ExpectedInvalidResponse) {
        // Retrieve the response body as a String
        String responseBody = responseTodos.getBody().asString();
        // Assert that the response body equals "{}"
        assertEquals("The response body should be an empty JSON object", "{}", responseBody.trim());
        System.out.println("Response body is correct for invalid ids");
    }

    @And("the post id should has completed populated")
    public void thePostIdShouldHasCompletedPopulated() {
        String actualCompleted = responseTodos.jsonPath().getString("completed");
        if (actualCompleted.equals("true")) {
            System.out.println("Completed is " + actualCompleted);
        } else {
            System.out.println("Completed is " + actualCompleted);
        }

    }

    @And("the post response should be as valid for todos")
    public void thePostResponseShouldBeAsValidForTodos() {
        String getBody = responseTodos.getBody().asString();
        System.out.println("getBody " + getBody);
        LineAndSeparatorLine();
        String headers = responseTodos.headers().toString();
        System.out.println("headers " + headers);
        LineAndSeparatorLine();
        String getCookies = responseTodos.getCookies().toString();
        System.out.println("getCookies " + getCookies);
        LineAndSeparatorLine();
        String getContentType = responseTodos.getContentType();
        System.out.println("getContentType " + getContentType);
        LineAndSeparatorLine();
        String getStatusLine = responseTodos.getStatusLine();
        System.out.println("getStatusLine " + getStatusLine);
        LineAndSeparatorLine();
        String getSessionId = responseTodos.getSessionId();
        System.out.println("getSessionId " + getSessionId);
        LineAndSeparatorLine();
        int getStatusCode = responseTodos.getStatusCode();
        System.out.println("getStatusCode " + getStatusCode);
        LineAndSeparatorLine();
        int getTime = (int) responseTodos.getTime();
        System.out.println("getTime " + getTime);
    }

    @And("the post response should have a valid body")
    public void thePostResponseShouldHaveAValidBody() throws IOException {
        String responseBody = getResponseBodyTodos(1);
        assertNotNull(responseBody);
        System.out.println("Response body should not be null");
        assertFalse(responseBody.trim().isEmpty());
        System.out.println("Response body should not be empty");
        validateTodosBodyStructure();
        validateTodosBodyTypes();
        validateTodosBodyNonNullAttributes();

    }

    @And("the post response for todos must have a valid number of users")
    public void thePostResponseForTodosMustHaveAValidNumberOfUsers() throws IOException {
        validateTodosNumberOfUsers();
    }
}
