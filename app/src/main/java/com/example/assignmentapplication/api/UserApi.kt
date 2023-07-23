package com.example.assignmentapplication.api

import com.example.assignmentapplication.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    // BASE_URL + "/users?size={size}"
    @GET("users")
    suspend fun getUsers(@Query("size") size: Int) : Response<List<User>>

}