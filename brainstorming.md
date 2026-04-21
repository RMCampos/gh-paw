# GitHub CLI (Kotlin) - Brainstorming & Plan

## 🎯 Objective
Build a custom, fully functional GitHub Command Line Interface (CLI) using Kotlin. This project will serve as a learning journey to master Kotlin, CLI development, and API integration, while providing a useful daily tool for GitHub interactions.

## 🚀 Features Scope (Initial Focus)
We will focus on the following core areas to provide a robust initial version:

*   **Pull Requests (`pr`)**
    *   List open PRs.
    *   Create new PRs.
    *   Merge PRs.
*   **Issues (`issue`)**
    *   List open issues.
    *   Create new issues.
    *   Add comments to existing issues.
*   **Repositories (`repo`)**
    *   List repositories.
    *   View detailed information about a repository.
    *   Clone repositories.
*   **Actions (`action` / `run`)**
    *   List recent workflow runs.
    *   Trigger a new workflow run.

## 🛠️ Technology Stack & Deployment
*   **Language:** Kotlin
*   **Deployment Target:** **GraalVM Native Image** (Crucial requirement for fast startup, cross-environment compatibility, and standalone binary creation). All selected tools must be GraalVM-compliant.
*   **CLI Framework:** **Clikt** (GraalVM compatible, intuitive, type-safe).
*   **HTTP Client:** **Ktor Client** (GraalVM compatible. We will use a native-image friendly engine like CIO or OkHttp).
*   **JSON Serialization:** **kotlinx.serialization** (GraalVM compatible as it avoids reflection).
*   **Styling & Output:** **Mordant** (GraalVM compatible, pairs perfectly with Clikt).
*   **Build Tool:** Gradle (Kotlin DSL) with the GraalVM Native Image plugin.

## 🔐 Authentication
*   **Initial Approach:** Personal Access Token (PAT).
    *   The CLI will prompt the user to input their PAT.
    *   The token will be securely stored locally (e.g., in `~/.config/gh-paw/config.json` or utilizing a secure credential store if available).
    *   Future enhancement: OAuth Device Authorization flow for better UX.

## 🏗️ Architecture & Project Structure (Proposed)
```text
gh-paw/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── cli/          # Clikt Command definitions (e.g., PrCommand, IssueCommand)
│   │   │   ├── api/          # Ktor client setup and GitHub API wrappers
│   │   │   ├── models/       # kotlinx.serialization data classes representing GitHub entities
│   │   │   ├── config/       # Token management and configuration loading
│   │   │   └── Main.kt       # Application entry point
│   └── test/                 # Unit and integration tests
├── build.gradle.kts
└── ...
```

## 🛤️ Implementation Phases

*   **Phase 1: Foundation & Authentication**
    *   Initialize Gradle project with Kotlin.
    *   Set up Clikt for basic command parsing (e.g., `gh-paw auth login`).
    *   Implement PAT storage and retrieval.
    *   Configure Ktor Client with authentication headers.
*   **Phase 2: The `repo` Command**
    *   Create data models for Repositories.
    *   Implement API calls to fetch repo details.
    *   Build `gh-paw repo view` and `gh-paw repo list` commands.
*   **Phase 3: Issues & Pull Requests**
    *   Implement models and API calls for Issues and PRs.
    *   Build `gh-paw issue list|create` and `gh-paw pr list|create` commands.
*   **Phase 4: Actions & Refinement**
    *   Implement Actions functionality.
    *   Introduce Mordant for styling outputs (tables for lists, colors for status).
    *   Write tests and refine error handling.
