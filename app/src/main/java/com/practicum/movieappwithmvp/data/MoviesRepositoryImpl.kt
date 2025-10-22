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
import com.practicum.movieappwithmvp.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient,
                            // Добавили конвертер
                           private val movieCastConverter: MovieCastConverter) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        /*
        Если запрос прошёл успешно (resultCode имеет значение 200), используя метод map{},
        преобразуем список фильмов MovieDto в список элементов типа Movie.
        При необходимости можно передать только нужные поля или произвести
        какие-то промежуточные преобразования данных.
        В этом примере мы просто передаём данные как есть.
         */
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        /*
        Если запрос прошёл успешно (resultCode имеет значение 200), используя метод map{},
        преобразуем список фильмов MovieDto в список элементов типа Movie.
        При необходимости можно передать только нужные поля или произвести
        какие-то промежуточные преобразования данных.
        В этом примере мы просто передаём данные как есть.
         */
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as MovieDetailsResponse) {
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
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        // Поменяли объект dto на нужный Request-объект
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                // используем конвертер вместо
                // прямой конвертации
                Resource.Success(
                    data = movieCastConverter.convert(response as MovieCastResponse)
                )
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}