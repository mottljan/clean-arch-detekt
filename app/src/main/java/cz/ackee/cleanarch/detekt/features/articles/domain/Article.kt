package cz.ackee.cleanarch.detekt.features.articles.domain

data class Article(
    val id: Long,
    val title: String,
    val summary: String,
    val author: String
)