package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable
/**
 * POJO representing the response from the server
 */
data class GifListResponse (
    @SerializedName("data")
    val data : ArrayList<GifObject>?,
    @SerializedName("pagination")
    val pagination : PaginationObject,
    @SerializedName("meta")
    val meta : MetaObject
): Serializable