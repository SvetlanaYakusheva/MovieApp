package com.practicum.movieappwithmvp.data

import com.practicum.movieappwithmvp.data.dto.Response

interface NetworkClient {
    //fun doRequest(dto: Any): Response
    suspend fun doRequestSuspend(dto: Any): Response
}


