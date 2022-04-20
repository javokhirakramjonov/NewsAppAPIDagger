package uz.gita.newsappapidagger.data.remote.response

import uz.gita.newsappapidagger.data.model.ItemModel

class ResponseNews(
    val category: String,
    val total: Int,
    val articles: List<ItemModel>
)