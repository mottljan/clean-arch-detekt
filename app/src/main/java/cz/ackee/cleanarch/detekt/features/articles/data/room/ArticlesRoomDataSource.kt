package cz.ackee.cleanarch.detekt.features.articles.data.room

import cz.ackee.cleanarch.detekt.features.articles.data.ArticlesDatabaseSource
import cz.ackee.cleanarch.detekt.features.articles.domain.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRoomDataSource(
    private val articlesDao: ArticlesDao
) : ArticlesDatabaseSource {

    private val mapper = DbArticleMapper()

    override fun getArticlesStream() = articlesDao.getArticlesStream().toArticlesStream()

    override suspend fun insert(article: Article) {
        articlesDao.insert(article.toDbArticle())
    }

    override suspend fun delete(article: Article) {
        articlesDao.delete(article.toDbArticle())
    }

    private fun Flow<List<DbArticle>>.toArticlesStream() = map { it.toArticles() }

    private fun List<DbArticle>.toArticles() = map { it.toArticle() }

    private fun DbArticle.toArticle() = mapper.mapToArticle(this)

    private fun Article.toDbArticle() = mapper.mapToDbArticle(this)
}