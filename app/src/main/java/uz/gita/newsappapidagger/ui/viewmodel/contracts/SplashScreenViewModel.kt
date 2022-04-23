package uz.gita.newsappapidagger.ui.viewmodel.contracts

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val openNextScreenLiveData: LiveData<Unit>
}