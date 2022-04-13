package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class postOperation {
    @Test
    public  void test_03() {
        String uri = "https://gorest.co.in/public/v1/users";

        JSONObject request = new JSONObject();
        request.put("name","Deepanshu singh");
        request.put("gender","male");
        request.put("email","deee@15ce.com");
        request.put("status","active");

        String payLoad = request.toJSONString();
        String token="1e3fbfc04d510423f16db6b1976fb8d49a339e4e486ef5006ebe118b552a2a32";
        String authToken = "Bearer "+token;

        RequestSpecification requestSpecification = RestAssured.given()
                .header("Authorization",authToken)
                .contentType("application/json")
                .body(payLoad);

        Response response = requestSpecification.post(uri);
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        String payLoadData = response.body().prettyPrint();
        System.out.println(payLoadData);
    }
}
