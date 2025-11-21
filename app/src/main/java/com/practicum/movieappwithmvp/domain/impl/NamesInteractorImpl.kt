package com.practicum.movieappwithmvp.domain.impl

import com.practicum.movieappwithmvp.domain.api.NamesInteractor
import com.practicum.movieappwithmvp.domain.api.NamesRepository
import com.practicum.movieappwithmvp.domain.models.Person
import com.practicum.movieappwithmvp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class NamesInteractorImpl (private val repository: NamesRepository) : NamesInteractor {

    //private val executor = Executors.newSingleThreadExecutor()
    //override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
//    executor.execute {
//        when (val resource = repository.searchNames(expression)) {
//            is Resource.Success -> {
//                consumer.consume(resource.data, null)
//            }
//
//            is Resource.Error -> {
//                consumer.consume(null, resource.message)
//            }
//        }
//    }


    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return repository.searchNames(expression).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
}