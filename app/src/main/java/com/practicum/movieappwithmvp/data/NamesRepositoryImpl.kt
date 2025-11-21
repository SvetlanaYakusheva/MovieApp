package com.practicum.movieappwithmvp.data

import com.practicum.movieappwithmvp.data.dto.NamesSearchRequest
import com.practicum.movieappwithmvp.data.dto.NamesSearchResponse
import com.practicum.movieappwithmvp.domain.api.NamesRepository
import com.practicum.movieappwithmvp.domain.models.Person
import com.practicum.movieappwithmvp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NamesRepositoryImpl (private val networkClient: NetworkClient) : NamesRepository {


    //override fun searchNames(expression: String): Resource<List<Person>> {
    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow {

        val response = networkClient.doRequestSuspend(NamesSearchRequest(expression))

        //return when (response.resultCode) {
        when (response.resultCode) {
            -1 -> {
                //Resource.Error("Проверьте подключение к интернету")
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
//                Resource.Success((response as NamesSearchResponse).results.map {
//                    Person(id = it.id,
//                        name = it.title,
//                        description = it.description,
//                        photoUrl = it.image)
//                })
                with(response as NamesSearchResponse) {
                    val data = response.results.map {
                        Person(
                            id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image
                        )
                    }
                    emit(Resource.Success(data))
                }
            }

            else -> {
                //Resource.Error("Ошибка сервера")
                emit(Resource.Error("Ошибка сервера"))
            }
        }

    }
}
