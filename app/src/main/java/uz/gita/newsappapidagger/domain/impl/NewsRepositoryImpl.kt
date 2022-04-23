package uz.gita.newsappapidagger.domain.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappapidagger.data.local.ArticleDao
import uz.gita.newsappapidagger.data.local.ArticleEntity
import uz.gita.newsappapidagger.data.remote.api.API
import uz.gita.newsappapidagger.data.remote.response.toArticlesEntity
import uz.gita.newsappapidagger.domain.NewsRepository
import uz.gita.newsappapidagger.utils.isConnected
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val api: API,
    private val dao: ArticleDao
) : NewsRepository {

    override fun getNewsByCategory(category: String) = flow<Result<List<ArticleEntity>>> {
        if(isConnected()) {

            val response = api.getNewsByType(category)

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.success(it.articles.map { item -> item.toArticlesEntity(category) }))
                    dao.clear(category)
                    val temp = it.articles.map { item -> item.toArticlesEntity(category) }
                    dao.insertArticle(temp)
                    return@flow
                }
            } else
                emit(Result.failure(Exception("ERROR")))
        }
        else {
            emit(Result.success(dao.getArticleByCategory(category)))
        }
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)
}