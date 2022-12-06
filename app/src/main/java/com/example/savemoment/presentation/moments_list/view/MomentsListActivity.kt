package com.example.savemoment.presentation.moments_list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemoment.databinding.ActivityMomentsListBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.adapter.MomentAdapter
import com.example.savemoment.presentation.moments_list.viewmodel.MomentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MomentsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMomentsListBinding
    private val viewModel by viewModel<MomentsViewModel>()
    private lateinit var momentsAdapter: MomentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMomentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setObservers()
        setListeners()
    }

    private fun init() {
        momentsAdapter = MomentAdapter(this)
        binding.rvMoments.adapter = momentsAdapter
    }

    private fun setObservers() {
        viewModel.moments.observe(this) {
            binding.rvMoments.apply {
                layoutManager = LinearLayoutManager(this@MomentsListActivity)
                momentsAdapter.updateMoments(it)
            }
        }
    }

    private fun setListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.save(
                Moment(
                    pictureUri = "",
                    title = "Test Title",
                    description = "Test Description"
                )
            )
        }
    }
}