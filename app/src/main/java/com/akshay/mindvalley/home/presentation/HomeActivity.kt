package com.akshay.mindvalley.home.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.mindvalley.R
import com.akshay.mindvalley.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val mainViewModel: HomeViewModel by viewModels()
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var channelAdapter: ChannelAdapter
    private lateinit var newEpisodeAdapter: NewEpisodeAdapter
    private lateinit var categoryGridAdapter: NewEpisodeAdapter
    private lateinit var showMediaContentAdapter: ShowMediaContentAdapter
    private lateinit var categoryAdapter: CategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityHomeBinding.inflate(layoutInflater).also { binding = it }
        setContentView(binding.root)

        binding.swipeRefresh.setOnRefreshListener {
            mainViewModel.loadContent()
        }

        initializeAdapter()
        setUpObserver()
        mainViewModel.loadContent()
    }

    private fun setUpObserver() {
        with(mainViewModel) {
            newEpisodeList.observe(this@HomeActivity, showMediaContentAdapter::submitList)
            channelContentList.observe(this@HomeActivity, channelAdapter::submitList)
            categoryList.observe(this@HomeActivity, categoryAdapter::submitList)

            loading.observe(this@HomeActivity) { data ->
                data?.let {
                    with(binding) {
                        if (it) {
                            contentLayout.visibility = View.GONE
                            progressLoading.visibility = View.VISIBLE

                        } else {
                            contentLayout.visibility = View.VISIBLE
                            progressLoading.visibility = View.GONE
                        }
                        swipeRefresh.isRefreshing = false
                    }
                }

            }
        }
    }


    private fun initializeAdapter() {
        channelAdapter = ChannelAdapter()
        showMediaContentAdapter = ShowMediaContentAdapter()
        categoryAdapter = CategoryAdapter()


        newEpisodeAdapter = NewEpisodeAdapter(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            ), getString(R.string.title_new_episode), this, showMediaContentAdapter
        )

        categoryGridAdapter = NewEpisodeAdapter(
            GridLayoutManager(
                this,
                2
            ), getString(R.string.title_categories), this, categoryAdapter
        )

        concatAdapter = ConcatAdapter(
            newEpisodeAdapter,
            channelAdapter,
            categoryGridAdapter
        )
        binding.mainRecyclerView.adapter = concatAdapter
    }
}