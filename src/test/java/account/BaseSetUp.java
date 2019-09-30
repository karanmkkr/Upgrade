package account;

import commons.RestUtilities;
import constants.HeaderConstants;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import model.AuthenticationBody;
import org.testng.annotations.BeforeClass;

import static constants.HeaderConstants.SOURCE_ID_VALUE;
import static constants.HeaderConstants.UUID_VALUE;
import static constants.Path.BASE_PATH;
import static io.restassured.http.ContentType.JSON;

public class BaseSetUp extends RestUtilities {

    String message = null;
    int code = 0;
    String httpStatus = null;

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    AuthenticationBody auth = new AuthenticationBody();

    @BeforeClass
    public void setUp() {
        reqSpec = RestUtilities.getRequestSpecification();
        reqSpec.basePath(BASE_PATH);
        reqSpec.contentType(JSON);
        reqSpec.header(HeaderConstants.SOURCE_ID, SOURCE_ID_VALUE);
        reqSpec.header(HeaderConstants.UUID,UUID_VALUE);
        resSpec = RestUtilities.getResponseSpecification();
    }
}
