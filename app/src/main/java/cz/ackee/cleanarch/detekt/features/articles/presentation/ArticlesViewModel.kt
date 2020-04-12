package cz.ackee.cleanarch.detekt.features.articles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cz.ackee.cleanarch.detekt.features.articles.domain.Article
import cz.ackee.cleanarch.detekt.features.articles.domain.DeleteArticleUseCase
import cz.ackee.cleanarch.detekt.features.articles.domain.GetArticlesStreamUseCase
import cz.ackee.cleanarch.detekt.features.articles.domain.InsertRandomArticleUseCase
import kotlinx.coroutines.launch

class ArticlesViewModel(
    getArticlesStreamUseCase: GetArticlesStreamUseCase,
    private val insertRandomArticleUseCase: InsertRandomArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
) : ViewModel() {

    val articles = getArticlesStreamUseCase().asLiveData()

    fun insertRandomArticle() {
        viewModelScope.launch { insertRandomArticleUseCase() }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch { deleteArticleUseCase(article) }
    }
}