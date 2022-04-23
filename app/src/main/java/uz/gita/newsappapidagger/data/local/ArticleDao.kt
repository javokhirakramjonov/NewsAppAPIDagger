package uz.gita.newsappapidagger.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappapidagger.data.remote.response.NewsResponse

@Dao
interface ArticleDao {
    @Query("SELECT * FROM ArticleEntity WHERE category = :category")
    suspend fun getArticleByCategory(category: String): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(news: List<ArticleEntity>)

    @Query("DELETE FROM ArticleEntity WHERE category = :category")
    suspend fun clear(category: String)
}