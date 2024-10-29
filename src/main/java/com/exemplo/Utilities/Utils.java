package com.exemplo.Utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Utils {
    Response responseTodos;
    Map<String, Object> bodyMap;

    public void LineAndSeparatorLine() {
        System.out.println("\n---------------------------------------------------------");
    }

    public String getResponseBodyTodos(int id) throws IOException {
        String endpointTodos = "https://jsonplaceholder.typicode.com/todos";
        responseTodos = RestAssured.get(endpointTodos + "/" + id);
        String getBody = responseTodos.getBody().asString();
        System.out.println("getBody " + getBody);
        LineAndSeparatorLine();
        return endpointTodos;
    }

    public void validateTodosBodyStructure() throws IOException {
        bodyMap = responseTodos.jsonPath().getMap("$");

        assertTrue("Body should contain userId", bodyMap.containsKey("userId"));
        assertTrue("Body should contain id", bodyMap.containsKey("id"));
        assertTrue("Body should contain title", bodyMap.containsKey("title"));
        assertTrue("Body should contain completed", bodyMap.containsKey("completed"));
        System.out.println("The structure is as expected");
    }

    public void validateTodosBodyTypes() throws IOException {
        bodyMap = responseTodos.jsonPath().getMap("$");
        assertTrue("userId should be an integer", bodyMap.get("userId") instanceof Integer);
        assertTrue("id should be an integer", bodyMap.get("id") instanceof Integer);
        assertTrue("title should be a string", bodyMap.get("title") instanceof String);
        assertTrue("completed should be a Boolean", bodyMap.get("completed") instanceof Boolean);
        System.out.println("The types  is as expected");
    }

    public void validateTodosBodyNonNullAttributes() throws IOException {
        bodyMap = responseTodos.jsonPath().getMap("$");
        assertNotNull("userId should not be null", bodyMap.get("userId"));
        assertNotNull("id should not be null", bodyMap.get("id"));
        assertNotNull("title should not be null", bodyMap.get("title"));
        assertNotNull("completed should not be null", bodyMap.get("completed"));
        System.out.println("The attrubtes are not null");
    }
    public void validateTodosNumberOfUsers() throws IOException {
        responseTodos = RestAssured.given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .when()
                .get("/todos")
                .then()
                .extract()
                .response();

        if (responseTodos == null) {
            throw new IllegalStateException("responseTodos is null. Ensure the API request was made.");
        }

        String jsonString = responseTodos.getBody().asString();

        JSONArray jsonArray = new JSONArray(jsonString);
        Set<Integer> userIdSet = new HashSet<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int userId = jsonObject.getInt("userId");
            userIdSet.add(userId);

        }

        System.out.println("User IDs unicos: " + userIdSet);
    }


}