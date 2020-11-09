package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.ArrayList

/**
 * POJO representing a request for the server
 */
data class TrendyGifsRequest (
    @SerializedName("api_key")
    val apiKey : String,
    @SerializedName("limit")
    val limit : Int,
    @SerializedName("offset")
    val offset : Int,
    @SerializedName("rating")
    val rating : String,
    @SerializedName("random_id")
    val theRandomID : String



): Serializable