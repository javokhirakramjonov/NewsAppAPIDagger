package uz.gita.newsappapidagger.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactapp3.utils.showToast
import uz.gita.newsappapidagger.R
import uz.gita.newsappapidagger.databinding.ScreenWebBinding
import uz.gita.newsappapidagger.ui.viewmodel.contracts.WebViewModel
import uz.gita.newsappapidagger.ui.viewmodel.implement.WebViewModelImpl

class WebViewScreen : Fragment(R.layout.screen_web) {
    private val model: WebViewModel by viewModels<WebViewModelImpl>()
    private val binding by viewBinding(ScreenWebBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        title.text = requireArguments().getString("TITLE")
        if(title.text.toString().length >= 30)
            title.text = title.text.toString().substring(0,27) + "..."
        back.setOnClickListener { model.openMainScreen() }

        model.loadSource()

        model.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        model.openMainScreenLiveData.observe(viewLifecycleOwner, openMainScreenObserver)
        model.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        model.sourceLiveData.observe(viewLifecycleOwner, sourceObserver)
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }

    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_webViewScreen_to_mainScreen)
    }

    private val progressObserver = Observer<Boolean> {
        if (it) binding.progress.show()
        else binding.progress.hide()
    }

    private val sourceObserver = Observer<Unit> {
        if(requireArguments().getString("URL") != null)
            binding.webView.loadUrl(requireArguments().getString("URL")!!)
        else
            showToast("error url")
        binding.progress.hide()
    }

    
}