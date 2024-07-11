package com.example.loginapplication.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.loginapplication.MainActivity
import com.example.loginapplication.R
import com.example.loginapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        observeLogin()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.login_credentials_required), Toast.LENGTH_SHORT).show()
            } else {
                val loginCredentials = LoginCredentials(username, password).apply {
                    this.username = username
                    this.password = password
                }
                loginViewModel.attemptLogin(loginCredentials)
            }
        }
    }

    private fun observeLogin() {
        loginViewModel.isLoggedIn.observe(this, Observer { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, getString(R.string.invalid_login), Toast.LENGTH_SHORT).show()
            }
        })
    }
}