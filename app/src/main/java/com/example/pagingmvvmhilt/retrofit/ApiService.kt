package com.example.pagingmvvmhilt.retrofit

import com.example.pagingmvvmhilt.models.model_users.GetUsers
import com.example.pagingmvvmhilt.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.USERS)
    suspend fun getUsers(@Query("page") page: Int, @Query("per_page") per_page: String): Response<GetUsers>

}