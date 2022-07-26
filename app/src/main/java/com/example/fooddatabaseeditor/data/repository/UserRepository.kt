package com.example.fooddatabaseeditor.data.repository


import androidx.lifecycle.LiveData
import com.example.fooddatabaseeditor.data.dao.UserDao
import com.example.fooddatabaseeditor.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun getUser(number: String, password:String): User? {
        return userDao.getUser(number, password)
    }

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }


}