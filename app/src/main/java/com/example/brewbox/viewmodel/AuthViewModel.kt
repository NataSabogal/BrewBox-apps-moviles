package com.example.brewbox.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbox.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val userPreferences = UserPreferences(application)

    val currentUser: Flow<UserEntity?> = userDao.getUser()
    val isLoggedIn: Flow<Boolean> = userPreferences.isLoggedIn

    fun register(email: String, fullName: String, address: String, birthday: String) {
        viewModelScope.launch {
            val newUser = UserEntity(
                email = email,
                fullName = fullName,
                address = address,
                birthday = birthday
            )
            userDao.insertUser(newUser)
            userPreferences.setLoggedIn(true, email)
        }
    }

    fun login(email: String) {
        viewModelScope.launch {
            userPreferences.setLoggedIn(true, email)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
            userDao.clearUser()
        }
    }
}
