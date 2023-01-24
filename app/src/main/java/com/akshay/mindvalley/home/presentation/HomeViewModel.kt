package com.akshay.mindvalley.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.mindvalley.home.domain.model.CategoryItem
import com.akshay.mindvalley.home.domain.model.ChannelItem
import com.akshay.mindvalley.home.domain.model.MediaItem
import com.akshay.mindvalley.home.domain.usecase.GetCategoryListUseCase
import com.akshay.mindvalley.home.domain.usecase.GetChannelContentListUseCase
import com.akshay.mindvalley.home.domain.usecase.GetNewEpisodeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val channelContentListUseCase: GetChannelContentListUseCase,
    private val categoryListUseCase: GetCategoryListUseCase,
    private val newEpisodeListUseCase: GetNewEpisodeListUseCase
) : ViewModel() {

    private val _channelContentList = MutableLiveData<List<ChannelItem>>()
    var channelContentList: LiveData<List<ChannelItem>> = _channelContentList

    private val _categoryList = MutableLiveData<List<CategoryItem>>()
    var categoryList: LiveData<List<CategoryItem>> = _categoryList

    private val _newEpisodeList = MutableLiveData<List<MediaItem>>()
    var newEpisodeList: LiveData<List<MediaItem>> = _newEpisodeList

    private val _loading = MutableLiveData(true)
    var loading: LiveData<Boolean> = _loading


    @OptIn(ExperimentalCoroutinesApi::class)
    fun loadContent() {

        viewModelScope.launch(Dispatchers.IO) {

            val resultChannelDeferred = async {
                channelContentListUseCase.getChannelList()
            }
            val resultNewEpisodeDeferred = async {
                newEpisodeListUseCase.getNewEpisodeList()
            }

            val resultCategoryDeferred = async {
                categoryListUseCase.getCategoryList()
            }


            if (resultChannelDeferred.await().isSuccess && resultNewEpisodeDeferred.await().isSuccess && resultCategoryDeferred.await().isSuccess) {
                _channelContentList.postValue(resultChannelDeferred.getCompleted().getOrNull())
                _newEpisodeList.postValue(resultNewEpisodeDeferred.getCompleted().getOrNull())
                _categoryList.postValue(resultCategoryDeferred.getCompleted().getOrNull())
                _loading.postValue(false)
            }
        }
    }
}