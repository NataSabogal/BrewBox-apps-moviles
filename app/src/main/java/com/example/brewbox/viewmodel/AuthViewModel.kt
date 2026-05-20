package com.example.brewbox.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbox.data.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val userPreferences = UserPreferences(application)

    val isLoggedIn: Flow<Boolean> = userPreferences.isLoggedIn
    
    val currentUser: Flow<UserEntity?> = userPreferences.userEmail.flatMapLatest { email ->
        if (email != null) {
            userDao.getUserByEmail(email)
        } else {
            flowOf(null)
        }
    }

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError

    init {
        // Mantenemos el requisito de pedir login al arrancar la app de cero
        logout()
    }

    fun register(email: String, fullName: String, address: String, birthday: String, password: String) {
        viewModelScope.launch {
            val newUser = UserEntity(
                email = email,
                fullName = fullName,
                address = address,
                birthday = birthday,
                password = password
            )
            userDao.insertUser(newUser)
            // Logueamos automáticamente para que al terminar el flujo de registro 
            // el usuario entre directamente al Home con su sesión iniciada.
            userPreferences.setLoggedIn(true, email)
        }
    }

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userDao.findUserByEmail(email)
            if (user != null) {
                if (user.password == password) {
                    userPreferences.setLoggedIn(true, email)
                    _loginError.value = null
                    onResult(true)
                } else {
                    _loginError.value = "Contraseña incorrecta"
                    onResult(false)
                }
            } else {
                _loginError.value = "Este correo no está registrado"
                onResult(false)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
        }
    }
    
    fun clearError() {
        _loginError.value = null
    }
}
