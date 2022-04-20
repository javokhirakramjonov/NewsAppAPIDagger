package uz.gita.newsappapidagger.ui.viewmodel.contracts

import androidx.lifecycle.LiveData
import uz.gita.newsappapidagger.data.model.ItemModel

interface WebViewModel {
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val sourceLiveData: LiveData<Unit>
    val openMainScreenLiveData: LiveData<Unit>

    fun openMainScreen()
    fun loadSource()
}