package cz.ackee.cleanarch.detekt.features.articles.di

import cz.ackee.cleanarch.detekt.features.core.data.db.AppDatabase
import cz.ackee.cleanarch.detekt.features.articles.data.ArticlesDatabaseSource
import cz.ackee.cleanarch.detekt.features.articles.data.ArticlesRepositoryImpl
import cz.ackee.cleanarch.detekt.features.articles.data.room.ArticlesRoomDataSource
import cz.ackee.cleanarch.detekt.features.articles.domain.ArticlesRepository
import cz.ackee.cleanarch.detekt.features.articles.domain.DeleteArticleUseCase
import cz.ackee.cleanarch.detekt.features.articles.domain.DeleteArticleUseCaseImpl
import cz.ackee.cleanarch.detekt.features.articles.domain.GetArticlesStreamUseCase
import cz.ackee.cleanarch.detekt.features.articles.domain.GetArticlesStreamUseCaseImpl
import cz.ackee.cleanarch.detekt.features.articles.domain.InsertRandomArticleUseCase
import cz.ackee.cleanarch.detekt.features.articles.domain.InsertRandomArticleUseCaseImpl
import cz.ackee.cleanarch.detekt.features.articles.presentation.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val articlesModule = module {
    data()
    domain()
    presentation()
}

private fun Module.data() {
    single { get<AppDatabase>().getArticlesDao() }
    single<ArticlesDatabaseSource> { ArticlesRoomDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
}

private fun Module.domain() {
    factory<GetArticlesStreamUseCase> { GetArticlesStreamUseCaseImpl(get()) }
    factory<InsertRandomArticleUseCase> { InsertRandomArticleUseCaseImpl(get()) }
    factory<DeleteArticleUseCase> { DeleteArticleUseCaseImpl(get()) }
}

private fun Module.presentation() {
    viewModel { ArticlesViewModel(get(), get(), get()) }
}
