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
/**
 * Repository class for TrendyGifsFragment
 * @param gifCRUD Injected Dao from DB created elsewhere
 */


class TrendyGifsRepository @Inject constructor(private val gifCRUD : GifCRUD) : BaseRepository() {


    private val _mutableLiveData = MutableLiveData<GifListResponse>()
    val mutableLiveData : LiveData<GifListResponse>
        get() = _mutableLiveData

    /**
     *  Calls the service's requestTrendyGifs method
     *  on 200: sets the mutable live data as the response's gif list
     *  @param trendyGifsRequest the Object representing the request to the server
     * @return Observable the response from the repository
     */

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

    /**
     *  Calls the service's requestGifSearch method
     *  on 200: sets the mutable live data as the response's gif list
     *  @param gifSearchRequest the Object representing the request to the server
     * @return Observable the response from the repository
     */
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
    /**
     *  Calls the dao's insertGif method
     *  @param gifSimpleObject the Gif to be inserted to the DB
     */
    fun saveGif(gifSimpleObject: GifSimpleObject){
        AsyncTask.execute {
            gifCRUD.insertGif(gifSimpleObject)
        }
    }
    /**
     *  Calls the dao's getFavoriteGifs method
     *  @return LiveData the LiveData of posts on the DB
     */
    fun  getGifs():LiveData<List<GifSimpleObject?>?>{
        return gifCRUD.getFavoriteGifs()
    }
}