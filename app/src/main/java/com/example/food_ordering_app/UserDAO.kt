package com.example.food_ordering_app

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    suspend fun findByUserEmailPassword(email:String, password:String):List<User>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("DELETE FROM users WHERE email=:email")
    suspend fun deleteuserAccount(email: String)

    @Query("UPDATE USERS Set email=:email")
    suspend fun updateuserAccount(email: String)

    @Query("SELECT * FROM users ORDER BY email DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE email LIKE :email")
    suspend fun getUsername(email: String): List<User>?=null

    @Query("SELECT * FROM users WHERE password LIKE :password")
    suspend fun getpassword(password: String): List<User>
}