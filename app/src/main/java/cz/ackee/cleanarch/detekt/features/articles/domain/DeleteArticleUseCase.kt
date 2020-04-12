package cz.ackee.cleanarch.detekt.features.articles.domain

interface DeleteArticleUseCase {

    suspend operator fun invoke(article: Article)
}

class DeleteArticleUseCaseImpl(
    private val articlesRepository: ArticlesRepository
) : DeleteArticleUseCase {

    override suspend fun invoke(article: Article) {
        articlesRepository.delete(article)
    }
}