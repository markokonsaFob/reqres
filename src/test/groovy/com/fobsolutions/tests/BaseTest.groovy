package com.fobsolutions.tests

import com.fobsolutions.utils.ConfigurationParser
import groovy.json.internal.LazyMap
import io.restassured.RestAssured
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

/**
 * Created by FOB Solutions
 */
class BaseTest {

    static String ENVIRONMENT_URL = ConfigurationParser.getEnvironmentUrl()

    /**
     * Do Post HTTP request
     * @param params
     * @param url
     * @return
     */
    static Response doPost(LazyMap params, String url) {
        Response response = given()
                .formParams(params)
                .log().all()
                .when()
                .post(url)

        // log body
        response.then().log().body()

        return response
    }

    /**
     * Do GET HTTP Request
     * @param params
     * @param url
     * @return
     */
    static Response doGet(LazyMap params, String url) {
        Response response = given()
                .get(url, params)

        // log body
        response.then().log().body()

        return response
    }

    /**
     * Get JSON from response
     * @param response
     * @return
     */
    static JsonPath getJson(Response response) {
        response.then().extract().response().jsonPath()
    }

    /**
     * Given method to set request specification
     * Set content type to url encoded
     * Set charset to UTF-8
     * @return
     */
    private static RequestSpecification given() {
        RestAssured.given().contentType("application/x-www-form-urlencoded; charset=UTF-8")
    }
}
