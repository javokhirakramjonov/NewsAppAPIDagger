package uz.gita.newsappapidagger.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappapidagger.domain.NewsRepository
import uz.gita.newsappapidagger.domain.impl.NewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NewsRepositoryModule {

    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}