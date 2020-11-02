package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RenditionObject (
    @SerializedName("url")
    val url : String,
    @SerializedName("width")
    val width : String,
    @SerializedName("height")
    val height : String,
    @SerializedName("size")
    val size : String,
    @SerializedName("mp4")
    val mp4 : String,
    @SerializedName("mp4_size")
    val mp4Size : String,
    @SerializedName("webp")
    val webp : String,
    @SerializedName("webp_size")
    val webpSize : String
): Serializable