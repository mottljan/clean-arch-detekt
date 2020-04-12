package cz.ackee.cleanarch.detekt.features.articles.data.db

import cz.ackee.cleanarch.detekt.features.articles.data.ArticlesDatabaseSource
import cz.ackee.cleanarch.detekt.features.articles.domain.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRoomDataSource(
    private val articlesDao: ArticlesDao
) : ArticlesDatabaseSource {

    override fun getArticlesStream() = articlesDao.getArticlesStream().mapToDomain()

    override suspend fun insert(article: Article) {
        articlesDao.insert(article.mapToDb())
    }

    override suspend fun delete(article: Article) {
        articlesDao.delete(article.mapToDb())
    }

    private fun Flow<List<DbArticle>>.mapToDomain() = map { it.mapToDomain() }

    private fun List<DbArticle>.mapToDomain() = map { it.mapToDomain() }
}