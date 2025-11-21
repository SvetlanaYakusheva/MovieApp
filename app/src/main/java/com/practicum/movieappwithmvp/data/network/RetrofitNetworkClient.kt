package com.practicum.movieappwithmvp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.practicum.movieappwithmvp.data.NetworkClient
import com.practicum.movieappwithmvp.data.dto.MovieCastRequest
import com.practicum.movieappwithmvp.data.dto.MovieDetailsRequest
import com.practicum.movieappwithmvp.data.dto.MoviesSearchRequest
import com.practicum.movieappwithmvp.data.dto.NamesSearchRequest
import com.practicum.movieappwithmvp.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//
class RetrofitNetworkClient( private val imdbService: IMDbApiService,
                             private val context: Context) : NetworkClient {

   override fun doRequest(dto: Any): Response {


      if (isConnected() == false) {
          return Response().apply { resultCode = -1 }
      }
      if ((dto !is MoviesSearchRequest) && (dto !is MovieDetailsRequest)
          && (dto !is MovieCastRequest) && (dto !is NamesSearchRequest)) {
          return Response().apply { resultCode = 400 }
      }


      val response = if (dto is MoviesSearchRequest) {
          imdbService.searchMovies(dto.expression).execute()
      } else if (dto is MovieDetailsRequest) {
          imdbService.getMovieDetails(dto.movieId).execute()
      } else {
          imdbService.getFullCast((dto as MovieCastRequest).movieId).execute()
//      } else { asis MovieCastRequest
//         // imdbService.searchNames((dto as NamesSearchRequest).expression).execute()
//
      }
      val body = response.body()
      return if (body != null) {
          body.apply { resultCode = response.code() }
      } else {
          Response().apply { resultCode = response.code() }
      }
  }
    override suspend fun doRequestSuspend(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }

        if (dto !is NamesSearchRequest) {
            return Response().apply { resultCode = 400 }
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = imdbService.searchNames(dto.expression)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }


    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}