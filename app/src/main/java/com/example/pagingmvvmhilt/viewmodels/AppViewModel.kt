package com.example.pagingmvvmhilt.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagingmvvmhilt.models.model_users.GetUsers
import com.example.pagingmvvmhilt.repositories.AppRepo
import com.example.pagingmvvmhilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appRepo: AppRepo) : ViewModel() {

    fun getUsers(page: Int, per_page: String) {
        viewModelScope.launch {
            appRepo.getUsers(page, per_page)
        }
    }

    val getUsers: LiveData<Resource<GetUsers>>
        get() = appRepo.getUsers

}