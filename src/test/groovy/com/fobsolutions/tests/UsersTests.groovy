package com.fobsolutions.tests

import com.fobsolutions.utils.data.UserData
import groovy.json.internal.LazyMap
import io.restassured.path.json.JsonPath
import io.restassured.response.Response
import org.junit.Test

/**
 * Created by FOB Solutions
 */
class UsersTests extends BaseTest {

    private static String USERS_URL = ENVIRONMENT_URL + "users"

    @Test
    void testGetListOfUsers() {
        LazyMap map = [:]
        Response response = doGet(map, USERS_URL)

        JsonPath responseJson = getJson(response)

        // Verifications
        assert responseJson.get("page") == 1
        assert responseJson.get("per_page") >= 1
        assert responseJson.get("total") >= 1
        assert responseJson.get("total_pages") >= 1
        assert !(responseJson.get("data") as List).isEmpty()
    }

    @Test
    void testGetSingleUser() {
        UserData user = getRandomUser()

        LazyMap map = ["id": user.getId()]
        Response response = doGet(map, USERS_URL + "/{id}")

        JsonPath responseJson = getJson(response)

        // Verifications
        LazyMap data = responseJson.get("data")
        assert new UserData(data).properties == user.properties
    }

    /**
     * Gets random user from list
     * @return
     */
    private static UserData getRandomUser() {
        LazyMap map = [:]
        Response response = doGet(map, USERS_URL)

        JsonPath responseJson = getJson(response)
        List dataList = (responseJson.get("data") as List)
        Collections.shuffle(dataList)
        new UserData(dataList.first() as LazyMap)
    }

}
