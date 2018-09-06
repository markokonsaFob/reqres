package com.fobsolutions.utils.data

import groovy.json.internal.LazyMap

/**
 * Created by FOB Solutions
 *
 * User data from HTTP Calls /api/users
 */
class UserData {

    String firstname
    String lastname
    String avatar
    String id

    UserData(LazyMap lazyMap) {
        this.firstname = lazyMap.get("first_name")
        this.lastname = lazyMap.get("last_name")
        this.avatar = lazyMap.get("avatar")
        this.id = lazyMap.get("id")
    }

}
