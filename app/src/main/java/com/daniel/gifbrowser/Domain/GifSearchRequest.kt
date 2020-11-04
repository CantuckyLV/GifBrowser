package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * POJO containing a list of gifs
 */
data class GifSearchRequest (
    @SerializedName("api_key")
    val apiKey : String,
    @SerializedName("q")
    val q : String,
    @SerializedName("limit")
    val limit : Int,
    @SerializedName("offset")
    val offset : Int,
    @SerializedName("rating")
    val rating : String,
    @SerializedName("lang")
    val lang : String,
    @SerializedName("random_id")
    val theRandomID : String



): Serializable