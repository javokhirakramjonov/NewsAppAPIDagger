package uz.gita.newsappapidagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappapidagger.data.local.ArticleDatabase

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun getDatabase(@ApplicationContext context: Context) = ArticleDatabase.getDatabase(context)

    @Provides
    fun getArticleDao(database: ArticleDatabase) = database.getArticleDao()
}