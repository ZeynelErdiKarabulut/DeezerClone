<h1 align="center">Deezer Clone</h1>

<p align="center">  
 Deezer Clone application, Dagger Hilt, Coroutines, Flow, Jetpack (Room, ViewModel, Navigation, LiveData) based on MVVM architecture. Also fetching data from the network and integrating local data in the database via repository pattern.
</p>

<p align="center">
<img src="/art/banner.png"/>
</p>


## Tech stack - Library
- [Kotlin](https://kotlinlang.org/) , [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) , [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- JetPack
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) 
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [MVVM Architecture]() (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit)
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Timber](https://github.com/JakeWharton/timber)
- [Material-Components](https://github.com/material-components/material-components-android)
- [Coil](https://github.com/coil-kt/coil)
- [ShimmerLayout](http://facebook.github.io/shimmer-android/)
- Test
  - [mockK](https://mockk.io/)
  - [Mock Server](https://www.mock-server.com/)
  - [Robolectric](http://robolectric.org/)
  - [Mockito Kotlin](https://github.com/nhaarman/mockito-kotlin)
  - [Turbine](https://cashapp.github.io/turbine/docs/0.x/turbine/)

## Architecture
DeezerClone is based on MVVM architecture and a repository pattern.

![architecture](https://github.com/ZeynelErdiKarabulut/DeezerClone/blob/master/art/mvvm.png)

## API
Used Deezer API. Deezer API provides a RESTful API. [Link](https://developers.deezer.com/api/)

## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details
