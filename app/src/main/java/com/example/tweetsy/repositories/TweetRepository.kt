package com.example.tweetsy.repositories

import com.example.tweetsy.api.ApiService
import javax.inject.Inject

class TweetRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getTweetsByCategory(category: String) =
        apiService.getTweetsByCategory("\$..[?(@.category==\"${category}\")]")
}