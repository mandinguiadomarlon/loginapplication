package com.example.loginapplication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun attemptLogin(loginCredentials: LoginCredentials) {
        _isLoggedIn.value = loginCredentials.username == "user"
                && loginCredentials.password == "password"
    }
}