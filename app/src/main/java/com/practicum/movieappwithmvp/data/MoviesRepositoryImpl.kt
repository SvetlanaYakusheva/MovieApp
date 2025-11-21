package com.practicum.movieappwithmvp.data


import com.practicum.movieappwithmvp.data.converters.MovieCastConverter
import com.practicum.movieappwithmvp.data.dto.MovieCastRequest
import com.practicum.movieappwithmvp.data.dto.MovieCastResponse
import com.practicum.movieappwithmvp.data.dto.MovieDetailsRequest
import com.practicum.movieappwithmvp.data.dto.MovieDetailsResponse
import com.practicum.movieappwithmvp.data.dto.MoviesSearchRequest
import com.practicum.movieappwithmvp.data.dto.MoviesSearchResponse
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.domain.models.MovieDetails
import com.practicum.movieappwithmvp.domain.models.MovieCast
import com.practicum.movieappwithmvp.domain.models.MovieCastPerson
import com.practicum.movieappwithmvp.domain.models.Person
import com.practicum.movieappwithmvp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val networkClient: NetworkClient,
                            // Добавили конвертер
                           private val movieCastConverter: MovieCastConverter) : MoviesRepository {

    //override fun searchMovies(expression: String): Resource<List<Movie>> {
 override  fun searchMovies(expression: String): Flow<Resource<List<Movie>>> = flow {
        val response = networkClient.doRequestSuspend(MoviesSearchRequest(expression))

        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                emit(Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)}))
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    override fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> = flow {
        val response = networkClient.doRequestSuspend(MovieDetailsRequest(movieId))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                emit(with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id = id,
                            title = title,
                            imDbRating = imDbRating,
                            year = year,
                            countries = countries,
                            genres = genres,
                            directors = directors,
                            writers = writers,
                            stars = stars,
                            plot = plot,
                        )
                    )
                })
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    override fun getMovieCast(movieId: String): Flow<Resource<MovieCast>> = flow {
        // Поменяли объект dto на нужный Request-объект
        val response = networkClient.doRequestSuspend(MovieCastRequest(movieId))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                // используем конвертер вместо
                // прямой конвертации
                emit(Resource.Success(
                    data = movieCastConverter.convert(response as MovieCastResponse)
                ))
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}