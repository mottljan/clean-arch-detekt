package cz.ackee.cleanarch.detekt.features.articles.domain

interface InsertRandomArticleUseCase {

    suspend operator fun invoke()
}

class InsertRandomArticleUseCaseImpl(
    private val articlesRepository: ArticlesRepository
) : InsertRandomArticleUseCase {

    private val randomArticleGenerator =
        RandomArticleGenerator()

    override suspend fun invoke() {
        articlesRepository.insert(randomArticleGenerator.generate())
    }
}

private class RandomArticleGenerator {

    private val articles = listOf(
        Article(
            0,
            "Inline functions — under the hood",
            "Kotlin Vocabulary",
            "Florina Muntenescu"
        ),
        Article(
            0,
            "Merge adapters sequentially with MergeAdapter",
            "Use case example: displaying a list header and footer",
            "Florina Muntenescu"
        ),
        Article(
            0,
            "Coroutines & Patterns for work that shouldn’t be cancelled",
            "Cancellation and Exceptions in Coroutines (Part 4)",
            "Manuel Vivo"
        ),
        Article(
            0,
            "Customizing WorkManager — Fundamentals",
            "An article about WorkManager custom configuration. What is it, why you may need one and how to implement it.",
            "Pietro Maggi"
        )
    )

    fun generate() = articles[articles.indices.random()]
}