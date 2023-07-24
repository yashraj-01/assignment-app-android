package com.example.assignmentapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentapplication.model.User
import com.example.assignmentapplication.repository.UserRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(): ViewModel() {

    init {
        viewModelScope.launch {
            UserRepository.getUsers(10)
        }
    }

    val users: LiveData<List<User>>
        get() = UserRepository.users

    fun getUser(index: Int) = UserRepository.getUser(index)!!

    fun deleteUser(index: Int) {
        UserRepository.deleteUser(index)
    }

}