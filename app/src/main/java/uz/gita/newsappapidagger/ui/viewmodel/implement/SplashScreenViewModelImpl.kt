package uz.gita.newsappapidagger.ui.viewmodel.implement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.newsappapidagger.ui.viewmodel.contracts.SplashScreenViewModel

class SplashScreenViewModelImpl : ViewModel(), SplashScreenViewModel {
    override val openNextScreenLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(1000)
            openNextScreenLiveData.postValue(Unit)
        }
    }
}