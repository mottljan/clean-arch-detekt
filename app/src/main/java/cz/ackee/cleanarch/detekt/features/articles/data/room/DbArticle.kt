package cz.ackee.cleanarch.detekt.features.articles.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.ackee.cleanarch.detekt.features.articles.domain.Article

@Entity(tableName = "article")
data class DbArticle(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val summary: String,
    val author: String
)