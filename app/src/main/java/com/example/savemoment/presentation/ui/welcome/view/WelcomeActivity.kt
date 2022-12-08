package com.example.savemoment.presentation.ui.welcome.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.savemoment.databinding.ActivityWelcomeBinding
import com.example.savemoment.presentation.ui.moments_list.view.MomentsListActivity

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
            finish()
        }
    }
}