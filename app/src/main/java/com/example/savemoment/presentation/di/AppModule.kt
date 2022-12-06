package com.example.savemoment.presentation.di

import com.example.savemoment.presentation.moments_list.viewmodel.MomentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MomentsViewModel(momentsRepository = get())
    }
}