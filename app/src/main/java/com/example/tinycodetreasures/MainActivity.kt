package com.example.tinycodetreasures

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("MainActivity: onCreate() called")
    }
}

fun startActivity(context: Context) {
    val intent = Intent(context, SecondActivity::class.java)
    intent.putExtra("message", "Hello from the first activity!")
    context.startActivity(intent)
    println("Starting activity with intent: $intent")
}

fun startService(context: Context) {
    val intent = Intent(context, FileDownloadService::class.java)
    context.startService(intent)
    println("Starting service with intent: $intent")
}

fun startBroadcast(context: Context) {
    val intent = Intent(context, MyBroadcastReceiver::class.java)
    context.sendBroadcast(intent)
    println("Sending broadcast with intent: $intent")
}
