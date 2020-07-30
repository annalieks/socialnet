package com.example.socialnet.data

class UserRepository private constructor(
    private val userDao: UserDao
){

    fun getUserById(userId: String) = userDao.getUserById(userId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also { instance = it }
            }
    }
}