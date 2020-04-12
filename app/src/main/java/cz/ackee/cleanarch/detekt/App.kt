package cz.ackee.cleanarch.detekt

import android.app.Application
import cz.ackee.cleanarch.detekt.features.articles.di.articlesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, articlesModule))
        }
    }
}