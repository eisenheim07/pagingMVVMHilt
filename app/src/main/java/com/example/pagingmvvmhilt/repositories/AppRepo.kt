package com.example.pagingmvvmhilt.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagingmvvmhilt.models.model_users.GetUsers
import com.example.pagingmvvmhilt.retrofit.ApiService
import com.example.pagingmvvmhilt.utils.Resource
import javax.inject.Inject

class AppRepo @Inject constructor(private val apiService: ApiService) : BaseRepository() {

    private val _getUsers: MutableLiveData<Resource<GetUsers>> = MutableLiveData()
    val getUsers: LiveData<Resource<GetUsers>>
        get() = _getUsers

    suspend fun getUsers(page: Int, per_page: String) = safeApiCall {
        _getUsers.postValue(Resource.Loading())
        try {
            val result = apiService.getUsers(page, per_page)
            if (result.body() != null && result.code() == 200) {
                _getUsers.postValue(Resource.Success(result.body()!!))
            } else {
                _getUsers.postValue(Resource.Failure("Something went wrong, ${result.code()}."))
            }
        } catch (e: Exception) {
            _getUsers.postValue(Resource.Failure("Something went wrong."))
        }
    }

}