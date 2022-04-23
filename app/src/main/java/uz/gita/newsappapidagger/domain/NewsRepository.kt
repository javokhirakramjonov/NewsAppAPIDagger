package uz.gita.newsappapidagger.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappapidagger.data.local.ArticleEntity

interface NewsRepository {
    fun getNewsByCategory(category: String): Flow<Result<List<ArticleEntity>>>
}