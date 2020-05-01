package cz.ackee.cleanarch.detekt

import androidx.room.Room
import cz.ackee.cleanarch.detekt.features.core.data.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app").build()
    }
}