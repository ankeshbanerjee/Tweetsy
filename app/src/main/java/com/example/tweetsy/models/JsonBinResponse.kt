package com.example.tweetsy.models

data class JsonBinResponse<T>(
    val record: T,
    val metadata: Metadata
)

data class Metadata(
    val id: String,
    val private: Boolean,
    val createdAt: String,
    val name: String
)