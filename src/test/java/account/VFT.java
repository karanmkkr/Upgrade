package account;

import ResponseObjects.LoanDetails;
import ResponseObjects.UserDetails;
import constants.EndPoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class VFT extends BaseSetUp {

    @Test
    public void validate_ValuesReturned_when_inAValidScenario() {

        auth.setUsername("coding.challenge.login@upgrade.com");
        auth.setPassword("On$3XcgsW#9q");
        Response response =
                given()
                        .log()
                        .all()
                        .spec(reqSpec)
                        .body(auth)
                .when()
                        .post(EndPoints.LOGIN)
                .then()
                        .spec(resSpec)
                        .extract()
                        .response();

        UserDetails user = response.as(UserDetails.class, ObjectMapperType.GSON);

        SoftAssert newSoftAssert = new SoftAssert();
        newSoftAssert.assertEquals(user.getFirstName(), "Ian");
        newSoftAssert.assertTrue(user.getUserId()== 9114917,"same userId");

        List<LoanDetails> details = user.getLoansInReview();
        for (LoanDetails detail : details) {
            newSoftAssert.assertTrue(detail.getId() == 9545966,"same Loan application Id");
            newSoftAssert.assertEquals(detail.getProductType(), "PERSONAL_LOAN");
            newSoftAssert.assertEquals(detail.getSourceSystem(), "BORROWER_FUNNEL_V2");
            newSoftAssert.assertEquals(detail.getPurpose(), "CREDIT_CARD");
            newSoftAssert.assertEquals(detail.getHasOpenBackendCounter().booleanValue(), false);
        }
        newSoftAssert.assertAll();
    }
}
