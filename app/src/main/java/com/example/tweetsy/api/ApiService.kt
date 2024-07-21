package com.example.tweetsy.api

import com.example.tweetsy.models.JsonBinResponse
import com.example.tweetsy.models.Tweet
import com.example.tweetsy.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiService {
    @Headers("X-Master-Key: ${Constants.API_KEY}")
    @GET("${Constants.CATEGORIES_BIN_ID}")
    suspend fun getCategories() : Response<JsonBinResponse<List<String>>>

    @Headers("X-Master-Key: ${Constants.API_KEY}")
    @GET("${Constants.TWEETS_BIN_ID}")
    suspend fun getTweetsByCategory(@Header("X-JSON-Path")category: String) : Response<JsonBinResponse<List<Tweet>>>
}