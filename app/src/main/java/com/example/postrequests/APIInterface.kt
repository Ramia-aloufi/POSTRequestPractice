package com.example.postrequests


import com.example.postrequests.Users
import retrofit2.Call
import retrofit2.http.*


interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getit(): Call<List<Users.UserData>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun added(@Body userdat: Users.UserData):Call<Users.UserData>

    @Headers("Content-Type: application/json")
    @PUT("/test/{id}")
    fun update(@Path("id") id:Int, @Body userdat:Users.UserData):Call<Users.UserData>

    @Headers("Content-Type: application/json")
    @DELETE("/test/{id}")
    fun delete(@Path("id") id:Int):Call<Void>

}