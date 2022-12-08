package com.example.savemoment.presentation.di

import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.data.storage.db.firebase.FirebaseFireStoreStorage
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<MomentStorage> {
        FirebaseFireStoreStorage()
    }
}