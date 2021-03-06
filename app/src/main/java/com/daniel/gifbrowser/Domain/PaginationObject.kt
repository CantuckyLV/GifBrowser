package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable
/**
 * POJO representing the pagination Object
 */
data class PaginationObject (
    @SerializedName("offset")
    val offset : String,
    @SerializedName("total_count")
    val totalCount : String,
    @SerializedName("count")
    val count : String
): Serializable