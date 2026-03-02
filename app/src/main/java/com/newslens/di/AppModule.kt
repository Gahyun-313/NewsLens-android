package com.newslens.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 앱 전역 Hilt DI 모듈
 *
 * @Module
 * - 이 클래스가 Hilt 의존성 제공 모듈임을 선언
 * - @Provides 또는 @Binds 함수를 통해 의존성을 등록함
 *
 * @InstallIn(SingletonComponent::class)
 * - 이 모듈의 의존성을 어느 스코프에 설치할지 결정
 * - SingletonComponent: 앱 생명주기와 동일 (앱 시작 ~ 종료)
 * - 앱 전체에서 동일한 인스턴스를 공유해야 하는 것들을 여기에 등록
 *   예) Retrofit, OkHttpClient, NewsDatabase, Repository
 *
 * 추후 추가 예정:
 * - NetworkModule: Retrofit, OkHttpClient, NewsApiService
 * - DatabaseModule: NewsDatabase, DAO
 * - RepositoryModule: Repository 인터페이스 → 구현체 바인딩
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule
