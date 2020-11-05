package com.daniel.gifbrowser.Data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSimpleObject
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val gifCRUD : GifCRUD) : BaseRepository(){


    fun getFavoritesList() : LiveData<List<GifSimpleObject?>?> {
        return gifCRUD.getFavoriteGifs()
    }
    fun removeFavorite(gifSimpleObject: GifSimpleObject){
        AsyncTask.execute {
            gifCRUD.deleteGif(gifSimpleObject)
        }
    }

}