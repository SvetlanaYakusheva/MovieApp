package com.practicum.movieappwithmvp.data


import com.practicum.movieappwithmvp.data.dto.MoviesSearchRequest
import com.practicum.movieappwithmvp.data.dto.MoviesSearchResponse
import com.practicum.movieappwithmvp.domain.api.MoviesRepository
import com.practicum.movieappwithmvp.domain.models.Movie
import com.practicum.movieappwithmvp.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

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
}