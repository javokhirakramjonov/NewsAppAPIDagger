package uz.gita.newsappapidagger.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(

    val image: String,

    val readMore: String?,

    val author: String,

    val description: String,

    val inshortsLink: String,

    @PrimaryKey
    val title: String,

    val timestamp: String,

    val category: String
)