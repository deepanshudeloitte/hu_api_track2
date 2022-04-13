package apiTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

public class getOperation {
     public String url ="https://gorest.co.in/public/v1/users";
    @Test
    public void test_1() {

        given().get(url).then().statusCode(200).log().all();

        Response response = given().get(url);
        Assert.assertEquals(response.getStatusCode(), 200,"Response is Miss Match");
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test        // validate gender from the response///
    public  void test_2() {
        String url = "https://gorest.co.in";
        String endpoint = "/public/v1/users";
        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then().extract().response();
        ResponseBody rbody = response.getBody();
        //rbody.print();


        given().get("https://gorest.co.in/public/v1/users").
                then().
                statusCode(200).
                body("data.gender",hasItems("male","female"));
        Assert.assertEquals(response.getStatusCode(),200,"Responsce is miss Match");
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");

    }
    @Test           //Validate the json schema for the response
    public  void test_3() {
        baseURI = "https://gorest.co.in";
                given().
                get("/public/v1/users").
                then().
                assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
    }

}
