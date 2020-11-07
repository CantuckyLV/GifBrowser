package com.daniel.gifbrowser.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Data.TrendyGifsRepository
import com.daniel.gifbrowser.Domain.TrendyGifsRequest
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSearchRequest
import com.daniel.gifbrowser.Domain.GifSimpleObject
import javax.inject.Inject

class TrendyGifsFragmentViewModel @Inject constructor(private val gifDB : GifDB ) : ViewModel() {

    private var _mutableLiveData = MutableLiveData<GifListResponse>()
    val mutableLiveData : LiveData<GifListResponse>
        get() = _mutableLiveData

    private val trendyGifsRepository = TrendyGifsRepository(gifDB.gifCRUD())

    fun getTrendyGifs(trendyGifsRequest : TrendyGifsRequest): LiveData<GifListResponse>? {
        _mutableLiveData = trendyGifsRepository.requestTrendyGifs(trendyGifsRequest) as MutableLiveData<GifListResponse>
        return mutableLiveData
    }
    fun getTrendyGifs(trendyGifsRequest : TrendyGifsRequest,mockedResponse : GifListResponse): LiveData<GifListResponse>? {
        _mutableLiveData.value = mockedResponse
        return mutableLiveData
    }
    fun getGifSearch(gifSearchRequest : GifSearchRequest): LiveData<GifListResponse>? {
        _mutableLiveData = trendyGifsRepository.requestGifSearch(gifSearchRequest) as MutableLiveData<GifListResponse>
        return mutableLiveData
    }
    fun getGifSearch(gifSearchRequest : GifSearchRequest,mockedResponse : GifListResponse): LiveData<GifListResponse>? {
        _mutableLiveData.value = mockedResponse
        return mutableLiveData
    }
    fun saveGif(gifSimpleObject: GifSimpleObject){
        trendyGifsRepository.saveGif(gifSimpleObject)
    }
    fun getGifs(): LiveData<List<GifSimpleObject?>?>{
        return trendyGifsRepository.getGifs()
    }
}