package cz.ackee.cleanarch.detekt.features

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.ackee.cleanarch.detekt.features.articles.data.db.DbArticle
import cz.ackee.cleanarch.detekt.features.articles.data.db.ArticlesDao

@Database(entities = [DbArticle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getArticlesDao(): ArticlesDao
}