package com.example.savemoment.presentation.ui.welcome.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.savemoment.databinding.ActivityWelcomeBinding
import com.example.savemoment.presentation.di.*
import com.example.savemoment.presentation.ui.moments_list.view.MomentsListActivity
import com.example.savemoment.presentation.ui.signup.view.SignUpActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnSaveInLocal.setOnClickListener {
            startActivity(Intent(this, MomentsListActivity::class.java))
            startKoinWithDatasource(isLocal = true)
            finish()
        }
        binding.btnSaveInCloud.setOnClickListener {
            if (Firebase.auth.currentUser != null) {
                startActivity(Intent(this, MomentsListActivity::class.java))
            } else {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            startKoinWithDatasource(isLocal = false)
            finish()
        }
    }

    private fun startKoinWithDatasource(isLocal: Boolean) {
        if (isLocal) {
            startKoin {
                androidLogger(Level.DEBUG)
                androidContext(this@WelcomeActivity)
                modules(appModule, dataModule, domainModule, localDataSourceModule)
            }
        } else {
            startKoin {
                androidLogger(Level.DEBUG)
                androidContext(this@WelcomeActivity)
                modules(appModule, dataModule, domainModule, remoteDataSourceModule)
            }
        }
    }
}