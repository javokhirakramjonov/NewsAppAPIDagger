package uz.gita.newsappapidagger.data.remote.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsappapidagger.data.remote.response.ResponseNews

interface API {
    @GET("news")
    fun getNewsByType(@Query("category") type : String) : Call<ResponseNews>
}