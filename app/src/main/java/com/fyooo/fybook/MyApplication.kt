package com.fyooo.fybook

import android.app.Application
import com.fyooo.core.di.databaseModule
import com.fyooo.core.di.networkModule
import com.fyooo.core.di.repositoryModule
import com.fyooo.fybook.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(repositoryModule, viewModelModule, databaseModule, networkModule)
        }
    }
}