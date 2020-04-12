package cz.ackee.cleanarch.detekt.features.articles.domain

import kotlinx.coroutines.flow.Flow

interface GetArticlesStreamUseCase {

    operator fun invoke(): Flow<List<Article>>
}

class GetArticlesStreamUseCaseImpl(
    private val articlesRepository: ArticlesRepository
) : GetArticlesStreamUseCase {

    override fun invoke() = articlesRepository.getArticlesStream()
}