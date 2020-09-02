# RevolutTestApp
**Please read README.md at first, especially part about Currency domain class.**

## How app works
When app is launched at the first time it downloads data from api and saves it in memory cache which is the single sourse of true. App updates cache every second.

When app is put in background, updating of cache stops until user puts app in the foreground. 

When device has problems with internet connection, application can be used in offline mode so than user can convert currencies but could not select another currency.
If user selects another currency in offline mode, app shows error that user can't change currency and offers to get back to offline mode. When internet connection is estableshed user gets new currency rates automatically.

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

On the other hand, presentation layer was devided into several moduels in order to support features that can be independed on each other.

The app follows Clean Architecture approach as well. Full UML diagram you can see bellow. The diagram shows dependencies of modules. You can see that there are no acycling dependencies.

<img src="https://github.com/Raiden18/RevolutTestApp/blob/master/modules_dependency_diagram.PNG" data-canonical-src="https://github.com/Raiden18/RevolutTestApp/blob/master/modules_dependency_diagram.PNG" width="400" height="450" />

Kotlin does't have package-private access modifier, but it has internal access modifier. So I chose three-tier architecture at the top level modules to explisitly show that:
1. Business logic is pure Kotlin and doesn't have any ui or data dependencies
2. UI knows nothing about what frameworks data uses
3. Data knows nothing about ui framerowks.

So it's impossible to make features depends on data or data on feature accidentally.

MVI was chosen as architecture of presentation layer. Features are devided into 4 layers:

1. di - to get and provide deps for feature 
2. models - to show data on the screen to minimize logic in view layer
3. view 
4. viewmodel

Diagram that shows dependencies of components of feature you can see underneeth. There are no acycling dependences as well.

<img src="https://github.com/Raiden18/RevolutTestApp/blob/master/feature_components_dependencies_diagram.PNG" data-canonical-src="hhttps://github.com/Raiden18/RevolutTestApp/blob/master/feature_components_dependencies_diagram.PNG" width="415" height="400" />

## Special topic about Currency domain class
It looks like that:
```Kotlin
data class Currency(
    val amount: Double,
    val code: String //descriptor
) {
    //Rest of code ...
}
```
Here is what I want to say:

This class has descriptor "code". According to Polymorphism of GRASP patterns and "Effective Java" this is not good practice because this class represents every specific currency such as Rouble, Dollar, Euro etc. So that this class should be an interface and this interface should be implemented by classes for every specific currency.

I followed this practice at first. But eventually I realized that for this test project that practice doesn't make sense because every specific instance of specific currency has the same behavior. And that best practice does nothing and I just had code that wasn't even used (But I can't say the same thing for unit tests. That practice was extremely useful for them). So that I made data class from Currency interface and got rid of implementations of that interface. 

And, of course, turning Currency interface into data class violates DDD approach. So that when we talk about currencies we have to translate a business object let's say Rouble to the specific instance of Currency class. Developer doesn't have the same terminology with the business. This is not convenient and requires additional mental working.

This test assignment is not going to be a real project in production. This test assignment is not going to have managers. Requirements of this this test assignemnt project are not going to be changed frequently. This project does not have team of developers that have to communicate with each other.  So I think that creating Currency class in a way that it was created makes sense for this project.  
