package cz.ackee.cleanarch.detekt.features.articles.data

import cz.ackee.cleanarch.detekt.features.articles.domain.Article
import cz.ackee.cleanarch.detekt.features.articles.domain.ArticlesRepository

class ArticlesRepositoryImpl(
    private val articlesDatabaseSource: ArticlesDatabaseSource
) : ArticlesRepository {

    override fun getArticlesStream() = articlesDatabaseSource.getArticlesStream()

    override suspend fun insert(article: Article) {
        articlesDatabaseSource.insert(article)
    }

    override suspend fun delete(article: Article) {
        articlesDatabaseSource.delete(article)
    }
}