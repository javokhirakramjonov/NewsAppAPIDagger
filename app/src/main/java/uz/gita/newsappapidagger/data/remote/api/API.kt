package uz.gita.newsappapidagger.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsappapidagger.data.remote.response.NewsResponse

interface API {
    @GET("news")
    suspend fun getNewsByType(@Query("category") type: String): Response<NewsResponse.Response>
}