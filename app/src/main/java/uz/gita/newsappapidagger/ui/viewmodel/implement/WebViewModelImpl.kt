package uz.gita.newsappapidagger.ui.viewmodel.implement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.newsappapidagger.ui.viewmodel.contracts.WebViewModel

class WebViewModelImpl : ViewModel(), WebViewModel {
    override val sourceLiveData = MutableLiveData<Unit>()
    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val noDataLiveData = MutableLiveData<Boolean>()

    override fun refresh() {
        loadSource()
    }

    override fun changeState(state: Boolean) {
        noDataLiveData.value = state
    }

    override fun openMainScreen() {
        openMainScreenLiveData.value = Unit
    }

    override fun loadSource() {
        sourceLiveData.value = Unit
    }
}