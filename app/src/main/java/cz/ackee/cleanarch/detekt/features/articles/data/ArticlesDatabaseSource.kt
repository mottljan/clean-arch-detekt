package cz.ackee.cleanarch.detekt.features.articles.data

import cz.ackee.cleanarch.detekt.features.articles.domain.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesDatabaseSource {

    fun getArticlesStream(): Flow<List<Article>>

    suspend fun insert(article: Article)

    suspend fun delete(article: Article)
}