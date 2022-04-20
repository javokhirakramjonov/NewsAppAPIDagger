package uz.gita.contactapp3.utils

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//fun Fragment.openScreenWithoutSave(fm: Fragment) {
//    (this.requireActivity() as MainActivity).openScreenWithoutSave(fm)
//}
//
//fun Fragment.openScreen(fm: Fragment) {
//    (this.requireActivity() as MainActivity).openScreen(fm)
//}
//
//fun Fragment.closeScreen() {
//    (this.requireActivity() as MainActivity).closeScreen()
//}

fun <T> Call<T>.myEnqueue(onSuccess: (response: Response<T>) -> Unit, onFailure: (t: Throwable) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            onSuccess.invoke(response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure.invoke(t)
        }
    })
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun EditText.values(): String {
    return this.text.toString()
}




