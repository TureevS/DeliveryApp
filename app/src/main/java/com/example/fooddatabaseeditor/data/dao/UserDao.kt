package com.example.fooddatabaseeditor.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fooddatabaseeditor.model.Product
import com.example.fooddatabaseeditor.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY phoneNumber ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE phoneNumber = :number AND password = :password")
    suspend fun getUser(number: String, password: String): User?
}