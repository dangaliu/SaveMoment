package com.example.savemoment.presentation.di

import com.example.savemoment.data.db.room.MomentsDatabase
import com.example.savemoment.data.repository.MomentsRepositoryImpl
import com.example.savemoment.domain.repository.MomentsRepository
import org.koin.dsl.module

val dataModule = module {

    single {
        MomentsDatabase.getInstance(context = get())
    }

    single<MomentsRepository> {
        MomentsRepositoryImpl(momentDb = get())
    }
}