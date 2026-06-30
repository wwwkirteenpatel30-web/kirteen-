package com.example.data.repository

import com.example.data.local.UserDao
import com.example.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val loggedInUser: Flow<User?> = userDao.getLoggedInUserFlow()

    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)

    suspend fun getActiveUser(): User? = userDao.getLoggedInUser()

    suspend fun registerUser(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun loginUser(email: String, passwordHash: String): Boolean {
        val user = userDao.getUserByEmail(email)
        return if (user != null && user.passwordHash == passwordHash) {
            userDao.logoutAllUsers()
            userDao.updateUser(user.copy(isLoggedIn = true))
            true
        } else {
            false
        }
    }

    suspend fun loginWithGoogleSimulation(email: String, name: String): Boolean {
        userDao.logoutAllUsers()
        val existing = userDao.getUserByEmail(email)
        if (existing != null) {
            userDao.updateUser(existing.copy(isLoggedIn = true, displayName = name))
        } else {
            val newUser = User(
                email = email,
                passwordHash = "google_auth_placeholder",
                displayName = name,
                isLoggedIn = true
            )
            userDao.insertUser(newUser)
        }
        return true
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun logout() {
        userDao.logoutAllUsers()
    }
}
