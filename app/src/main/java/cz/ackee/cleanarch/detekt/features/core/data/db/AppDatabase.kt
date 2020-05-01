package cz.ackee.cleanarch.detekt.features.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.ackee.cleanarch.detekt.features.articles.data.room.DbArticle
import cz.ackee.cleanarch.detekt.features.articles.data.room.ArticlesDao

@Database(entities = [DbArticle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getArticlesDao(): ArticlesDao
}