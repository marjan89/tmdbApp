# TMDB App

Application for showcasing clean architecture approach with Jetpack Compose on Android. Built with Jetpack Compose, Koin, Ktor, and Kotlinx Serialization.

Project is built using clear architecture with clear separation of layers with Kotlin modules. Each module declares interfaces as layer boundaries and exposes only the required dependencies to the rest of the app via use of Koin modules and access modifiers.

There is no direct composition of components in the app and all dependencies are provided via constructors to enable testability and avoid coupling.

## Building the App

### Prerequisites
- Android Studio
- JDK 17 or higher
- Gradle 8.0+

### Steps to Build
1. Clone the repository
2. Set up your TMDB API key (see API Setup below)
3. Open the project in Android Studio
4. Sync Gradle files
5. Run the app on an emulator or physical device

### API Setup                                                                                                                                                                                                   
This application uses [The Movie Database (TMDB) API](https://www.themoviedb.org/3/) to fetch movie data. To run the app, you'll need to:                                                                       
                                                                                                                                                                                                                
1. Register for an account at [themoviedb.org](https://www.themoviedb.org/signup)                                                                                                                               
2. Request an API key from your account settings                                                                                                                                                                
3. Create a file named `secrets.properties` in the project root directory                                                                                                                                       
4. Add your API key to the file in the following format:                                                                                                                                                        
   ```                                                                                                                                                                                                          
   TMDB_API_KEY=your_api_key_here                                                                                                                                                                               
   ```                                                       
   
Note: API for genres seems to omit "All" genre from the list of provided genres. DiscoveryRepository handles this with use of `NO_GENRE_ID` constant.                                                                                                                                                   
                                                                                                                                                                                                                
The app uses the secrets-gradle-plugin to securely access this API key without exposing it in the source code.

## Architecture

This application follows a modular clean architecture approach:

- **app**: Presentation layer - UI components, ViewModels
- **domain**: Business logic and models
- **network**: Data sources and API communication

**app**

For sake of simplicity app module acts as both a presentation layer and a composition root for the project. In a more complex environment presentation role would be served by a separate UI module.

**app module as Composition root**

App module hosts the Koin container which integrates Koin modules from **app**, **domain** and **network** modules. It holds implementations of appropriate interfaces handles mapping of data across layers.
For simplicity mapping is handled with extension functions, but in a more complex environment it could be implemented via objects delivered by Koin modules to foster better testability and looser coupling.

**app module as Presentation layer**

App module hosts screen composables, view models, reusable UI components and a navigation component.

***Navigation***

App handles navigation between screens via type safe implementation of Jetpack Navigation. It hosts a type safe navigation graph and handles navigation between screens by use of router pattern.

Router is responsible for handling final navigation by creating actual screens and providing them with appropriate view models. This and use of callbacks for navigation events allows for decoupling of navigation logic from UI components.

**domain**

Domain module declares interfaces defining business logic and models. It has no dependencies on other modules and is devoid of any implementation details and framework specific code.

**network**

Network module is implemented with Ktor. It defines API services and interfaces which allow dependency mocking and testability.

It's concerns are split to app related network configuration and hidden API configuration considered as implementation details.

## Testing

There are two sample unit tests provided to showcase separation of concerns and testability. Tests make use of Mockito and Koin to provide mocking, dependency injection and appropriate use of coroutines.

## Libraries

All libraries and dependencies are handled via gradle version catalogs.

### UI
- Jetpack Compose - Modern UI toolkit
- Coil - Image loading

### Architecture & Navigation
- ViewModel - UI state management
- Koin - Dependency injection
- Jetpack Navigation - Screen navigation

### Networking
- Ktor - HTTP client
- Kotlinx Serialization - JSON parsing

### Testing
- JUnit - Unit testing framework
- MockK - Mocking library