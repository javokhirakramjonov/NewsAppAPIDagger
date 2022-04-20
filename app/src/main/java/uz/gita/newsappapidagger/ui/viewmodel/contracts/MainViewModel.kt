package uz.gita.newsappapidagger.ui.viewmodel.contracts

import androidx.lifecycle.LiveData
import uz.gita.newsappapidagger.data.model.ItemModel
import uz.gita.newsappapidagger.data.remote.response.ResponseNews

interface MainViewModel {
    val newsLiveData: LiveData<List<ItemModel>>
    val openWebLiveData: LiveData<ItemModel>
    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val titleLiveData: LiveData<String>
    val refreshLiveData: LiveData<Unit>

    fun refresh()
    fun titleChanged(title: String)
    fun loadNews(text: String)
    fun openWeb(item: ItemModel)
}