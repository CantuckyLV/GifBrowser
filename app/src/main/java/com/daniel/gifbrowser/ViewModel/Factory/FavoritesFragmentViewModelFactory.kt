package com.daniel.gifbrowser.ViewModel.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.daniel.gifbrowser.DB.GifDB

/**
 * Factory Class for FavoritesFragmentViewModel
 * @param gifDB to inject data base for  repository to comunicate with
 */

class FavoritesFragmentViewModelFactory (val gifDB : GifDB): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return   modelClass.getConstructor(com.daniel.gifbrowser.DB.GifDB::class.java).newInstance(gifDB)
    }
}