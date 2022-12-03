package com.example.savemoment.presentation.di

import com.example.savemoment.domain.usecase.DeleteMomentUseCase
import com.example.savemoment.domain.usecase.GetMomentsUseCase
import com.example.savemoment.domain.usecase.SaveMomentUseCase
import com.example.savemoment.domain.usecase.UpdateMomentUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        DeleteMomentUseCase(momentsRepository = get())
    }

    factory {
        GetMomentsUseCase(momentsRepository = get())
    }

    factory {
        SaveMomentUseCase(momentsRepository = get())
    }

    factory {
        UpdateMomentUseCase(momentsRepository = get())
    }
}