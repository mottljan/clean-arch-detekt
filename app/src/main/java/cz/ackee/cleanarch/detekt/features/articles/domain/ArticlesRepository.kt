package cz.ackee.cleanarch.detekt.features.articles.domain

import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    fun getArticlesStream(): Flow<List<Article>>

    suspend fun insert(article: Article)

    suspend fun delete(article: Article)
}