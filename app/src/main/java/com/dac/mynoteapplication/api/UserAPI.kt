package com.dac.mynoteapplication.api

import com.dac.mynoteapplication.models.UserRequest
import com.dac.mynoteapplication.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("/users/signup")
    suspend fun signup(@Body userRequest: UserRequest) : Response<UserResponse>

    @POST("/users/signin")
    suspend fun signin(@Body userRequest: UserRequest) : Response<UserResponse>

}