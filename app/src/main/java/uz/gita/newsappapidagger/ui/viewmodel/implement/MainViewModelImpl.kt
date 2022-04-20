package uz.gita.newsappapidagger.ui.viewmodel.implement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.contactapp3.utils.myEnqueue
import uz.gita.newsappapidagger.data.model.ItemModel
import uz.gita.newsappapidagger.data.remote.api.API
import uz.gita.newsappapidagger.ui.viewmodel.contracts.MainViewModel
import uz.gita.newsappapidagger.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val api: API
) : ViewModel(), MainViewModel {
    override val newsLiveData = MutableLiveData<List<ItemModel>>()
    override val openWebLiveData = MutableLiveData<ItemModel>()
    override val errorLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val titleLiveData = MutableLiveData<String>()
    override val refreshLiveData = MutableLiveData<Unit>()
    override val openDrawerLiveData = MutableLiveData<Unit>()
    override val closeDrawerLiveData = MutableLiveData<Unit>()

    override fun openDrawer() {
        openDrawerLiveData.value = Unit
    }

    override fun closeDrawer() {
        closeDrawerLiveData.value = Unit
    }

    companion object {
        var title = "ALL"
    }

    override fun refresh() {
        titleLiveData.value = title
        loadNews(title.lowercase())
    }

    override fun titleChanged(newTitle: String) {
        title = newTitle
        titleLiveData.value = title
        loadNews(title.lowercase())
    }

    override fun loadNews(text: String) {
        progressLiveData.value = true
        if (!isConnected())
            errorLiveData.value = "Not Internet connection"
        api.getNewsByType(text).myEnqueue(
            { response ->
                progressLiveData.value = false
                if (response.isSuccessful) {
                    newsLiveData.value = response.body()!!.articles
                } else {
                    errorLiveData.value = "Error 1"
                }
            }, {
                errorLiveData.value = "Error 2"
            }
        )
    }

    override fun openWeb(item: ItemModel) {
        openWebLiveData.value = item
    }

}