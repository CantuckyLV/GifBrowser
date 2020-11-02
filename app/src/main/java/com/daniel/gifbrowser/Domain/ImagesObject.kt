package com.daniel.gifbrowser.Domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImagesObject (
    @SerializedName("fixed_height")
    val fixedHeight : RenditionObject,
    @SerializedName("fixed_height_still")
    val fixedHeightStill : RenditionObject,
    @SerializedName("fixed_height_downsampled")
    val fixedHeightDownsampled : RenditionObject,
    @SerializedName("fixed_width")
    val fixedWidth : RenditionObject,
    @SerializedName("fixed_width_still")
    val fixedWidthStill : RenditionObject,
    @SerializedName("fixed_width_downsampled")
    val fixedWidthDownsampled : RenditionObject,
    @SerializedName("fixed_height_small")
    val fixedHeightSmall : RenditionObject,
    @SerializedName("fixed_height_small_still")
    val fixedHeightSmallStill : RenditionObject,
    @SerializedName("fixed_width_small")
    val fixedWidthSmall : RenditionObject,
    @SerializedName("fixed_width_small_still")
    val fixedWidthSmallStill : RenditionObject,
    @SerializedName("downsized")
    val downsized : RenditionObject,
    @SerializedName("downsized_still")
    val downsizedStill : RenditionObject,
    @SerializedName("downsized_large")
    val downsizedLarge : RenditionObject,
    @SerializedName("downsized_medium")
    val downsizedMedium : RenditionObject,
    @SerializedName("downsized_small")
    val downsizedSmall : RenditionObject,
    @SerializedName("original")
    val original : RenditionObject,
    @SerializedName("original_still")
    val originalStill : RenditionObject,
    @SerializedName("looping")
    val looping : RenditionObject,
    @SerializedName("preview")
    val preview : RenditionObject,
    @SerializedName("preview_gif")
    val preview_gif : RenditionObject
): Serializable