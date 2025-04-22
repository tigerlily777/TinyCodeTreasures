package com.example.tinycodetreasures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tinycodetreasures.ui.theme.TinyCodeTreasuresTheme

class SecondActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("SecondActivity: onCreate() called")
        setContent {
            TinyCodeTreasuresTheme {
                // Your UI code here
            }
        }
    }
}