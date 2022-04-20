package uz.gita.newsappapidagger.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.newsappapidagger.BuildConfig.BASE_URL
import uz.gita.newsappapidagger.data.remote.api.API
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @[Provides Singleton]
    fun getOkHttpClientObject(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .build()

    @Provides
    @Singleton
    fun getRetrofitObject(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @[Singleton Provides]
    fun getAPI(retrofit: Retrofit): API = retrofit.create(API::class.java)
}