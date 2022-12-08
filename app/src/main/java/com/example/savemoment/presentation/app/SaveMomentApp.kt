package com.example.savemoment.presentation.app

import android.app.Application
import com.example.savemoment.presentation.di.appModule
import com.example.savemoment.presentation.di.dataModule
import com.example.savemoment.presentation.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SaveMomentApp : Application() {

}