package com.daniel.gifbrowser.Data


import com.daniel.gifbrowser.Domain.GifListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for retrofit builder, contains the API calls that the app will perform
 */
interface  GiphyService{
    @GET("v1/gifs/trending?")
    fun requestTrendyGifs(@Query("api_key") apikey : String, @Query("limit") limit : Int, @Query("rating") rating : String,@Query("offset") offset : Int): Call<GifListResponse>?

    @GET("v1/gifs/search?")
    fun requestGifSearch(@Query("api_key") apikey : String,@Query("q") q : String,@Query("limit") limit : Int,@Query("offset") offset : Int,@Query("rating") rating : String,@Query("lang") lang : String): Call<GifListResponse>?
}