package com.newslens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.newslens.navigation.NewsLensNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // 상태바·네비게이션바까지 화면 확장
        setContent {
            NewsLensNavHost()
        }
    }
}