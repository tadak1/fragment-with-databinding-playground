package com.example.fragmentwithdatabindingplayground.repository

import com.example.fragmentwithdatabindingplayground.model.GithubUserDetail
import com.example.fragmentwithdatabindingplayground.model.Resource
import com.example.fragmentwithdatabindingplayground.network.GithubAPI
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val api: GithubAPI
) {

    suspend fun getUserById(id: String): Resource<GithubUserDetail> {
        val response = try {
            val response = api.fetchUserDetail(id)
            if (response.isSuccessful) response.body()!! else throw HttpException(response)
        } catch (e: Exception) {
            return Resource.Error(message = "Error occured")
        }
        return Resource.Success(response)
    }
}
