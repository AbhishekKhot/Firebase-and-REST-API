package com.example.task.network

import com.example.task.model.NewsResponse
import com.example.task.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("v2/everything")
    suspend fun getNewsArticles(
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}