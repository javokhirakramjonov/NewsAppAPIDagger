package uz.gita.newsappapidagger.ui.screen

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.contactapp3.utils.showToast
import uz.gita.newsappapidagger.R
import uz.gita.newsappapidagger.data.model.ItemModel
import uz.gita.newsappapidagger.databinding.ScreenExtraBinding
import uz.gita.newsappapidagger.ui.adapter.CategoryAdapter
import uz.gita.newsappapidagger.ui.adapter.NewsAdapter
import uz.gita.newsappapidagger.ui.viewmodel.contracts.MainViewModel
import uz.gita.newsappapidagger.ui.viewmodel.implement.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_extra) {
    private val model: MainViewModel by viewModels<MainViewModelImpl>()
    private val binding by viewBinding(ScreenExtraBinding::bind)
    private val adapter by lazy { NewsAdapter() }
    private val adapterCategory by lazy { CategoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        inner.recyclerView.adapter = adapter
        inner.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        model.refresh()

        recyclerViewCategory.adapter = adapterCategory
        recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext())

        inner.menu.setOnClickListener {
            model.openDrawer()
        }

        adapterCategory.setMyClickListener {
            model.titleChanged(it)
            model.closeDrawer()
        }
        binding.inner.refresh.setOnRefreshListener {
            model.refresh()
        }

        adapter.setMyClickListener {
            model.openWeb(it)
        }

        model.titleLiveData.observe(viewLifecycleOwner, titleObserver)
        model.refreshLiveData.observe(viewLifecycleOwner, refreshObserver)
        model.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        model.newsLiveData.observe(viewLifecycleOwner, newsObserver)
        model.openWebLiveData.observe(viewLifecycleOwner, openWebObserver)
        model.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        model.openDrawerLiveData.observe(viewLifecycleOwner, openDrawerObserver)
        model.closeDrawerLiveData.observe(viewLifecycleOwner, closeDrawerObserver)

    }

    private val openDrawerObserver = Observer<Unit> {
        binding.drawer.openDrawer(GravityCompat.START)
    }

    private val closeDrawerObserver = Observer<Unit> {
        binding.drawer.closeDrawer(GravityCompat.START)
    }

    private val titleObserver = Observer<String> {
        binding.inner.title.text = it
    }

    private val refreshObserver = Observer<Unit> {
        model.refresh()
    }

    private val errorObserver = Observer<String> {
        showToast(it)
    }

    private val newsObserver = Observer<List<ItemModel>> {
        adapter.loadList(it)
        binding.inner.refresh.isRefreshing = false
    }

    private val openWebObserver = Observer<ItemModel> {
        val bundle = Bundle()
        bundle.putString("TITLE", it.title)
        bundle.putString("URL", it.read_more)
        findNavController().navigate(R.id.action_mainScreen_to_webViewScreen, bundle)
    }

    private val progressObserver = Observer<Boolean> {
        if (it) binding.inner.progress.show()
        else binding.inner.progress.hide()
    }
}