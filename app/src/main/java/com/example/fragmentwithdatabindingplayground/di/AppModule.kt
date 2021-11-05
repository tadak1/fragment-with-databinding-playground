package com.example.fragmentwithdatabindingplayground.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

interface GithubAPI {
    @GET("users/{user_name}")
    suspend fun fetchUserDetail(@Path("user_name") id: String): Response<GithubUserDetail>
}

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGitHubApi(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideGithubRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}

data class GithubUserDetail(
    val avatar_url: String,
    val bio: String?,
    val blog: String,
    val company: String?,
    val created_at: String,
    val email: String?,
    val events_url: String,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: Boolean?,
    val html_url: String,
    val id: Int,
    val location: String?,
    val login: String,
    val name: String,
    val node_id: String,
    val organizations_url: String,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val twitter_username: String?,
    val type: String,
    val updated_at: String,
    val url: String
)

sealed class Resource<T>(data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)
    class Loading<T>() : Resource<T>()
}

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
