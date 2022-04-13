package apiTest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class postOperation {

    @Test
    public  void test_05() throws IOException {
        FileInputStream file = new FileInputStream("./ExcelTest/apiTest.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("sheet1");


        String uri = "https://gorest.co.in/public/v1/users";
        String name = sheet.getRow(1).getCell(0).getStringCellValue();
        String gender = sheet.getRow(1).getCell(1).getStringCellValue();
        String email = sheet.getRow(1).getCell(2).getStringCellValue();
        String status = sheet.getRow(1).getCell(3).getStringCellValue();
        JSONObject request = new JSONObject();
        request.put("name",name);
        request.put("gender",gender);
        request.put("email",email);
        request.put("status",status);

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