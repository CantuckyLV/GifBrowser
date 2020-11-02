package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MetaObject (
    @SerializedName("msg")
    val offset : String,
    @SerializedName("status")
    val status : String,
    @SerializedName("response_id")
    val count : String
): Serializable