package account;

import commons.RestUtilities;
import constants.EndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class BVT extends BaseSetUp {

    @Test
    public void validate_status_post_workflow_gives_correct_response(){
        auth.setUsername("coding.challenge.login@upgrade.com");
        auth.setPassword("On$3XcgsW#9q");

        given()
                .log()
                .all()
                .spec(reqSpec)
                .body(auth)
        .when()
                .post(EndPoints.LOGIN)

        .then()
                .spec(resSpec)
                .statusCode(200)
                .body("userId",equalTo(9114917))
                .body("loansInReview.productType",Matchers.hasItem("PERSONAL_LOAN"));
    }

    @Test
    public void validate_statusCodeVerification_WrongCredentials(){
        auth.setUsername("xyz");
        auth.setPassword("xyz");

        Response response =
                given()
                        .log()
                        .all()
                        .spec(reqSpec)
                        .body(auth)
                .when()
                        .post(EndPoints.LOGIN)
                .then()
                        .statusCode(401)
                        .spec(resSpec)
                        .extract()
                        .response();

        JsonPath jsPath = RestUtilities.getJsonPath(response);

        code = jsPath.get("code");
        message = jsPath.get("message");
        httpStatus = jsPath.get("httpStatus");

        Assert.assertEquals(code,100039);
        Assert.assertEquals(httpStatus,"UNAUTHORIZED");
        Assert.assertEquals(message,"We're sorry, the credentials you've provided are incorrect. Please try again");
    }

}
