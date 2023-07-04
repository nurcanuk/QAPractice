package step_defs.api_step_defs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.asynchttpclient.util.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIdeneme {


    Response response;
    @Test
    public void get() {

        RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";

        response=given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI);

       // response.prettyPrint();

        String responseBody= response.getBody().asString();
        JsonPath json=new JsonPath(responseBody);

        Assert.assertEquals(json.getString("title"),"Git Pocket Guide");
        Assert.assertEquals(json.getString("author"),"Richard E. Silverman");

        System.out.println( json.getString("subTitle"));


    }

    @Test
    public void get01() {

        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI)
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", equalTo("Git Pocket Guide"),
                "author",  equalTo("Richard E. Silverman"),
                "subTitle",equalTo("A Working Introduction"));

    }
}
