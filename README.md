# CountryExplorer 🌍

## Table of Contents

- [About the Project](#about-the-project)
- [Features](#features) ✨
- [Tech Stack](#tech-stack) 📱
- [Installation](#installation) 🚀
- [Usage](#usage) 💡
- [Project Structure](#project-structure) 📂
- [API Reference](#api-reference) 🔗
- [Contributing](#contributing) 🙏
- [License](#license) 📜
- [Author](#author) ✍️

---

## About the Project 🚀

CountryExplorer is an Android application built using Kotlin and Jetpack Compose that allows users to explore a list of countries worldwide. It fetches country data from a public API and displays key information such as the country's name, region, population, and capital. The app provides a clean and intuitive interface for browsing and selecting countries.

---

## Features ✨

- **Country Listing:** Displays a comprehensive list of countries fetched from an external API.
- **Detailed Country Information:** Shows essential details for each country, including its name, region, population, and capital city.
- **Interactive Selection:** Allows users to select a country from the list, highlighting their choice.
- **Loading and Error Handling:** Provides visual feedback for loading states and gracefully handles potential API errors.
- **Modern UI:** Implemented using Jetpack Compose for a declarative and efficient UI development experience.

---

## Tech Stack 📱

- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Retrofit, OkHttp
- **Data Parsing:** Gson
- **Dependency Management:** Gradle

---

## Installation 🚀

To get this project up and running on your local machine, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/ARAVINDNARAYAN0966/CountryExplorer.git
    cd CountryExplorer
    ```

2.  **Set up Android Studio:** Ensure you have Android Studio installed and configured.

3.  **API Key Configuration:**
    This project requires an API key for the `restcountries.com` service. You need to add this key to your `local.properties` file or set it as a build variable.
    Create a file named `local.properties` in the root directory of the project (if it doesn't exist) and add the following line:
    ```properties
    # Example: Replace with your actual API key
    REST_COUNTRIES_API_KEY=YOUR_API_KEY_HERE
    ```
    Alternatively, you can manage this key within your `build.gradle` file or environment variables if you prefer.

4.  **Build and Run:**
    Open the project in Android Studio and build it. You can then run the application on an emulator or a physical Android device.
    ```bash
    ./gradlew build
    ./gradlew run
    ```

---

## Usage 💡

Upon launching the app, you will see a list of countries. Each country is displayed in a card format showing its name, region, population, and capital.

- **View Countries:** Scroll through the list to see all available countries.
- **Select a Country:** Tap on any country card to select it. The selected country will be highlighted, and its name will be displayed at the top of the screen.

This application serves as a practical example of fetching and displaying data from a remote API using modern Android development practices with Jetpack Compose.

---

## Project Structure 📂

The project follows a standard Android project structure with key directories:

- **`app/src/main/java/com/example/countryexplorer/`**: Contains the main application code.
  - **`api/`**: Handles network requests and data models for country information.
  - **`ui/theme/`**: Defines the application's color schemes and typography.
  - **`viewmodel/`**: Implements the ViewModel for managing UI-related data.
- **`app/src/androidTest/`**: Contains instrumented tests.
- **`app/src/test/`**: Contains local unit tests.
- **`res/`**: Holds application resources like drawables, layouts, and values.
- **`gradle/`**: Configuration files for Gradle.

---

## API Reference 🔗

This project utilizes the **REST Countries API** to fetch country data. The specific endpoint used is:

- **Endpoint:** `https://api.restcountries.com/countries/v5`
- **Authentication:** Requires an API key, passed via the `Authorization: Bearer YOUR_API_KEY` header.

The relevant data classes for handling API responses are located in `app/src/main/java/com/example/countryexplorer/api/Country.kt`.

---

## Contributing 🙏

Contributions are welcome! If you'd like to contribute to this project, please follow these guidelines:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix (`git checkout -b feature/your-feature` or `git checkout -b fix/your-fix`).
3.  Make your changes and commit them (`git commit -m 'Add some feature'`).
4.  Push to the branch (`git push origin feature/your-feature`).
5.  Open a Pull Request.

Please ensure your code follows the project's coding standards and includes relevant tests.

---

## License 📜

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Author ✍️

**ARAVINDNARAYAN0966** - Feel free to connect!

[![GitHub](https://img.shields.io/badge/GitHub-Profile-blue?style=flat-square&logo=github)](https://github.com/ARAVINDNARAYAN0966)

---

> ### **CountryExplorer**
> URL: [https://github.com/ARAVINDNARAYAN0966/CountryExplorer](https://github.com/ARAVINDNARAYAN0966/CountryExplorer)
>
> ⭐ Star this repo if you find it useful!
> 🍴 Fork this repo to make your own contributions!
> 💡 Open an issue for any suggestions or bug reports!


---
**<p align="center">Generated by [ReadmeCodeGen](https://www.readmecodegen.com/)</p>**