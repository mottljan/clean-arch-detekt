package cz.ackee.cleanarch.detekt.features.articles.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM article")
    fun getArticlesStream(): Flow<List<DbArticle>>

    @Insert
    suspend fun insert(article: DbArticle)

    @Delete
    suspend fun delete(article: DbArticle)
}