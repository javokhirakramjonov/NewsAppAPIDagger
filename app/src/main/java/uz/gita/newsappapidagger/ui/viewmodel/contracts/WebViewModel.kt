package uz.gita.newsappapidagger.ui.viewmodel.contracts

import androidx.lifecycle.LiveData

interface WebViewModel {
    val sourceLiveData: LiveData<Unit>
    val openMainScreenLiveData: LiveData<Unit>
    val noDataLiveData : LiveData<Boolean>

    fun refresh()
    fun changeState(state: Boolean)
    fun openMainScreen()
    fun loadSource()
}