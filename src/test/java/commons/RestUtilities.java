package commons;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static constants.Path.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RestUtilities {

    public static String ENDPOINT;
    public static RequestSpecBuilder REQUEST_BUILDER;
    public static RequestSpecification REQUEST_SPEC;
    public static ResponseSpecBuilder RESPONSE_BUILDER;
    public static ResponseSpecification RESPONSE_SPEC;

    public static void setEndPoint(String epoint) {
        ENDPOINT = epoint;
    }

    public static RequestSpecification getRequestSpecification() {
        REQUEST_BUILDER = new RequestSpecBuilder();
        REQUEST_BUILDER.setBaseUri(BASE_URI);
        REQUEST_SPEC = REQUEST_BUILDER.build();
        return REQUEST_SPEC;
    }

    public static RequestSpecification addHeaders(RequestSpecification rspec, Map<String, String> header ) {
        return rspec.headers(header);
    }

    public static ResponseSpecification getResponseSpecification() {
        RESPONSE_BUILDER = new ResponseSpecBuilder();
        RESPONSE_BUILDER.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
        RESPONSE_SPEC = RESPONSE_BUILDER.build();
        return RESPONSE_SPEC;
    }

    public static RequestSpecification createPathParam(RequestSpecification rspec,
                                                       String param, String value) {
        return rspec.pathParam(param, value);
    }

    public static RequestSpecification createQueryParam(RequestSpecification rspec,
                                                       String param, String value) {
        return rspec.queryParam(param, value);
    }

    public static RequestSpecification createQueryParam(RequestSpecification rspec,
                                                       Map<String, String> queryMap) {
        return rspec.queryParams(queryMap);
    }

    public static Response getResponse() {
        return given().get(ENDPOINT);
    }

    public static Response getResponse(RequestSpecification reqSpec, String type) {
        REQUEST_SPEC.spec(reqSpec);
        Response response = null ;

        if (type.equalsIgnoreCase("get")) {
            response = given().spec(REQUEST_SPEC).get(ENDPOINT);
        } else if (type.equalsIgnoreCase("post")) {
            response = given().spec(REQUEST_SPEC).post(ENDPOINT);
        } else if (type.equalsIgnoreCase("put")) {
            response = given().spec(REQUEST_SPEC).put(ENDPOINT);
        } else if (type.equalsIgnoreCase("delete")) {
            response = given().spec(REQUEST_SPEC).delete(ENDPOINT);
        } else {
            System.out.println("Type not supported");
        }

        response.then().log().all();
        response.then().spec(RESPONSE_SPEC);
        return response;
    }

    public static JsonPath getJsonPath(Response res){
        String path = res.asString();
        return new JsonPath(path);
    }

    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    public static void setContentType(ContentType type){
        given().contentType(type);
    }
}

