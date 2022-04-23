package uz.gita.newsappapidagger.ui.viewmodel.implement

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsappapidagger.data.local.ArticleEntity
import uz.gita.newsappapidagger.domain.NewsRepository
import uz.gita.newsappapidagger.ui.viewmodel.contracts.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(), MainViewModel {
    override val newsLiveData = MutableLiveData<List<ArticleEntity>>()
    override val openWebLiveData = MutableLiveData<ArticleEntity>()
    override val errorLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val titleLiveData = MutableLiveData<String>()
    override val refreshLiveData = MutableLiveData<Unit>()
    override val openDrawerLiveData = MutableLiveData<Unit>()
    override val closeDrawerLiveData = MutableLiveData<Unit>()
    override val noDataLiveData = MutableLiveData<Boolean>()

    override fun changeState(state: Boolean) {
        noDataLiveData.value = state
    }

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

    override fun titleChanged(x: String) {
        title = x
        titleLiveData.value = x
        loadNews(x.lowercase())
    }

    override fun loadNews(text: String) {
        progressLiveData.value = true
        repository.getNewsByCategory(text).onEach { response ->
            progressLiveData.value = false
            response.onSuccess {
                newsLiveData.postValue(it)
                if (it.isEmpty())
                    changeState(false)
                else
                    changeState(true)
            }
            response.onFailure {
                changeState(false)
                errorLiveData.value = "Error occurred"
            }
        }.catch {
            progressLiveData.value = false
            changeState(false)
            errorLiveData.postValue("Error occurred")
        }.launchIn(viewModelScope)
    }

    override fun openWeb(item: ArticleEntity) {
        openWebLiveData.value = item
    }

}