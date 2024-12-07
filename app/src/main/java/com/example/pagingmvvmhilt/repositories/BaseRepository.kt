package com.example.pagingmvvmhilt.repositories

import com.example.pagingmvvmhilt.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Resource.Failure(throwable.toString())
            }
        }
    }
}