package com.example.tweetsy.repositories

import com.example.tweetsy.api.ApiService
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getCategories() = apiService.getCategories()
}