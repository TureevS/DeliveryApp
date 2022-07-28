package com.example.fooddatabaseeditor.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fooddatabaseeditor.data.ProductDatabase
import com.example.fooddatabaseeditor.model.User
import com.example.fooddatabaseeditor.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    var user: User? = null

    init{
        val userDao = ProductDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun getUser(number: String, password: String): User? {
        viewModelScope.launch(Dispatchers.IO){
            user = repository.getUser(number, password)
        }
        return user
    }

}