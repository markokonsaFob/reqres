package com.fobsolutions.tests

import groovy.json.internal.LazyMap
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import org.junit.Test

/**
 * Created by FOB Solutions
 */
class RegisterTests extends BaseTest {

    private static String REGISTER_URL = ENVIRONMENT_URL + "register"

    @Test
    void testSuccessfulRegister() {
        LazyMap parameters = ["email": "marko@fob", "password": "fobsolutions"]
        Response response = doPost(parameters, REGISTER_URL)

        JsonPath responseJson = getJson(response)

        // Verifications
        assert response.time() < 2000l
        assert response.getStatusCode() == 201
        assert !responseJson.get("token").toString().isEmpty()
    }

    @Test
    void testUnsuccessfulRegisterWithoutPassword() {
        LazyMap parameters = ["email": "marko@fob"]
        Response response = doPost(parameters, REGISTER_URL)

        JsonPath responseJson = getJson(response)

        // Verifications
        assert response.time() < 2000l
        assert response.getStatusCode() == 400
        assert responseJson.get("error").toString() == 'Missing password'
    }

    @Test
    void testUnsuccessfulLoginWithoutEmail() {
        LazyMap parameters = ["password": "fobsolutions"]
        Response response = doPost(parameters, REGISTER_URL)

        JsonPath responseJson = getJson(response)

        // Verifications
        assert response.time() < 2000l
        assert response.getStatusCode() == 400
        assert responseJson.get("error").toString() == 'Missing email or username'
    }

}

