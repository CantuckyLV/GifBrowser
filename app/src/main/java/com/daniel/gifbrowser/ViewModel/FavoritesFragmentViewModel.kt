package com.daniel.gifbrowser.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daniel.gifbrowser.DB.GifDB
import com.daniel.gifbrowser.Data.FavoritesRepository
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSimpleObject
import javax.inject.Inject
/**
 * ViewModel Class for FavoritesFragment
 * @param gifDB injected data base for  repository to comunicate with
 */

class FavoritesFragmentViewModel @Inject constructor(private val gifDB : GifDB) : ViewModel(){

    private val favoritesRepository = FavoritesRepository(gifDB.gifCRUD())
    private var _mutableLiveData = MutableLiveData<ArrayList<GifSimpleObject>>()
    val mutableLiveData : LiveData<ArrayList<GifSimpleObject>>
        get() = _mutableLiveData

    /**
     * Calls the getFavoritesList on the repository to fetch favorite gifs from DB
     * @return Observable the lostof GifSimpleObject from the DB
     */
    fun getFavoritesList() : LiveData<List<GifSimpleObject?>?> {
        return favoritesRepository.getFavoritesList()
    }

    fun getFavoritesList(mockedDB : ArrayList<GifSimpleObject>) : LiveData<ArrayList<GifSimpleObject>> {
        _mutableLiveData.value = mockedDB
        return mutableLiveData
    }
    /**
     * Calls the removeFavorite on the repository to remove a gif from DB
     * @param gifSimpleObject the gifSimpleObject to be deleted from the DB
     * @return Observable the lostof GifSimpleObject from the DB
     */
    fun reMoveFavorite(gifSimpleObject : GifSimpleObject){
        favoritesRepository.removeFavorite(gifSimpleObject)
    }
}