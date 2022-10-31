# Applaudo's-Code-Challenge-Android

This project was developed for showing data retrieved from [TMDb's Api](https://www.themoviedb.org/documentation/api/).

## Requirements built:

1. Splash Screen
2. Login Screen
3. Show List Screen
    1. Pagination for every 20 elements
    1. Category filters
    2. Query by name
4. Details Screen
    1. Show's poster, rating and summary
    2. List of Seasons
5. Episodes List Screen

<p align="center">
  <img src="https://raw.githubusercontent.com/ramruizni/Applaudo-s-Code-Challenge-Android/master/Applaudo-s-Code-Challenge-Android.gif" />
</p>

## Nice to haves implemented:

1. Clean Architecture
2. Unit Tests for Use Cases and Daos
3. Profile Screen
    1. Favorites list
    2. Logout button
4. Season, Episode and Favorite Shows get cached with Room

## To run the project:

(For convenience, an **app-release.apk** is stored in the root folder to avoid the repository setup).

1. Clone the repository and build it in Android Studio.
2. Add the following LINES to your **local.properties** file (with a valid API_KEY):
```
API_URL=https://api.themoviedb.org/3/
API_KEY=PLEASE ADD YOUR API KEY FROM https://www.themoviedb.org/documentation/api
API_IMAGE_URL=https://image.tmdb.org/t/p/
```
