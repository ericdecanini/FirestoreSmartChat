package com.example.firestoresmartchat

import com.google.firebase.Timestamp
import java.util.*

data class ChatMessage(
    val text: String,
    val user: String,
    val timestamp: Date
)