package com.newslens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.newslens.feature.domestic.ui.DomesticScreen
import com.newslens.feature.international.ui.InternationalScreen
import com.newslens.feature.menu.ui.MenuScreen
import com.newslens.feature.mypage.ui.MyPageScreen

// 앱 전체 네비게이션 설정
// 각 Route에 어떤 Screen을 연결할지 정의
@Composable
fun NewsLensNavHost() {
    val navController = rememberNavController()

    Scaffold(
        // 하단 네비게이션 바 고정
        bottomBar = { NewsLensBottomBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController    = navController,
            startDestination = Route.INTERNATIONAL, // 앱 시작 화면
            modifier         = Modifier
                .fillMaxSize()
                .padding(innerPadding), // BottomBar 높이만큼 콘텐츠 패딩
        ) {
            composable(Route.INTERNATIONAL) { InternationalScreen() }
            composable(Route.DOMESTIC)      { DomesticScreen() }
            composable(Route.MENU)          { MenuScreen() }
            composable(Route.MY_PAGE)       { MyPageScreen() }
        }
    }
}