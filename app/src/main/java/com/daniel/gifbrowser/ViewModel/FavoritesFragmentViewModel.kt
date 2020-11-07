package com.daniel.gifbrowser.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Data.FavoritesRepository
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSimpleObject
import javax.inject.Inject

class FavoritesFragmentViewModel @Inject constructor(private val gifDB : GifDB) : ViewModel(){

    private val favoritesRepository = FavoritesRepository(gifDB.gifCRUD())
    private var _mutableLiveData = MutableLiveData<ArrayList<GifSimpleObject>>()
    val mutableLiveData : LiveData<ArrayList<GifSimpleObject>>
        get() = _mutableLiveData

    fun getFavoritesList() : LiveData<List<GifSimpleObject?>?> {
        return favoritesRepository.getFavoritesList()
    }

    fun getFavoritesList(mockedDB : ArrayList<GifSimpleObject>) : LiveData<ArrayList<GifSimpleObject>> {
        _mutableLiveData.value = mockedDB
        return mutableLiveData
    }
    fun reMoveFavorite(gifSimpleObject : GifSimpleObject){
        favoritesRepository.removeFavorite(gifSimpleObject)
    }
}