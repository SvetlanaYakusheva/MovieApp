package com.practicum.movieappwithmvp.data

interface StorageClient<T> {
    fun storeData(data: T)
    fun getData(): T?
}