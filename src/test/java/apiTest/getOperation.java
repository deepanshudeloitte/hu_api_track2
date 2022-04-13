package apiTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class getOperation {

    @Test
    public void test_01() {

        //given().get("https://gorest.co.in/public/v1/users").then().statusCode(200).log().all();

        Response response = given().get("https://gorest.co.in/public/v1/users");
        Assert.assertEquals(response.getStatusCode(), 200,"Response is Miss Match");
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public  void test_02() {
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


        given().get("https://gorest.co.in/public/v1/users").then().statusCode(200).body("data.gender",hasItems("female","male"));
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }
}
