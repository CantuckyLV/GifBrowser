package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrendyGifsResponse (
    @SerializedName("data")
    val data : ArrayList<GifObject>?,
    @SerializedName("pagination")
    val pagination : PaginationObject,
    @SerializedName("meta")
    val meta : MetaObject
): Serializable