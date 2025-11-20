# KKtotDemo

KKtotDemo is a demonstration Android application that showcases how to use **Ktor Client** for networking in a modern Android app built with **Jetpack Compose**.

The app interacts with the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API to perform CRUD (Create, Read, Update, Delete) operations on "posts".

## Features

-   **View Posts:** Fetches and displays a list of posts from the API.
-   **Create Post:** Allows users to create a new post with a title and body.
-   **Edit Post:** Enables users to update the title and body of an existing post.
-   **Delete Post:** Allows users to delete a post.
-   **Modern UI:** Built entirely with Jetpack Compose, following Material Design 3 guidelines.
-   **MVVM Architecture:** Separates UI logic from business logic using ViewModels.

## Technologies Used

-   **[Kotlin](https://kotlinlang.org/):** The primary programming language.
-   **[Jetpack Compose](https://developer.android.com/jetpack/compose):** Android's modern toolkit for building native UI.
-   **[Ktor Client](https://ktor.io/docs/client-create-new-application.html):** An asynchronous framework for creating connected applications.
    -   `ktor-client-core`: Core client functionality.
    -   `ktor-client-cio`: Coroutine-based I/O engine.
    -   `ktor-client-content-negotiation`: Plugin for handling content negotiation.
    -   `ktor-serialization-kotlinx-json`: JSON serialization support.
-   **[Kotlin Serialization](https://kotlinlang.org/docs/serialization.html):** JSON parsing and serialization.
-   **[Material Design 3](https://m3.material.io/):** The latest design system from Google.

## Project Structure

The project follows a clean architecture approach:

-   `com.example.kktotdemo`
    -   `component`: Reusable UI components (e.g., `PostDialog`).
    -   `model`: Data classes representing API resources (e.g., `Post`, `CreatePostRequest`).
    -   `screens`: Composable screens (e.g., `PostScreen`).
    -   `services`: Network layer handling API calls (`ApiService`).
    -   `viewmodels`: ViewModels managing UI state and business logic (`PostViewModel`).
    -   `ui`: Theme and UI-related configurations.

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Open in Android Studio:**
    -   Launch Android Studio.
    -   Select "Open" and navigate to the cloned directory.
3.  **Sync Gradle:**
    -   Allow Android Studio to sync the project and download dependencies.
4.  **Run the App:**
    -   Connect an Android device or start an emulator.
    -   Click the "Run" button (green play icon) in the toolbar.

## Requirements

-   Android Studio Iguana or later (recommended).
-   JDK 17 or later.
-   Android SDK API Level 24 (Min SDK) to 34 (Target SDK).

## License

[Add License Here, e.g., MIT License]
