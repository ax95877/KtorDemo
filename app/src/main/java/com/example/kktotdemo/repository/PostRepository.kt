package com.example.kktotdemo.repository

import com.example.kktotdemo.model.Post
import com.example.kktotdemo.services.ApiService

class PostRepository(private val apiService: ApiService=ApiService()) {
    suspend fun getPosts(): Result<List<Post>> =runCatching {
        apiService.getPosts()
    }
    suspend fun createPost(title: String,body: String): Result<Post> =runCatching {
        apiService.createPost(title=title,body=body)
    }
    suspend fun updatePost(id:Int,title: String,body: String): Result<Post> =runCatching {
        apiService.updatePost(id=id,title=title,body=body)
    }

    suspend fun deletePost(id:Int): Result<Unit> =runCatching {
        apiService.deletePost(id=id)
    }
}