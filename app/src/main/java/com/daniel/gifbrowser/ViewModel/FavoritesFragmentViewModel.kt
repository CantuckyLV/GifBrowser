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

    fun getFavoritesList() : LiveData<List<GifSimpleObject?>?> {
        return favoritesRepository.getFavoritesList()
    }
    fun reMoveFavorite(gifSimpleObject : GifSimpleObject){
        favoritesRepository.removeFavorite(gifSimpleObject)
    }
}