package com.daniel.gifbrowser.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daniel.gifbrowser.Data.GifCRUD
import com.daniel.gifbrowser.Domain.GifSimpleObject
/**
 * Abstract class representig the DB
 */

@Database(entities = [GifSimpleObject::class], version = 1)
abstract class GifDB : RoomDatabase() {
    abstract fun gifCRUD(): GifCRUD
}