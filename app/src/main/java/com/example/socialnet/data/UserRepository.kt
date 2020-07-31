package com.example.socialnet.data

class UserRepository private constructor(
    private val userDao: UserDao
){

    suspend fun getUserById(userId: String) = userDao.getUserById(userId)

    suspend fun saveUser(user: User) = userDao.insert(user)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also { instance = it }
            }
    }
}