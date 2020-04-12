package cz.ackee.cleanarch.detekt.features.articles.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import cz.ackee.cleanarch.detekt.features.articles.domain.Article

class ArticlesController(
    private val onArticleClick: (Article) -> Unit
) : TypedEpoxyController<List<Article>>() {

    override fun buildModels(data: List<Article>?) {
        data?.forEach {
            article {
                id(it.id)
                article(it)
                onArticleClick(onArticleClick)
            }
        }
    }
}