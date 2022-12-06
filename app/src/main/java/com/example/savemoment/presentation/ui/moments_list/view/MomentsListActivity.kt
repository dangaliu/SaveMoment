package com.example.savemoment.presentation.ui.moments_list.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savemoment.R
import com.example.savemoment.databinding.ActivityMomentsListBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.adapter.MomentAdapter
import com.example.savemoment.presentation.ui.addMoment.view.AddMomentActivity
import com.example.savemoment.presentation.ui.moments_list.viewmodel.MomentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MomentsListActivity : AppCompatActivity(), MomentMenuListener {

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
        momentsAdapter = MomentAdapter(this, this)
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
            startActivity(Intent(this, AddMomentActivity::class.java))
        }
    }

    private fun showPopupMenu(view: View, moment: Moment) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.moments_menu)

        popupMenu.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.deleteMoment -> {
                    viewModel.delete(moment)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    override fun onLongClick(moment: Moment, view: View) {
        showPopupMenu(view, moment)
    }

}