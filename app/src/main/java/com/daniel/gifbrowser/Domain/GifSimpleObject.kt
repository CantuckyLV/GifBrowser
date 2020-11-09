package com.daniel.gifbrowser.Domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * POJO representing a gif as it will be saved to the DB
 */
@Entity(tableName = "FAVGIFS")
data class GifSimpleObject (
    @PrimaryKey
    @SerializedName("id")
    val id : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("is_fav")
    var isFav : Boolean
):Serializable
