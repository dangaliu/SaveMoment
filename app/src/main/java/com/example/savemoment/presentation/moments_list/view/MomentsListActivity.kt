package com.example.savemoment.presentation.moments_list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.savemoment.databinding.ActivityMomentsListBinding

class MomentsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMomentsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMomentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}