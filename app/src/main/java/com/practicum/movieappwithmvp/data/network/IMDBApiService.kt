package com.practicum.movieappwithmvp.data.network

import com.practicum.movieappwithmvp.data.dto.MovieCastResponse
import com.practicum.movieappwithmvp.data.dto.MovieDetailsResponse
import com.practicum.movieappwithmvp.data.dto.MoviesSearchResponse
import com.practicum.movieappwithmvp.data.dto.NamesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>

    @GET("/en/API/FullCast/k_zcuw1ytf/{movie_id}")
    fun getFullCast(@Path("movie_id") movieId: String): Call<MovieCastResponse>

//    @GET("/en/API/SearchName/k_zcuw1ytf/{expression}")
//    fun searchNames(@Path("expression") expression: String): Call<NamesSearchResponse>

    @GET("/en/API/SearchName/k_zcuw1ytf/{expression}")
    suspend fun searchNames(@Path("expression") expression: String): NamesSearchResponse
}