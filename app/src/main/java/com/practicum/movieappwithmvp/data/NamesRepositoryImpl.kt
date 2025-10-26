package com.practicum.movieappwithmvp.data

import com.practicum.movieappwithmvp.data.dto.NamesSearchRequest
import com.practicum.movieappwithmvp.data.dto.NamesSearchResponse
import com.practicum.movieappwithmvp.domain.api.NamesRepository
import com.practicum.movieappwithmvp.domain.models.Person
import com.practicum.movieappwithmvp.util.Resource

class NamesRepositoryImpl (private val networkClient: NetworkClient) : NamesRepository {


    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NamesSearchRequest(expression))
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
                Resource.Success((response as NamesSearchResponse).results.map {
                    Person(id = it.id,
                        name = it.title,
                        description = it.description,
                        photoUrl = it.image)
                })
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

}
