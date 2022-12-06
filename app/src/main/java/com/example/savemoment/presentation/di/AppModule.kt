package com.example.savemoment.presentation.di

import com.example.savemoment.presentation.ui.addMoment.viewmodel.AddMomentViewModel
import com.example.savemoment.presentation.ui.moments_list.viewmodel.MomentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MomentsViewModel(getMomentsUseCase = get(), deleteMomentUseCase = get())
    }
    viewModel {
        AddMomentViewModel(
            saveMomentUseCase = get(),
            updateMomentUseCase = get()
        )
    }
}