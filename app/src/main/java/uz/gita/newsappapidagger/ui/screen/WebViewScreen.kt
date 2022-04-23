package uz.gita.newsappapidagger.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappapidagger.R
import uz.gita.newsappapidagger.databinding.ScreenWebBinding
import uz.gita.newsappapidagger.ui.viewmodel.contracts.WebViewModel
import uz.gita.newsappapidagger.ui.viewmodel.implement.WebViewModelImpl
import uz.gita.newsappapidagger.utils.isConnected

@AndroidEntryPoint
class WebViewScreen : Fragment(R.layout.screen_web) {
    private val model: WebViewModel by viewModels<WebViewModelImpl>()
    private val binding by viewBinding(ScreenWebBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        title.text = requireArguments().getString("TITLE")
        if (title.text.toString().length >= 30)
            title.text = title.text.toString().substring(0, 27) + "..."
        back.setOnClickListener { model.openMainScreen() }

        refresh.setOnRefreshListener {
            model.refresh()
        }

        model.loadSource()
        model.noDataLiveData.observe(viewLifecycleOwner, noDataObserver)
        model.openMainScreenLiveData.observe(viewLifecycleOwner, openMainScreenObserver)
        model.sourceLiveData.observe(viewLifecycleOwner, sourceObserver)
    }

    private val noDataObserver = Observer<Boolean> {
        if (it) {
            binding.webView.visibility = View.VISIBLE
            binding.errorData.visibility = View.GONE
        } else {
            binding.webView.visibility = View.GONE
            binding.errorData.visibility = View.VISIBLE
        }
    }

    private val openMainScreenObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val sourceObserver = Observer<Unit> {
        if (requireArguments().getString("URL") != null && isConnected()) {
            model.changeState(true)
            binding.webView.loadUrl(requireArguments().getString("URL")!!)
        } else {
            model.changeState(false)
        }

        binding.refresh.isRefreshing = false
    }
}