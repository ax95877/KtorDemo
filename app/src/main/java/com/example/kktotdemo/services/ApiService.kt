package com.example.kktotdemo.services


import com.example.kktotdemo.model.Post
import com.example.kktotdemo.model.CreatePostRequest
import com.example.kktotdemo.model.PostUpdateRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*


class ApiService {
    private val client= HttpClient(CIO){
        install(ContentNegotiation){
            json(Json{
                prettyPrint=true
                isLenient=true
                ignoreUnknownKeys=true
            }
            )
        }
    }
    private val baseUrl="https://jsonplaceholder.typicode.com"

    suspend fun getPosts():List<Post>{
        return client.get("$baseUrl/posts").body<List<Post>>().take(20)
    }

    suspend fun createPost(title:String,body:String):Post{
        return client.post("$baseUrl/posts"){
            contentType(ContentType.Application.Json)
            setBody(
                CreatePostRequest(
                    title = title,
                    body = body,
                    userId = 1
                )
            )
        }.body()

    }
    suspend fun updatePost(id:Int,title:String,body:String):Post{
        return client.put("$baseUrl/posts/$id"){
            contentType(ContentType.Application.Json)
            setBody(
                PostUpdateRequest(
                    id = id,
                    title = title,
                    body = body,
                    userId = 1
                )
            )
        }.body()

    }
    suspend fun deletePost(id:Int){
         client.delete("$baseUrl/posts/$id")
    }

}