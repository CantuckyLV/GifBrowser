package com.daniel.gifbrowser.Data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSimpleObject
import javax.inject.Inject

/**
 * Repository class for FavoritesFragment
 * @param gifCRUD Injected Dao from DB created elsewhere
 */

class FavoritesRepository @Inject constructor(private val gifCRUD : GifCRUD) : BaseRepository(){


    /**
     *  Calls the dao's getFavoriteGifs method
     *  @return LiveData the LiveData of posts on the DB
     */
    fun getFavoritesList() : LiveData<List<GifSimpleObject?>?> {
        return gifCRUD.getFavoriteGifs()
    }
    /**
     *  Calls the dao's deleteGif method
     *  @param gifSimpleObject the Gif to be deleted from the DB
     */
    fun removeFavorite(gifSimpleObject: GifSimpleObject){
        AsyncTask.execute {
            gifCRUD.deleteGif(gifSimpleObject)
        }
    }

}