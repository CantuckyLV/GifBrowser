package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GifObject (
    @SerializedName("type")
    val type : String,
    @SerializedName("id")
    val id : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("bitly_url")
    val bitlyUrl : String,
    @SerializedName("embed_url")
    val embedUrl : String,
    @SerializedName("username")
    val username : String,
    @SerializedName("source")
    val source : String,
    @SerializedName("rating")
    val rating : String,
    @SerializedName("content_url")
    val contentUrl : String,
    @SerializedName("user")
    val user : UserObject,
    @SerializedName("source_tld")
    val sourceTld : String,
    @SerializedName("source_post_url")
    val sourcePostUrl : String,
    @SerializedName("update_datetime")
    val updateDatetime : String,
    @SerializedName("create_datetime")
    val createDatetime : String,
    @SerializedName("import_datetime")
    val importDatetime : String,
    @SerializedName("trending_datetime")
    val trendingDatetime : String,
    @SerializedName("images")
    val images : ImagesObject,
    @SerializedName("title")
    val title : String
    ): Serializable