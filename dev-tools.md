# Development Tools & Commands

This guide covers the essential commands for developing, building, and running the `gh-paw` CLI.

## 🛠️ Environment Setup (SDKMAN)

We use **SDKMAN!** to manage Java and Gradle versions.

*   **Initialize SDKMAN (if not in path):**
    ```bash
    source "$HOME/.sdkman/bin/sdkman-init.sh"
    ```
*   **Install/Use correct Java version (GraalVM):**
    ```bash
    sdk install java 21.0.2-graalce
    sdk use java 21.0.2-graalce
    ```
*   **Install/Use Gradle:**
    ```bash
    sdk install gradle 8.7
    ```

## 🏗️ Building the Project

*   **Standard Build (JAR):**
    ```bash
    ./gradlew build
    ```
*   **Generate Native Binary (GraalVM):**
    ```bash
    ./gradlew nativeCompile
    ```
    *The binary will be created at: `build/native/nativeCompile/gh-paw`*

## 🚀 Running the CLI

*   **Via Gradle (Development):**
    ```bash
    ./gradlew run --args="<command>"
    # Example:
    ./gradlew run --args="auth login"
    ```
*   **Via Native Binary (After `nativeCompile`):**
    ```bash
    ./build/native/nativeCompile/gh-paw <command>
    ```

## 📖 CLI Commands (Current)

*   **Authentication:**
    *   `auth login`: Authenticate with a GitHub Personal Access Token.
*   **Repositories:**
    *   `repo list`: List your repositories.
    *   `repo view <owner/repo>`: View details for a specific repository.
