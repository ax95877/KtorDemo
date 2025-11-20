package com.example.kktotdemo.model

import kotlinx.serialization.Serializable

@Serializable
data class Post (
    val id:Int,
    val userId:Int,
    val title:String,
    val body:String
)

@Serializable
data class CreatePostRequest(
    val title: String,
    val body: String,
    val userId: Int
)

@Serializable
data class PostUpdateRequest(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)