# Project Description

This project is a mobile application developed using Kotlin and Java, built with Gradle. It uses the Model-View-ViewModel (MVVM) architectural pattern and follows the principles of Clean Architecture. The project is divided into several layers, each with its own set of responsibilities.

## Layers
![Layer Diagram](https://github.com/divyadeep86/FakeStore/blob/main/images/appArcGif.drawio.svg)

### UI Layer

This layer is responsible for displaying the user interface and capturing user interactions. It includes screens, custom views, and navigation components. The main classes in this layer are:

- `AppNavHost.kt`: This class is responsible for navigation within the application. It uses the Jetpack Navigation component to manage app navigation.
- `CategoriesScreen.kt`: This screen displays a list of categories and products. It interacts with `CategoriesViewModel` and `ProductListViewModel` to fetch and display data.
- `LoadingContentWrapper.kt`: This is a custom Composable function that displays a loading indicator or error message based on the state of the data.

### ViewModel Layer

This layer is responsible for managing and storing UI-related data in a lifecycle conscious way. It interacts with the use case layer to fetch data and updates the UI state accordingly. The main classes in this layer are:

- `CategoriesViewModel.kt`: This ViewModel is responsible for managing the state of the categories screen. It interacts with `CategoriesUseCase` to fetch categories.
- `ProductListViewModel.kt`: This ViewModel is responsible for managing the state of the product list. It interacts with `ProductListUseCase` to fetch products by category.

### Domain Layer

This layer contains business logic and use cases. Use cases are responsible for orchestrating the flow of data to and from the entities and represent a single use case in the app. The main classes in this layer are:

- `CategoriesUseCase.kt`: This use case is responsible for fetching categories.
- `ProductListUseCase.kt`: This use case is responsible for fetching products by category.

### Data Layer

This layer is responsible for data persistence and network calls. It abstracts the source of data and how it's retrieved. The main classes in this layer are:

- `CategoriesRepository.kt`: This repository is responsible for fetching categories data. It can fetch data from a local database or a remote API.
- `ProductListRepository.kt`: This repository is responsible for fetching product data. It can fetch data from a local database or a remote API.



Each layer has a clear set of responsibilities, which makes the code easier to maintain and test. The separation of concerns also allows each layer to be developed and tested independently.