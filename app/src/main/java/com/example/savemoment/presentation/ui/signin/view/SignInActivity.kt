package com.example.savemoment.presentation.ui.signin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.lifecycle.lifecycleScope
import com.example.savemoment.databinding.ActivityMomentsListBinding
import com.example.savemoment.databinding.ActivitySignInBinding
import com.example.savemoment.presentation.ui.moments_list.view.MomentsListActivity
import com.example.savemoment.presentation.ui.signup.view.SignUpActivity
import com.example.savemoment.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.tvNoAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        binding.btnSignIn.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (isSignInDataValidate()) {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        showToast("Авторизация прошла успешно")
                        startActivity(Intent(this@SignInActivity, MomentsListActivity::class.java))
                        finish()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        showToast(e.message.toString())
                    }
                }
            }
        } else {
            showToast("Были введены некорректные данные")
        }
    }

    private fun isSignInDataValidate(): Boolean {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        return Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() && password.trim().length >= 8
    }

}