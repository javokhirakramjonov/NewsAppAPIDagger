package uz.gita.newsappapidagger.data.remote.response

import com.google.gson.annotations.SerializedName
import uz.gita.newsappapidagger.data.local.ArticleEntity

sealed class NewsResponse {
    data class Response(

        @field:SerializedName("total")
        val total: Int,

        @field:SerializedName("category")
        val category: String,

        @field:SerializedName("articles")
        val articles: List<ArticlesItem>
    )

    data class ArticlesItem(

        @field:SerializedName("image")
        val image: String,

        @field:SerializedName("read_more")
        val readMore: String,

        @field:SerializedName("author")
        val author: String,

        @field:SerializedName("description")
        val description: String,

        @field:SerializedName("inshorts_link")
        val inshortsLink: String,

        @field:SerializedName("title")
        val title: String,

        @field:SerializedName("timestamp")
        val timestamp: String
    )
}

fun NewsResponse.ArticlesItem.toArticlesEntity(category: String): ArticleEntity = ArticleEntity(image, readMore,
    author, description, inshortsLink, title, timestamp, category)