package uz.gita.newsappapidagger.ui.viewmodel.implement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsappapidagger.data.model.ItemModel
import uz.gita.newsappapidagger.data.remote.api.API
import uz.gita.newsappapidagger.ui.viewmodel.contracts.WebViewModel
import uz.gita.newsappapidagger.utils.isConnected
import javax.inject.Inject

class WebViewModelImpl : ViewModel(), WebViewModel {
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val sourceLiveData = MutableLiveData<Unit>()
    override val openMainScreenLiveData = MutableLiveData<Unit>()

    override fun openMainScreen() {
        openMainScreenLiveData.value = Unit
    }

    override fun loadSource() {
        progressLiveData.value = true
        if (!isConnected())
            errorLiveData.value = "Not Internet connection"
        sourceLiveData.value = Unit
    }
}