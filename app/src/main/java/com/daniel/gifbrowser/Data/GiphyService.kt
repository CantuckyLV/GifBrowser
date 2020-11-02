package com.daniel.gifbrowser.Data


import com.daniel.gifbrowser.Domain.TrendyGifsRequest
import com.daniel.gifbrowser.Domain.TrendyGifsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface for retrofit builder, contains the API calls that the app will perform
 */
interface  GiphyService{
    @GET("v1/gifs/trending?")
    fun requestTrendyGifs(@Query("api_key") apikey : String, @Query("limit") limit : Int, @Query("rating") rating : String): Call<TrendyGifsResponse>?
    /*@GET("")
    fun getUser(@Path("") id:String): Call<>?*/
}