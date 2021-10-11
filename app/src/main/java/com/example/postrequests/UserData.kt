package com.example.postrequests

//data class UserData(val name:String,val location:String)


class Users {

    var data: List<UserData>? = null

    class UserData(var pk: Int?, var name: String?, var location: String?) {

    }
}