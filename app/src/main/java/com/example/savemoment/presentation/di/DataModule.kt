package com.example.savemoment.presentation.di

import com.example.savemoment.data.storage.db.room.MomentsDatabase
import com.example.savemoment.data.repository.MomentsRepositoryImpl
import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.data.storage.db.room.MomentsDatabaseStorage
import com.example.savemoment.domain.repository.MomentsRepository
import org.koin.dsl.module

val dataModule = module {

    single<MomentsRepository> {
        MomentsRepositoryImpl(momentStorage = get())
    }
}