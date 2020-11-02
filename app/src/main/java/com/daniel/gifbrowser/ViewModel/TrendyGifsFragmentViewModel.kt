package com.daniel.gifbrowser.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.gifbrowser.Data.TrendyGifsRepository
import com.daniel.gifbrowser.Domain.TrendyGifsRequest
import com.daniel.gifbrowser.Domain.TrendyGifsResponse
import java.util.ArrayList

class TrendyGifsFragmentViewModel : ViewModel() {

    private var _mutableLiveData = MutableLiveData<TrendyGifsResponse>()
    val mutableLiveData : LiveData<TrendyGifsResponse>
        get() = _mutableLiveData
    private val trendyGifsRepository = TrendyGifsRepository()
    /**
     * sets the Live data value to be the given arraylist of commits
     * @return LiveData the Live data representing the list of commits
     */
    fun getTrendyGifs(trendyGifsRequest : TrendyGifsRequest): LiveData<TrendyGifsResponse>? {
        _mutableLiveData = trendyGifsRepository.requestTrendyGifs(trendyGifsRequest) as MutableLiveData<TrendyGifsResponse>
        return mutableLiveData
    }
}