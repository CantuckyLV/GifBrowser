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
/**
 * ViewModel Class for TrendyGifsFragment
 * @param gifDB injected data base for  repository to comunicate with
 */

class TrendyGifsFragmentViewModel @Inject constructor(private val gifDB : GifDB ) : ViewModel() {

    private var _mutableLiveData = MutableLiveData<GifListResponse>()
    val mutableLiveData : LiveData<GifListResponse>
        get() = _mutableLiveData

    private val trendyGifsRepository = TrendyGifsRepository(gifDB.gifCRUD())

    /**
     * Calls the requestTrendyGifs on the repository to fetch trending gifs
     * @param trendyGifsRequest the Object representing the request to the server
     * @return Observable the response from the repository
     */
    fun getTrendyGifs(trendyGifsRequest : TrendyGifsRequest): LiveData<GifListResponse>? {
        _mutableLiveData = trendyGifsRepository.requestTrendyGifs(trendyGifsRequest) as MutableLiveData<GifListResponse>
        return mutableLiveData
    }
    fun getTrendyGifs(trendyGifsRequest : TrendyGifsRequest,mockedResponse : GifListResponse): LiveData<GifListResponse>? {
        _mutableLiveData.value = mockedResponse
        return mutableLiveData
    }

    /**
     * Calls the requestGifSearch on the repository to fetch a gif search
     * @param gifSearchRequest the Object representing the request to the server
     * @return Observable the response from the repository
     */
    fun getGifSearch(gifSearchRequest : GifSearchRequest): LiveData<GifListResponse>? {
        _mutableLiveData = trendyGifsRepository.requestGifSearch(gifSearchRequest) as MutableLiveData<GifListResponse>
        return mutableLiveData
    }
    fun getGifSearch(gifSearchRequest : GifSearchRequest,mockedResponse : GifListResponse): LiveData<GifListResponse>? {
        _mutableLiveData.value = mockedResponse
        return mutableLiveData
    }

    /**
     * Calls the saveGif on the repository to save into DB
     * @param gifSimpleObject the GifSimpleObject to be saved to the DB
     * @return Observable the response from the repository
     */
    fun saveGif(gifSimpleObject: GifSimpleObject){
        trendyGifsRepository.saveGif(gifSimpleObject)
    }
    /**
     * Calls the getGifs on the repository to load gifs from DB
     * @return Observable the Loit of GifsSimpleObject from the DB
     */
    fun getGifs(): LiveData<List<GifSimpleObject?>?>{
        return trendyGifsRepository.getGifs()
    }
}