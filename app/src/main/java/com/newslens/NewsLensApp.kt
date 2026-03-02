package com.newslens

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * NewsLens Application 클래스
 *
 * @HiltAndroidApp
 * - Hilt 의존성 주입 시스템을 초기화하는 진입점
 * - 이 어노테이션이 없으면 앱 전체에서 Hilt를 사용할 수 없음
 * - Hilt가 필요한 컴포넌트들의 코드를 컴파일 타임에 자동 생성함
 *
 * Application 클래스를 사용하는 이유
 * - 앱이 시작될 때 가장 먼저 생성되는 클래스
 * - Hilt는 앱 생명주기 전체에서 동작해야 하므로 여기서 초기화
 */
@HiltAndroidApp
class NewsLensApp : Application()
