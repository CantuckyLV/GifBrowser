package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserObject (
    @SerializedName("avatar_url")
    val avatarUrl : String,
    @SerializedName("banner_url")
    val bannerUrl : String,
    @SerializedName("profile_url")
    val profileUrl : String,
    @SerializedName("username")
    val username : String,
    @SerializedName("display_name")
    val displayName : String
    ): Serializable