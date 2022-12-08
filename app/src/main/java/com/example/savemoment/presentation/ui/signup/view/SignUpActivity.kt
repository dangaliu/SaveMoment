package com.example.savemoment.presentation.ui.signup.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.lifecycle.lifecycleScope
import com.example.savemoment.databinding.ActivitySignUpBinding
import com.example.savemoment.presentation.ui.moments_list.view.MomentsListActivity
import com.example.savemoment.utils.showToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun isSignUpDataValidate(): Boolean {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeatPassword.text.toString()

        return Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() && password.trim().length >= 8 && repeatPassword == password
    }

    private fun signUp() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (isSignUpDataValidate()) {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    if (auth.currentUser != null) {
                        withContext(Dispatchers.Main) {
                            showToast("Регистрая прошла успешно")
                            startActivity(
                                Intent(
                                    this@SignUpActivity,
                                    MomentsListActivity::class.java
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        showToast(e.message.toString())
                    }
                }
            }
        } else {
            showToast("Вы ввели некорректные данные")
        }
    }
}