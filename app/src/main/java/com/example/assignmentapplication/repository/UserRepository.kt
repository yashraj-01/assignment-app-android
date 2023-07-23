package com.example.assignmentapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignmentapplication.api.RetrofitHelper
import com.example.assignmentapplication.api.UserApi
import com.example.assignmentapplication.model.User

object UserRepository {

    private val userApi: UserApi = RetrofitHelper.getInstance().create(UserApi::class.java)
    private val usersLiveData = MutableLiveData<List<User>>()

    val users: LiveData<List<User>>
        get() = usersLiveData

    suspend fun getUsers(size: Int) {
        val response = userApi.getUsers(size);
        if (response?.body() != null) {
            usersLiveData.postValue(response.body())
        }
    }

    fun deleteUser(index: Int) {
        usersLiveData.value = usersLiveData.value?.toMutableList()?.apply {
            removeAt(index)
        }?.toList()
    }

}