package com.example.kktotdemo.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kktotdemo.component.PostDialog
import com.example.kktotdemo.model.Post
import com.example.kktotdemo.viewmodels.PostViewModel

@Composable
fun PostScreen( viewModel: PostViewModel=viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var showCreateDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf<Post?>(null) }
    var showDeleteDialog by remember { mutableStateOf<Post?>(null) }



    Scaffold(
        topBar = { Text(text = "Posts-JSONPLACEHOLDER") },
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreateDialog=true }){
                Icon(Icons.Default.Add, contentDescription = "Create Post")
            }
        }


    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )

                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage!!,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.posts.isEmpty() -> {
                    Text(
                        text = "No posts",
                        modifier = Modifier.align(Alignment.Center)

                    )
                }

                uiState.posts.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(uiState.posts) { post ->
                            Column() {
                                Text(
                                    text = post.title,
                                    modifier = Modifier.padding(16.dp)
                                )
                                Row() {
                                    OutlinedButton(
                                        onClick = {
                                            showEditDialog = post
                                        }

                                    ) {
                                        Icon(
                                            Icons.Default.Edit,
                                            contentDescription = null
                                        )
                                        Text(text = "Edit")
                                    }
                                    OutlinedButton(
                                        onClick = {
                                            showDeleteDialog = post

                                        }

                                    ) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = null
                                        )
                                        Text(text = "Delete")
                                    }
                                }
                            }
                        }

                    }
                }

            }
        }

        if (showCreateDialog) {
            PostDialog(
                title = "Create Post",
                onDismiss = { showCreateDialog = false },
                onConfirm = { title, body ->
                    viewModel.createPost(title, body)
                    showCreateDialog = false
                }
            )
        }
        showEditDialog?.let { post ->
            PostDialog(
                title = "Edit Post",
                initialBody = post.body,
                initialTitle = post.title,
                onDismiss = { showEditDialog = null },
                onConfirm = { title, body ->
                    viewModel.updatePost(
                        id = post.id,
                        title = title,
                        body = body
                    )
                    showEditDialog = null
                }
            )
        }
        showDeleteDialog?.let { post ->
            AlertDialog(
                onDismissRequest = { showDeleteDialog = null },
                title = { Text(text = "Delete Post") },
                text = { Text(text = "Are you sure you want to delete this post?") },
                confirmButton = {
                    OutlinedButton(
                        onClick = {
                            viewModel.deletePost(post.id)
                            showDeleteDialog = null
                        }
                    ) {
                        Text(text = "Delete")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showDeleteDialog = null }
                    ) {
                        Text(text = "Cancel")
                    }
                }


            )
        }


    }
}