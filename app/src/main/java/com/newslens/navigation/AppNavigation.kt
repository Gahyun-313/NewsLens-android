package com.newslens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

// ── Route 상수 ──────────────────────────────────────────────────
// 화면 경로를 문자열 상수로 관리
object Route {
    const val INTERNATIONAL = "international"
    const val DOMESTIC      = "domestic"
    const val MENU          = "menu"
    const val MY_PAGE       = "my_page"
}

// ── BottomNavItem ───────────────────────────────────────────────
// 하단 탭 각각의 정보를 담는 데이터 클래스
data class BottomNavItem(
    val route: String,       // 이동할 화면 경로
    val label: String,       // 탭 레이블
    val icon: ImageVector,   // 탭 아이콘
)

// 하단 탭 목록 (순서 = 탭 순서)
val bottomNavItems = listOf(
    BottomNavItem(Route.INTERNATIONAL, "해외", Icons.Default.Public),
    BottomNavItem(Route.DOMESTIC,      "국내", Icons.Default.Home),
    BottomNavItem(Route.MENU,          "메뉴", Icons.Default.Menu),
    BottomNavItem(Route.MY_PAGE,       "마이", Icons.Default.Person),
)

// ── NewsLensBottomBar ───────────────────────────────────────────
// 앱 하단 네비게이션 바
@Composable
fun NewsLensBottomBar(navController: NavHostController) {
    // 현재 백스택 엔트리를 구독해서 선택된 탭 실시간 반영
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        bottomNavItems.forEach { item ->
            // 현재 경로가 해당 탭의 route와 일치하면 선택 상태
            val isSelected = currentDestination?.hierarchy
                ?.any { it.route == item.route } == true

            NavigationBarItem(
                selected = isSelected,
                onClick  = {
                    navController.navigate(item.route) {
                        // 탭 전환 시 백스택 루트까지 정리 (중복 쌓임 방지)
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true  // 탭 상태 저장 (스크롤 위치 등)
                        }
                        launchSingleTop = true  // 같은 탭 중복 생성 방지
                        restoreState    = true  // 돌아올 때 저장된 상태 복원
                    }
                },
                icon  = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
            )
        }
    }
}