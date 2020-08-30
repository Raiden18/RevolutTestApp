# RevolutTestApp
## Used Frameworks
1. RxJava 3
2. Dagger 2
3. Retrofit 2
4. Junit5
5. MockK
5. Glide
6. RxRelay
7. RxLifeCycle 4
8. DiffUtils and Payloads

## About architecture
This app folows three-tier architecture that was described by Martin Fowler. So that there are 3 top-level layers:
1. Data
2. Domain
3. Presentation

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
