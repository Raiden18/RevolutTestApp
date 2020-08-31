# RevolutTestApp
## How app works
When app is launched at the first time it downloads data from api and saves it in memory cache. Memory cache is the single sourse of true. App updates cache every second.

When app is put in background, updating of cache stops until user puts app in the foreground. 

When device has problems with internet connection, application can be used in offline mode so than user can convert currencies but could not select another currency.
If user selects another currency in offline mode, app shows error about internenet connection and tryes to update data. When internet connection is estableshed user gets new currency rates with base currency that user selected during offline mode.

## Used Frameworks
1. RxJava 3
2. Dagger 2
3. Retrofit 2
4. Junit5
5. MockK
5. RxRelay
6. RxLifeCycle 4
7. DiffUtils and Payloads

## About architecture
This app folows three-tier architecture. So that there are 3 top-level layers:
1. Data
2. Domain
3. Presentation

Kotlin does't have package-private access modifier, but it has internal access modifier. So I chose three-tier architecture at the top level modules to explisitly show that:
1. Business logic is pure Kotlin and doesn't have any ui or data dependencies
2. UI knows nothing about what frameworks data uses
3. Data knows nothing about ui framerowks.
So it's impossible to make features depends on data or data on feature accidentally.

On the other hand, presentation layer was devided into several moduels in order to support features that can be independed on each other.

The app follows Clean Architecture approach as well. Full UML diagram you can see bellow. The diagram shows dependencies of modules. You can see that there are no acycling dependencies.

<img src="https://github.com/Raiden18/RevolutTestApp/blob/master/modules_dependency_diagram.PNG" data-canonical-src="https://github.com/Raiden18/RevolutTestApp/blob/master/modules_dependency_diagram.PNG" width="400" height="450" />

MVI was chosen as architecture of presentation layer. Features are devided into 4 layers:

1. di - to get and provide deps for feature 
2. models - to show data on the screen to minimize logic in view layer
3. view 
4. viewmodel

Diagram that shows dependencies of components of feature you can see underneeth. There are no acycling dependences as well.

<img src="https://github.com/Raiden18/RevolutTestApp/blob/master/feature_components_dependencies_diagram.PNG" data-canonical-src="hhttps://github.com/Raiden18/RevolutTestApp/blob/master/feature_components_dependencies_diagram.PNG" width="415" height="400" />
