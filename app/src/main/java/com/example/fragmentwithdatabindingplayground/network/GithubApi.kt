package com.example.fragmentwithdatabindingplayground.network

import com.example.fragmentwithdatabindingplayground.model.GithubUserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("users/{user_name}")
    suspend fun fetchUserDetail(@Path("user_name") id: String): Response<GithubUserDetail>
}
