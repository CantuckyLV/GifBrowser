package com.daniel.gifbrowser.Data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daniel.gifbrowser.Domain.TrendyGifsRequest
import com.daniel.gifbrowser.Domain.TrendyGifsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class TrendyGifsRepository : BaseRepository() {


    private val _mutableLiveData = MutableLiveData<TrendyGifsResponse>()
    val mutableLiveData : LiveData<TrendyGifsResponse>
        get() = _mutableLiveData

    fun requestTrendyGifs(trendyGifsRequest : TrendyGifsRequest): LiveData<TrendyGifsResponse> {
        val call = service.requestTrendyGifs(trendyGifsRequest.apiKey,trendyGifsRequest.limit,trendyGifsRequest.rating)
        call!!.enqueue(object : Callback<TrendyGifsResponse?> {
            override fun onResponse(call: Call<TrendyGifsResponse?>, response: Response<TrendyGifsResponse?>) {
                if (response.code() == 200) {
                    println("success")
                    _mutableLiveData.value = response.body()!!
                }else{
                    println(response)
                }
            }

            override fun onFailure(call: Call<TrendyGifsResponse?>, t: Throwable) {
                Log.e("failed",""+t)
            }
        })
        return mutableLiveData
    }
}