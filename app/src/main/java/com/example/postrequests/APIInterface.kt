package com.example.postrequests


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIInterface {
    @GET("/test/")
    fun getit(): Call<List<Users.UserData>>
    @POST("/test/")
    fun added(@Body userdat:Users.UserData):Call<Users.UserData>

}