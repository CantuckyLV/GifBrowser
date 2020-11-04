package com.daniel.gifbrowser.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.gifbrowser.Data.TrendyGifsRepository
import com.daniel.gifbrowser.Domain.TrendyGifsRequest
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSearchRequest

class TrendyGifsFragmentViewModel : ViewModel() {

    private var _mutableLiveData = MutableLiveData<GifListResponse>()
    val mutableLiveData : LiveData<GifListResponse>
        get() = _mutableLiveData
    private val trendyGifsRepository = TrendyGifsRepository()
    /**
     * sets the Live data value to be the given arraylist of commits
     * @return LiveData the Live data representing the list of commits
     */
    fun getTrendyGifs(trendyGifsRequest : TrendyGifsRequest): LiveData<GifListResponse>? {
        _mutableLiveData = trendyGifsRepository.requestTrendyGifs(trendyGifsRequest) as MutableLiveData<GifListResponse>
        return mutableLiveData
    }
    fun getGifSearch(gifSearchRequest : GifSearchRequest): LiveData<GifListResponse>? {
        _mutableLiveData = trendyGifsRepository.requestGifSearch(gifSearchRequest) as MutableLiveData<GifListResponse>
        return mutableLiveData
    }
}