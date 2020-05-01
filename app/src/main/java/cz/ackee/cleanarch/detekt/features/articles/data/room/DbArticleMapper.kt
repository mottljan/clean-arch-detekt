package cz.ackee.cleanarch.detekt.features.articles.data.room

import cz.ackee.cleanarch.detekt.features.articles.domain.Article

/**
 * Mapper between [Article] and [DbArticle].
 */
class DbArticleMapper {

    fun mapToArticle(dbArticle: DbArticle) = dbArticle.map()

    private fun DbArticle.map(): Article {
        return Article(
            id = id,
            title = title,
            summary = summary,
            author = author
        )
    }

    fun mapToDbArticle(article: Article) = article.map()

    private fun Article.map(): DbArticle {
        return DbArticle(
            id = id,
            title = title,
            summary = summary,
            author = author
        )
    }
}