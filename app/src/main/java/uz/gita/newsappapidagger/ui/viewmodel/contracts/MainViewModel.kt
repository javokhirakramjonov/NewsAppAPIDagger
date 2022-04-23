package uz.gita.newsappapidagger.ui.viewmodel.contracts

import androidx.lifecycle.LiveData
import uz.gita.newsappapidagger.data.local.ArticleEntity
import uz.gita.newsappapidagger.data.remote.response.NewsResponse

interface MainViewModel {
    val newsLiveData: LiveData<List<ArticleEntity>>
    val openWebLiveData: LiveData<ArticleEntity>
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val titleLiveData: LiveData<String>
    val refreshLiveData: LiveData<Unit>
    val openDrawerLiveData: LiveData<Unit>
    val closeDrawerLiveData: LiveData<Unit>
    val noDataLiveData : LiveData<Boolean>

    fun changeState(state: Boolean)
    fun openDrawer()
    fun closeDrawer()
    fun refresh()
    fun titleChanged(title: String)
    fun loadNews(text: String)
    fun openWeb(item: ArticleEntity)
}