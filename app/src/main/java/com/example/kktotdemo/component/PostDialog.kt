package com.example.kktotdemo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@Composable
fun PostDialog(
    title:String,
    initialTitle:String="",
    initialBody:String="",
    onDismiss:()->Unit,
    onConfirm:(title:String,body:String)->Unit
) {
    var titleText by remember { mutableStateOf(initialTitle) }
    var bodyText by remember { mutableStateOf(initialBody) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value=titleText,
                    onValueChange = {titleText=it},
                    label = { Text("Title") },
                )
                OutlinedTextField(
                    value=bodyText,
                    onValueChange = {bodyText=it},
                    label = { Text("Body") },
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if(titleText.isNotBlank() && bodyText.isNotBlank()) {
                        onConfirm(titleText,bodyText)
                    }
                }

            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }


    )

}