package account;

import commons.RestUtilities;
import constants.EndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.testng.annotations.Test;

import static constants.HeaderConstants.SOURCE_ID;
import static constants.HeaderConstants.UUID;
import static constants.Messages.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.XML;

public class NFT extends BaseSetUp{

    @Test
    public void validate_IncorrectContentType() {

        auth.setUsername("coding.challenge.login@upgrade.com");
        auth.setPassword("On$3XcgsW#9q");

        Response response =
                given()
                        .log()
                        .all()
                        .spec(reqSpec)
                        .body(auth)
                .when()
                        .contentType(XML)
                        .post(EndPoints.LOGIN)
                .then()
                        .statusCode(500)
                        .spec(resSpec)
                        .extract()
                        .response();



        JsonPath jsPath = RestUtilities.getJsonPath(response);

        code = jsPath.get("code");
        message = jsPath.get("message");
        httpStatus = jsPath.get("httpStatus");

        Assert.assertEquals(code, 0);
        Assert.assertEquals(httpStatus, null);
        Assert.assertEquals(message, UNSUPPORTED_CONTENT_TYPE);
    }

    @Test
    public void validate_Missing_SourceID() {

        auth.setUsername("coding.challenge.login@upgrade.com");
        auth.setPassword("On$3XcgsW#9q");

        Response response =
                given()
                        .log()
                        .all()
                        .header(SOURCE_ID," ")
                        .spec(reqSpec)
                        .body(auth)
                .when()
                        .post(EndPoints.LOGIN)

                .then()
                        .statusCode(500)
                        .spec(resSpec)
                        .extract()
                        .response();

        JsonPath jsPath = RestUtilities.getJsonPath(response);

        message = jsPath.get("message");
        Assert.assertEquals(message, MISSING_SOURCE_ID);
    }

    @Test
    public void validate_Missing_CorrID() {

        auth.setUsername("coding.challenge.login@upgrade.com");
        auth.setPassword("On$3XcgsW#9q");

        Response response =
                given()
                        .log()
                        .all()
                        .header(UUID," ")
                        .spec(reqSpec)
                        .body(auth)
                .when()
                        .post(EndPoints.LOGIN)
                .then()
                        .statusCode(500)
                        .spec(resSpec)
                        .extract().response();

        JsonPath jsPath = RestUtilities.getJsonPath(response);

        message = jsPath.get("message");
        Assert.assertEquals(message, MISSING_CORR_ID);
    }
}
