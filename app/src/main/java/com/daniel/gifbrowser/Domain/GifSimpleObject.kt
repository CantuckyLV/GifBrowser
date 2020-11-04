package com.daniel.gifbrowser.Domain

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "FAVGIFS")
data class GifSimpleObject (
    @SerializedName("id")
    val id : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("is_fav")
    val isFav : Boolean
):Serializable
