package com.daniel.gifbrowser.Data

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniel.gifbrowser.Domain.TrendyGifsRequest
import com.daniel.gifbrowser.Domain.GifListResponse
import com.daniel.gifbrowser.Domain.GifSearchRequest
import com.daniel.gifbrowser.Domain.GifSimpleObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class TrendyGifsRepository @Inject constructor(private val gifCRUD : GifCRUD) : BaseRepository() {


    private val _mutableLiveData = MutableLiveData<GifListResponse>()
    val mutableLiveData : LiveData<GifListResponse>
        get() = _mutableLiveData

    fun requestTrendyGifs(trendyGifsRequest : TrendyGifsRequest): LiveData<GifListResponse> {
        val call = service.requestTrendyGifs(trendyGifsRequest.apiKey,trendyGifsRequest.limit,trendyGifsRequest.rating,trendyGifsRequest.offset)
        call!!.enqueue(object : Callback<GifListResponse?> {
            override fun onResponse(call: Call<GifListResponse?>, response: Response<GifListResponse?>) {
                if (response.code() == 200) {
                    Log.e("success",""+response.body()!!.pagination)
                    _mutableLiveData.value = response.body()!!
                }else{
                    println(response)
                }
            }

            override fun onFailure(call: Call<GifListResponse?>, t: Throwable) {
                Log.e("failed",""+t)
            }
        })
        return mutableLiveData
    }
    fun requestGifSearch(gifSearchRequest : GifSearchRequest): LiveData<GifListResponse> {
        val call = service.requestGifSearch(gifSearchRequest.apiKey,gifSearchRequest.q,gifSearchRequest.limit,gifSearchRequest.offset,gifSearchRequest.rating,gifSearchRequest.lang)
        call!!.enqueue(object : Callback<GifListResponse?> {
            override fun onResponse(call: Call<GifListResponse?>, response: Response<GifListResponse?>) {
                if (response.code() == 200) {
                    _mutableLiveData.value = response.body()!!
                }else{
                    println(response)
                }
            }

            override fun onFailure(call: Call<GifListResponse?>, t: Throwable) {
                Log.e("failed",""+t)
            }
        })
        return mutableLiveData
    }
    fun saveGif(gifSimpleObject: GifSimpleObject){
        AsyncTask.execute {
            gifCRUD.insertGif(gifSimpleObject)
        }
    }

    fun  getGifs():LiveData<List<GifSimpleObject?>?>{
        return gifCRUD.getFavoriteGifs()
    }
}