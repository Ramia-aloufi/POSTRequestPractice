package com.example.postrequests

//data class UserData(val name:String,val location:String)


class Users {

    var data: List<UserData>? = null

    class UserData {

        var name: String? = null

        var location: String? = null

        constructor(name: String?, location: String?) {
            this.name = name
            this.location = location
        }
    }
}