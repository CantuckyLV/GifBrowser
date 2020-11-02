package com.daniel.gifbrowser.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepository {
    /**
     * Initialize retrofit service instance and variables
     */
    protected val gson = GsonBuilder()
        .setLenient()
        .create()
    protected val retrofit = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    protected val service = retrofit.create(GiphyService::class.java)

}