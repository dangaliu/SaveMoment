package com.example.savemoment.presentation.di

import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.data.storage.db.room.MomentsDatabase
import com.example.savemoment.data.storage.db.room.MomentsDatabaseStorage
import org.koin.dsl.module

val localDataSourceModule = module {

    single {
        MomentsDatabase.getInstance(context = get())
    }

    single<MomentStorage> {
        MomentsDatabaseStorage(momentsDb = get())
    }
}