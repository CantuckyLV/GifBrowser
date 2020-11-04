package com.daniel.gifbrowser.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.daniel.gifbrowser.Domain.GifSimpleObject

@Dao
interface GifCRUD{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGif(gifSimpleObject: GifSimpleObject?)

    @Query("SELECT * FROM'FAVGIFS'")
    fun getFavoriteGifs(): LiveData<List<GifSimpleObject?>?>

    @Delete
    fun deleteGif(u: GifSimpleObject?)
}