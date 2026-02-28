# Contributing Guide

NewsLens 프로젝트의 Git 운영 규칙입니다.  
Compose + 멀티모듈 + MVVM 기준으로 작성되었습니다.

---

## 1. Branch Strategy

### 브랜치 구조

```
main ← release/* ← develop ← feature/*
                              fix/*
                              refactor/*
                              docs/*
main ← hotfix/*
```

| 브랜치 | 역할 | 분기 기준 | 병합 방향 |
|--------|------|-----------|-----------|
| `main` | 배포 기준 | — | PR만 허용 |
| `develop` | 개발 통합 | — | PR만 허용 |
| `feature/<name>` | 기능 개발 | develop | → develop |
| `fix/<name>` | 버그 수정 | develop | → develop |
| `refactor/<name>` | 구조 개선 | develop | → develop |
| `release/<version>` | 배포 준비 | develop | → main |
| `hotfix/<name>` | 긴급 수정 | main | → main → develop |

### 네이밍 규칙

- 소문자 + 하이픈(`-`) 사용
- 이슈 번호가 있으면 포함: `fix/123-feed-scroll-crash`

```
feature/news-feed-screen
feature/offline-cache-ttl
fix/room-ttl-not-expiring
refactor/viewmodel-stateflow
docs/update-readme
docs/contributing-guide
release/1.0.0
hotfix/crash-on-cold-start
```

### Merge 방식

| 대상 | 방식 |
|------|------|
| feature / fix / refactor / docs → develop | **Squash Merge** |
| release / hotfix → main | **Merge Commit** |

> `hotfix`는 main 병합 후 **develop 역반영 필수**

---

## 2. Commit Message

[Conventional Commits](https://www.conventionalcommits.org/) 규칙을 따릅니다.

### 형식

```
<type>(<scope>): <subject>
```

### Type

| Type | 설명 |
|------|------|
| `feat` | 새로운 기능 추가 |
| `fix` | 버그 수정 |
| `refactor` | 기능 변화 없는 코드 개선 |
| `perf` | 성능 개선 |
| `style` | 코드 스타일 변경 (동작 무관) |
| `test` | 테스트 코드 추가/수정 |
| `docs` | 문서 수정 |
| `chore` | 빌드/설정/의존성 변경 |
| `revert` | 이전 커밋 되돌리기 |

### Scope (책임 영역 기준, 1개만 사용)

| Scope | 대상 |
|-------|------|
| `news-feed` | 뉴스 피드 화면/로직 |
| `article-detail` | 기사 상세 화면/로직 |
| `search` | 검색 화면/로직 |
| `reading-mission` | 읽기 미션 기능 |
| `navigation` | NavGraph, BottomNav, 화면 이동 구조 |
| `ui` | 공용 컴포넌트, Theme, 디자인 시스템 |
| `state-management` | ViewModel, UiState, StateFlow/SharedFlow |
| `build` | Gradle, 멀티모듈, 의존성, CI |

### 예시

```bash
# Good
feat(navigation): bottom navigation 기반 app navigation graph 초기 구성
feat(news-feed): 뉴스 피드 목록 UI 및 로딩 상태 추가
fix(navigation): 탭 재선택 시 back stack 누적 문제 수정
refactor(state-management): UiState 구조 단순화 및 업데이트 로직 정리
chore(build): 멀티모듈 설정 및 version catalog 정리
```

---

## 3. Pull Request

### PR 제목

커밋 메시지와 동일한 형식 사용

```
feat(navigation): bottom navigation 기반 app navigation graph 초기 구성
```

### PR 체크리스트

- [ ] 커밋 메시지 규칙 준수
- [ ] 빌드 및 실행 확인
- [ ] UI 변경 시 스크린샷 첨부
- [ ] 영향 범위 및 리스크 명시
- [ ] 관련 이슈 연결

---

## 4. Workflow

### 기능 개발

```bash
git checkout develop
git checkout -b feature/news-feed-screen
# 작업
git push origin feature/news-feed-screen
# PR: feature/news-feed-screen → develop (Squash Merge)
```

### 배포

```bash
git checkout develop
git checkout -b release/1.0.0
# QA 및 버그 fix만 허용
# PR: release/1.0.0 → main (Merge Commit)
git tag v1.0.0
```

### 긴급 수정

```bash
git checkout main
git checkout -b hotfix/crash-on-cold-start
# 수정
# PR: hotfix → main (Merge Commit)
# main 변경 → develop 역반영 필수
```

---

## 5. 원칙

- `main`, `develop` 직접 push 금지
- 1 브랜치 = 1 책임
- 커밋 단위 = 동작 가능한 최소 변경
- 로그만 봐도 변경 내용을 파악할 수 있게 작성