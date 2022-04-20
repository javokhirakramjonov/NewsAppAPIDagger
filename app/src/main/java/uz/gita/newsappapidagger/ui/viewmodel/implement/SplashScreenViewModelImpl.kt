package uz.gita.newsappapidagger.ui.viewmodel.implement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.newsappapidagger.ui.viewmodel.contracts.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor() : ViewModel(), SplashScreenViewModel {
    override val openNextScreenLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            openNextScreenLiveData.postValue(Unit)
        }
    }
}