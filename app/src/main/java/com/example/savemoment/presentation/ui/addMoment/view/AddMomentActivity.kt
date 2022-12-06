package com.example.savemoment.presentation.ui.addMoment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.savemoment.databinding.ActivityAddMomentBinding

class AddMomentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMomentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMomentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setListeners()
    }

    private fun init() {

    }

    private fun setListeners() {
        binding.toolbar.setNavigationOnClickListener { finish() }
    }
}