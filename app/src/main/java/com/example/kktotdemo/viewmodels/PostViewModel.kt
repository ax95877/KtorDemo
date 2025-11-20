package com.example.kktotdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kktotdemo.model.Post
import com.example.kktotdemo.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class PostUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val errorMessage: String? = null,
)
class PostViewModel (private val repository: PostRepository=PostRepository()) : ViewModel(){

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()
    init {
        loadPosts()
    }

    fun loadPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            repository.getPosts()
                .onSuccess { posts ->
                    _uiState.value = _uiState.value.copy(isLoading = false, posts = posts)
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = it.message)
                }

        }
    }

    fun createPost(title: String, body: String){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            repository.createPost(title, body)
                .onSuccess { newPost ->
                    _uiState.value = _uiState.value.copy(isLoading = false, posts = listOf(newPost) + _uiState.value.posts)
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = it.message)
                }

        }

    }
    fun updatePost(id:Int,title: String, body: String){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            repository.updatePost(id,title, body)
                .onSuccess { newPost ->
                    _uiState.value = _uiState.value.copy(isLoading = false, posts = listOf(newPost) + _uiState.value.posts)
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = it.message)
                }

        }

    }

    fun deletePost(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            repository.deletePost(id)
                .onSuccess { newPost ->
                    _uiState.value = _uiState.value.copy(isLoading = false, posts =  _uiState.value.posts.filter { it.id != id })
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = it.message)
                }

        }

    }



}